package com.shangkang.im;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.shangkang.im.applications.ActivityManager;
import com.shangkang.im.constants.Constant;
import com.shangkang.im.helper.ConnectHelper;
import com.shangkang.im.helper.LoginConfigHelper;
import com.shangkang.im.helper.ServerConnection;
import com.shangkang.im.model.LoginConfig;
import com.shangkang.im.task.LoginTask;
import com.shangkang.im.utils.StringUtil;
import com.shangkang.im.utils.ValidateUtil;

import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class MainActivity extends Activity {

    private EditText userNameEditText, passwordEditText;
    private CheckBox rememberCheckBox;
    private Button loginBtn;
    private SharedPreferences preferences;
    private LoginConfig loginConfig;
    private ProgressDialog pd;
    /*private Handler handler = new Handler() {
        public void handleMessage(android.os.Message m) {
            Message msg = (Message) m.obj;
            //把从服务器获得的消息通过广播发送
            Intent intent = new Intent("org.yhn.mes");
            String[] message = new String[]{
                    msg.getFrom(),
                    msg.getBody()};
            System.out.println("==========收到服务器消息  From===========" + message[0].toString());
            System.out.println("==========收到服务器消息  Body===========" + message[1].toString());
            intent.putExtra("message", message);
            getBaseContext().sendBroadcast(intent);
        };
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences(Constant.LOGIN_SET, Context.MODE_PRIVATE);

        setContentView(R.layout.activity_main_im);
        pd = new ProgressDialog(this);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
        final View loginView = factory.inflate(R.layout.login_config, null);


        final EditText xmppHostText = (EditText) loginView.findViewById(R.id.server_ip_edt);
        final EditText fromUserText = (EditText) loginView.findViewById(R.id.from_user_name_edt);

        xmppHostText.setText(loginConfig.getXmppHost());
        fromUserText.setText(loginConfig.getFromUserName());

        switch (item.getItemId()) {
            case R.id.menu_login_set:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("参数设置")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(loginView)
                        .setMessage("请设置对应参数")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        String xmppHost = StringUtil
                                                .doEmpty(xmppHostText.getText()
                                                        .toString());
                                        String fromUser = StringUtil.doEmpty(fromUserText.getText().toString());

                                        loginConfig.setXmppHost(xmppHost);
                                        loginConfig.setFromUserName(fromUser);
                                        ConnectHelper.getInstance().init(
                                                loginConfig);
                                        MainActivity.this
                                                .saveLoginConfig(loginConfig);
                                    }
                                })
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.cancel();// 取消弹出框
                                    }
                                }).create().show();

                break;
            case R.id.menu_exit:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public ProgressDialog getProgressDialog()
    {
        return pd;
    }

    public void saveLoginConfig(LoginConfig loginConfig) {
        preferences.edit()
                .putString(Constant.XMPP_HOST, loginConfig.getXmppHost())
                .commit();
        preferences.edit()
                .putInt(Constant.XMPP_PORT, loginConfig.getXmppPort()).commit();
        preferences
                .edit()
                .putString(Constant.XMPP_SEIVICE_NAME,
                        loginConfig.getXmppServiceName()).commit();
        preferences.edit()
                .putString(Constant.USERNAME, loginConfig.getUsername())
                .commit();
        preferences.edit()
                .putString(Constant.PASSWORD, loginConfig.getPassword())
                .commit();
        preferences.edit()
                .putBoolean(Constant.IS_REMEMBER, loginConfig.isRemember())
                .commit();
        preferences.edit()
                .putBoolean(Constant.IS_FIRSTSTART, loginConfig.isFirstStart())
                .commit();
        preferences.edit()
                .putString(Constant.FROM_USER_NAME, loginConfig.getFromUserName());
    }

    private void init(){
        loginConfig = getLoginConfig();
        ((ActivityManager) this.getApplication()).addActivity(this);

        userNameEditText = (EditText) findViewById(R.id.ui_username_input);
        passwordEditText = (EditText) findViewById(R.id.ui_password_input);
        rememberCheckBox = (CheckBox) findViewById(R.id.remember);
        loginBtn = (Button) findViewById(R.id.ui_login_btn);

        // 初始化各组件的默认状态
        userNameEditText.setText(loginConfig.getUsername());
        passwordEditText.setText(loginConfig.getPassword());
        rememberCheckBox.setChecked(loginConfig.isRemember());

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkData() && validateInternet()) {
                    String username = userNameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    // 先记录下各组件的目前状态,登录成功后才保存
                    loginConfig.setPassword(password);
                    loginConfig.setUsername(username);
                    loginConfig.setRemember(rememberCheckBox.isChecked());

                    LoginTask loginTask = new LoginTask(MainActivity.this,
                            loginConfig);
                    loginTask.execute();
                }
            }
        });
    }

    /**
     *
     * 登录校验.
     *
     * @return
     */
    private boolean checkData() {
        boolean checked = false;
        checked = (!ValidateUtil.isEmpty(userNameEditText, "登录名") && !ValidateUtil
                .isEmpty(passwordEditText, "密码"));
        return checked;
    }

    private boolean validateInternet() {
        ConnectivityManager manager = (ConnectivityManager) getBaseContext()
                .getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        if (manager == null) {
            openWirelessSet();
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
        openWirelessSet();
        return false;
    }

    private void openWirelessSet() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getBaseContext());
        dialogBuilder
                .setTitle(R.string.prompt)
                .setMessage(getBaseContext().getString(R.string.check_connection))
                .setPositiveButton(R.string.menu_settings,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                                Intent intent = new Intent(
                                        Settings.ACTION_WIRELESS_SETTINGS);
                                getBaseContext().startActivity(intent);
                            }
                        })
                .setNegativeButton(R.string.close,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                dialog.cancel();
                            }
                        });
        dialogBuilder.show();
    }

    private LoginConfig getLoginConfig() {
        /*LoginConfig loginConfig = new LoginConfig();
        loginConfig.setXmppHost(preferences.getString(Constant.XMPP_HOST,
                getResources().getString(R.string.server_host_ip)));
        loginConfig.setXmppPort(preferences.getInt(Constant.XMPP_PORT,
                getResources().getInteger(R.integer.server_port)));
        loginConfig.setUsername(preferences.getString(Constant.USERNAME, null));
        loginConfig.setPassword(preferences.getString(Constant.PASSWORD, null));
        loginConfig.setFromUserName(preferences.getString(Constant.FROM_USER_NAME, Constant.FROM_USER_NAME_SAMPLE));
        loginConfig.setXmppServiceName(preferences.getString(
                Constant.XMPP_SEIVICE_NAME,
                getResources().getString(R.string.xmpp_service_name)));
        loginConfig.setRemember(preferences.getBoolean(Constant.IS_REMEMBER,
                getResources().getBoolean(R.bool.is_remember)));
        loginConfig.setFirstStart(preferences.getBoolean(
                Constant.IS_FIRSTSTART, true));*/
        return LoginConfigHelper.getLoginConfig(preferences, this.getBaseContext());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectHelper.getInstance().init(loginConfig);
    }

    class Login extends AsyncTask<String, String, String> {
        private boolean isLogin = true;
        private String account;
        private String password;

        @Override
        protected String doInBackground(String... params) {
            this.account = params[0];
            this.password = params[1];

            try {
                ConnectHelper.getInstance().getConnection();
                ConnectHelper.getInstance().getConnection().login(account, password);
            } catch (XMPPException e) {
                isLogin = false;
                ConnectHelper.getInstance().disconnect();
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            // 如果登录成功
            if (isLogin) {
                Toast.makeText(MainActivity.this, "登录成功！",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ChatActivity.class);

                intent.putExtra("loginName", this.account);
                intent.putExtra("password", this.password);

                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "登录失败！",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
