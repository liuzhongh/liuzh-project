package com.shangkang.im;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shangkang.im.helper.ConnectHelper;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends Activity {

    private String loginName;
    private String password;
    private List<Msg> msgs = new ArrayList<Msg>();
    private MsgAdapter msgAdapter;
    private final static String TAG = ChatActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_main);

        loginName = getIntent().getStringExtra("loginName");
        password = getIntent().getStringExtra("password");

        TextView loginNameTV = (TextView) findViewById(R.id.login_name_tv);
        ListView listView = (ListView) findViewById(R.id.rec_msg_lv);

        loginNameTV.setText(loginName);

        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgAdapter = new MsgAdapter(this, msgs);
        listView.setAdapter(msgAdapter);

        //消息监听
        ChatManager cm = ConnectHelper.getInstance().getConnection().getChatManager();
        //发送消息给water-pc服务器water（获取自己的服务器，和好友）
        final Chat newchat = cm.createChat("zzq@liuzh-ubuntu", null);

        cm.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean b) {
                chat.addMessageListener(new MessageListener() {
                    @Override
                    public void processMessage(Chat chat, Message message) {
                        Log.d(TAG, "from :" + message.getFrom());
                        Log.d(TAG, "message :" + message.getBody());

                        //收到来自liuzh-ubuntu服务器water的消息（获取自己的服务器，和好友）
                        if(message.getFrom().contains(loginName+"@liuzh-ubuntu"))
                        {
                            //获取用户、消息、时间、IN
                            Msg args = new Msg(loginName, message.getBody(), new Date().toString(), "IN" );

                            //在handler里取出来显示消息
                            android.os.Message msg = msgHandler.obtainMessage();
                            msg.what = 1;
                            msg.obj = args;
                            msg.sendToTarget();
                        }
                        else
                        {
                            //message.getFrom().cantatins(获取列表上的用户，组，管理消息);
                            //获取用户、消息、时间、IN
                            Msg args = new Msg( message.getFrom(), message.getBody(), new Date().toString(), "IN" );

                            //在handler里取出来显示消息
                            android.os.Message msg = msgHandler.obtainMessage();
                            msg.what = 1;
                            msg.obj = args;
                            msg.sendToTarget();
                        }
                    }
                });
            }
        });

        Button sendBtn = (Button) findViewById(R.id.send_btn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText sendMsgET = (EditText) findViewById(R.id.send_msg_edit);

                String msg = sendMsgET.getText().toString();

                new ChatAction().execute(loginName, msg, sendMsgET, newchat);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat, menu);
        return true;
    }

    class ChatAction extends AsyncTask {
        private String loginName;
        private String sendMsg;
        private EditText sendMsgET;
        private Chat chat;
        @Override
        protected Object doInBackground(Object[] params) {
            this.loginName = (String) params[0];
            this.sendMsg = (String) params[1];
            this.sendMsgET = (EditText) params[2];
            this.chat = (Chat) params[3];

            try {

                if(sendMsg.length() > 0){
                    //发送消息
                    msgs.add(new Msg(loginName, sendMsg, new Date().toString(), "OUT"));
                    //刷新适配器
                    msgAdapter.notifyDataSetChanged();

                    //发送消息给xiaowang
                    chat.sendMessage(sendMsg);
                }
                else
                {
                    Toast.makeText(ChatActivity.this, "请输入信息", Toast.LENGTH_SHORT).show();
                }
                //清空text
                sendMsgET.setText("");
            } catch (XMPPException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private Handler msgHandler = new Handler() {
        public void handleMessage(android.os.Message msg)
        {

            switch (msg.what) {
                case 1:
                    //获取消息并显示
                    Msg args = (Msg) msg.obj;
                    msgs.add(args);
                    //刷新适配器
                    msgAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };


    public class Msg {
        String userId;
        String msg;
        String date;
        String from;

        public Msg(String userId, String msg, String date, String from) {
            this.userId = userId;
            this.msg = msg;
            this.date = date;
            this.from = from;
        }
    }
}
