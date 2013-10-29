package com.shangkang.im.helper;

import android.content.Context;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

/**
 * Created by liuzh on 13-10-16.
 */
public class ServerConnection {
    private Context context;

    public ServerConnection(Context context) {
        this.context = context;

    }

    public ServerConnection() {

    }

    public boolean login(String a, String p) {
        /** 创建connection链接 */
        XMPPConnection connection = ConnectHelper.getInstance().getConnection();
        try {
            /** 建立连接 */
            connection.connect();
            /** 登录*/
            connection.login(a, p);
            /** 设置状态 */
            Presence presence = new Presence(Presence.Type.available);
            presence.setStatus("Q我吧");
            connection.sendPacket(presence);


            /** 开启读写线程，并加入到管理类中*/
            //ClientSendThread cst=new ClientSendThread(connection);
            //cst.start();
            //ManageClientThread.addClientSendThread(a, cst);
            return true;
        } catch (XMPPException e) {
            e.printStackTrace();
        }
        return false;
    }
}