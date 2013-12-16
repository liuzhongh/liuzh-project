package com.shangkang.im.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.shangkang.im.MainActivity;
import com.shangkang.im.R;
import com.shangkang.im.constants.Constant;
import com.shangkang.im.helper.ConnectHelper;
import com.shangkang.im.model.LoginConfig;
import com.shangkang.im.service.MsgListerService;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.OfflineMessageManager;

import java.util.Collection;

/**
 *
 * 登录异步任务.
 *
 * @author Liuzh
 */
public class LoginTask extends AsyncTask<String, Integer, Integer> {

    private ProgressDialog pd;
    private Context context;
    private MainActivity activitySupport;
    private LoginConfig loginConfig;

    public LoginTask(MainActivity activitySupport, LoginConfig loginConfig) {
        this.activitySupport = activitySupport;
        this.loginConfig = loginConfig;
        this.pd = activitySupport.getProgressDialog();
        this.context = activitySupport.getBaseContext();
    }

    @Override
    protected void onPreExecute() {
        pd.setTitle("请稍等");
        pd.setMessage("正在登录...");
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... params) {
        return login();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    }

    @Override
    protected void onPostExecute(Integer result) {
        pd.dismiss();
        switch (result) {
            case Constant.LOGIN_SECCESS: // 登录成功
                Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();

                if(loginConfig.isFirstStart())
                    loginConfig.setFirstStart(false);

                Intent intent = new Intent();
                intent.setClass(context, MsgListerService.class);
                intent.putExtra(Constant.FROM_USER_NAME, loginConfig.getFromUserName());
                intent.putExtra(Constant.XMPP_SEIVICE_NAME, loginConfig.getXmppServiceName());
                activitySupport.saveLoginConfig(loginConfig);// 保存用户配置信息

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

    // 登录
    private Integer login() {
        String username = loginConfig.getUsername();
        String password = loginConfig.getPassword();
        try {
            XMPPConnection connection = ConnectHelper.getInstance()
                    .getConnection();
            connection.connect();
            connection.login(username, password); // 登录

            OfflineMessageManager offlineMessageManager = new OfflineMessageManager(connection);

            offlineMessageManager.deleteMessages();

            connection.sendPacket(new Presence(Presence.Type.available));
            loginConfig.setUsername(username);
            if (loginConfig.isRemember()) {// 保存密码
                loginConfig.setPassword(password);
            } else {
                loginConfig.setPassword("");
            }
            loginConfig.setOnline(true);
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
}