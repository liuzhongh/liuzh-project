package com.shangkang.im.helper;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * Created by liuzh on 13-10-17.
 */
public class ConnectHelper {
    private static int PORT = 5222;
    private static String SERVER_ADDRESS = "192.168.13.118";
    private static XMPPConnection connection;

    private static void openConnection() {
        try {
            //url、端口，也可以设置连接的服务器名字，地址，端口，用户。
            ConnectionConfiguration connConfig = new ConnectionConfiguration(SERVER_ADDRESS, PORT);
            connection = new XMPPConnection(connConfig);
            connection.connect();
        }
        catch (XMPPException xe)
        {
            xe.printStackTrace();
        }
    }

    public static XMPPConnection getConnection() {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    public static void closeConnection() {
        connection.disconnect();
        connection = null;
    }
}
