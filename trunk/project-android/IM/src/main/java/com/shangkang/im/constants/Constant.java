package com.shangkang.im.constants;

/**
 * Created by liuzh on 13-10-24.
 */
public class Constant {

    /**
     * 服务器的配置
     */
    public static final String LOGIN_SET = "im_login_set";// 登录设置
    public static final String USERNAME = "username";// 账户
    public static final String FROM_USER_NAME = "FROM_USER_NAME";
    public static final String PASSWORD = "password";// 密码
    public static final String XMPP_HOST = "server_host_ip";// 地址
    public static final String XMPP_PORT = "server_port";// 端口
    public static final String XMPP_SEIVICE_NAME = "xmpp_service_name";// 服务名
    public static final String IS_REMEMBER = "isRemember";// 是否记住账户密码
    public static final String IS_FIRSTSTART = "isFirstStart";// 是否首次启动
    public static final String FROM_USER_NAME_SAMPLE = "messenger";

    /**
     * 登录提示
     */
    public static final int LOGIN_SECCESS = 0;// 成功
    public static final int HAS_NEW_VERSION = 1;// 发现新版本
    public static final int IS_NEW_VERSION = 2;// 当前版本为最新
    public static final int LOGIN_ERROR_ACCOUNT_PASS = 3;// 账号或者密码错误
    public static final int SERVER_UNAVAILABLE = 4;// 无法连接到服务器
    public static final int LOGIN_ERROR = 5;// 连接失败

    public static final String TEL_PREFIX = "tel:";
    public static final String IS_BOOT_SERVICE = "IS_BOOT_SERVICE";

}
