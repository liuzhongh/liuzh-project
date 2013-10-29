package com.shangkang.im.service;

import android.app.Service;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.shangkang.im.MainActivity;
import com.shangkang.im.applications.ActivityManager;
import com.shangkang.im.constants.Constant;
import com.shangkang.im.helper.ConnectHelper;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by liuzh on 13-10-25.
 */
public class MsgListerService extends Service {

    private final static String TAG = MsgListerService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String userName = intent.getStringExtra(Constant.FROM_USER_NAME);
        boolean isBootService = intent.getBooleanExtra(Constant.IS_BOOT_SERVICE, false);

        Log.d(TAG, "It is from boot service :" + isBootService);

        doListerMsg(userName, MsgListerService.this);

        if(!isBootService)
            ((ActivityManager)this.getApplication()).exit();

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void doListerMsg(String userName, final MsgListerService msgListerService)
    {
        XMPPConnection connection = ConnectHelper.getInstance().getConnection();

        ChatManager chatManager = connection.getChatManager();
        Log.d(TAG, " lister from user :" + userName);
        chatManager.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean b) {

            }
        });
        Chat newChat = chatManager.createChat(userName + "@liuzh-ubuntu", new MessageListener() {

            @Override
            public void processMessage(Chat chat, Message message) {

                String msg = message.getBody();

                Log.d(TAG, "msg : " + msg);
                if (msg != null && msg.trim().startsWith(Constant.TEL_PREFIX)) {

                    Log.d(TAG, "start call phone number : " + msg);
                    Intent phoneIntent = new Intent();

                    phoneIntent.setAction(Intent.ACTION_CALL);
                    phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    phoneIntent.setData(Uri.parse(msg));

                    msgListerService.startActivity(phoneIntent);
                }
            }
        });

    }
}
