package com.shangkang.im;

import android.os.Handler;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by liuzh on 13-10-17.
 */
public class AndroidChatManagerListener implements ChatManagerListener {
    private Handler handler;

    public AndroidChatManagerListener(Handler handler) {
        this.handler = handler;/**/
    }

    @Override
    public void chatCreated(Chat chat, boolean b) {
        chat.addMessageListener(new MessageListener() {
            public void processMessage(Chat arg0, Message msg) {
                /**通过handler转发消息*/
                android.os.Message m = handler.obtainMessage();
                m.obj = msg;
                m.sendToTarget();


            }
        });
    }
}
