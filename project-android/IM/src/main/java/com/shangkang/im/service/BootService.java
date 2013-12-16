package com.shangkang.im.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.shangkang.im.R;
import com.shangkang.im.constants.Constant;
import com.shangkang.im.helper.ConnectHelper;
import com.shangkang.im.helper.LoginConfigHelper;
import com.shangkang.im.model.LoginConfig;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.OfflineMessageManager;

/**
 * Created by liuzh on 13-10-28.
 */
public class BootService extends BroadcastReceiver {
    private final static String TAG = BootService.class.getSimpleName();
    private final static String NETWORK_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "随机启动拨号App！");

        boolean hasNetwork = validateInternet(context);

        Log.d(TAG, "have network :" + hasNetwork);

        if((intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) && hasNetwork)
                || intent.getAction().equals(NETWORK_ACTION) && hasNetwork)
        {
            SharedPreferences preferences = context.getSharedPreferences(Constant.LOGIN_SET, Context.MODE_PRIVATE);

            LoginConfig loginConfig = LoginConfigHelper.getLoginConfig(preferences, context);

            boolean isFirstStart = loginConfig.isFirstStart();

            Log.d(TAG, "Is first start :" + isFirstStart);

            if(!isFirstStart)
            {
                new LoginAction(context).execute(loginConfig);
            }
        }

    }

    private boolean validateInternet(Context context) {
        try
        {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(context.CONNECTIVITY_SERVICE);
            if (manager == null) {
                return false;
            } else {
                NetworkInfo[] info = manager.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());

            return false;
        }
        return false;
    }

    class LoginAction extends AsyncTask<LoginConfig, Integer, Integer> {

        private String fromUserName;
        private String serviceName;
        private Context context;

        LoginAction(Context context) {
            this.context = context;
        }

        @Override
        protected Integer doInBackground(LoginConfig... loginConfigs) {

            if(loginConfigs != null)
            {
                LoginConfig loginConfig = loginConfigs[0];

                String username = loginConfig.getUsername();
                String password = loginConfig.getPassword();
                fromUserName = loginConfig.getFromUserName();
                serviceName = loginConfig.getXmppServiceName();

                Log.d(TAG, "随系统启动登录,当前用户为:" + username);

                try {
                    ConnectHelper.getInstance().init(loginConfig);
                    XMPPConnection connection = ConnectHelper.getInstance()
                            .getConnection();
                    connection.connect();
                    connection.login(username, password); // 登录

                    OfflineMessageManager offlineMessageManager = new OfflineMessageManager(connection);

                    offlineMessageManager.deleteMessages();
                    connection.sendPacket(new Presence(Presence.Type.available));
                    return Constant.LOGIN_SECCESS;
                } catch (Exception xee) {
                    xee.printStackTrace();
                    if (xee instanceof XMPPException) {
                        XMPPException xe = (XMPPException) xee;
                        final XMPPError error = xe.getXMPPError();
                        int errorCode = 0;
                        if (error != null) {
                            errorCode = error.getCode();
                        }
                        if (errorCode == 401) {
                            return Constant.LOGIN_ERROR_ACCOUNT_PASS;
                        }else if (errorCode == 403) {
                            return Constant.LOGIN_ERROR_ACCOUNT_PASS;
                        } else {
                            return Constant.SERVER_UNAVAILABLE;
                        }
                    } else {
                        return Constant.LOGIN_ERROR;
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            switch (result) {
                case Constant.LOGIN_SECCESS: // 登录成功
                    Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();

                    intent.setClass(context, MsgListerService.class);
                    intent.putExtra(Constant.FROM_USER_NAME, fromUserName);
                    intent.putExtra(Constant.XMPP_SEIVICE_NAME, serviceName);
                    intent.putExtra(Constant.IS_BOOT_SERVICE, true);

                    context.startService(intent);
                    break;
                case Constant.LOGIN_ERROR_ACCOUNT_PASS:// 账户或者密码错误
                    Toast.makeText(
                            context,
                            context.getResources().getString(
                                    R.string.message_invalid_username_password),
                            Toast.LENGTH_SHORT).show();
                    break;
                case Constant.SERVER_UNAVAILABLE:// 服务器连接失败
                    Toast.makeText(
                            context,
                            context.getResources().getString(
                                    R.string.message_server_unavailable),
                            Toast.LENGTH_SHORT).show();
                    break;
                case Constant.LOGIN_ERROR:// 未知异常
                    Toast.makeText(
                            context,
                            context.getResources().getString(
                                    R.string.unrecoverable_error), Toast.LENGTH_SHORT)
                            .show();
                    break;
            }
            super.onPostExecute(result);
        }
    }
}
