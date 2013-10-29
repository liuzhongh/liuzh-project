package com.shangkang.im.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.shangkang.im.R;
import com.shangkang.im.constants.Constant;
import com.shangkang.im.model.LoginConfig;

/**
 * Created by liuzh on 13-10-28.
 */
public class LoginConfigHelper {

    public static LoginConfig getLoginConfig(SharedPreferences preferences, Context context) {
        LoginConfig loginConfig = new LoginConfig();
        loginConfig.setXmppHost(preferences.getString(Constant.XMPP_HOST,
                context.getResources().getString(R.string.server_host_ip)));
        loginConfig.setXmppPort(preferences.getInt(Constant.XMPP_PORT,
                context.getResources().getInteger(R.integer.server_port)));
        loginConfig.setUsername(preferences.getString(Constant.USERNAME, null));
        loginConfig.setPassword(preferences.getString(Constant.PASSWORD, null));
        loginConfig.setFromUserName(preferences.getString(Constant.FROM_USER_NAME, Constant.FROM_USER_NAME_SAMPLE));
        loginConfig.setXmppServiceName(preferences.getString(
                Constant.XMPP_SEIVICE_NAME,
                context.getResources().getString(R.string.xmpp_service_name)));
        loginConfig.setRemember(preferences.getBoolean(Constant.IS_REMEMBER,
                context.getResources().getBoolean(R.bool.is_remember)));
        loginConfig.setFirstStart(preferences.getBoolean(
                Constant.IS_FIRSTSTART, true));
        return loginConfig;
    }
}
