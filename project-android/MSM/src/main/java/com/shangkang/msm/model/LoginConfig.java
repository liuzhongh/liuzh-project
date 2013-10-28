package com.shangkang.msm.model;

/**
 *
 * 登录配置.
 *
 * @author Liuzh
 */
public class LoginConfig {

    private String xmppHost;// 地址
    private Integer xmppPort;// 端口
    private String xmppServiceName;// 服务器名称
    private String username;// 用户名
    private String password;// 密码
    private String sessionId;// 会话id
    private boolean isRemember;// 是否记住密码
    private boolean isAutoLogin;// 是否自动登录
    private boolean isNovisible;// 是否隐藏登录
    private boolean isOnline;// 用户连接成功connection
    private boolean isFirstStart;// 是否首次启动

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getXmppHost() {
        return xmppHost;
    }

    public void setXmppHost(String xmppHost) {
        this.xmppHost = xmppHost;
    }

    public Integer getXmppPort() {
        return xmppPort;
    }

    public void setXmppPort(Integer xmppPort) {
        this.xmppPort = xmppPort;
    }

    public String getXmppServiceName() {
        return xmppServiceName;
    }

    public void setXmppServiceName(String xmppServiceName) {
        this.xmppServiceName = xmppServiceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean isRemember) {
        this.isRemember = isRemember;
    }

    public boolean isAutoLogin() {
        return isAutoLogin;
    }

    public void setAutoLogin(boolean isAutoLogin) {
        this.isAutoLogin = isAutoLogin;
    }

    public boolean isNovisible() {
        return isNovisible;
    }

    public void setNovisible(boolean isNovisible) {
        this.isNovisible = isNovisible;
    }

    public boolean isFirstStart() {
        return isFirstStart;
    }

    public void setFirstStart(boolean isFirstStart) {
        this.isFirstStart = isFirstStart;
    }

}