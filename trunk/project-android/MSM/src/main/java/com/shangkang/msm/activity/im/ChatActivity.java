package com.shangkang.msm.activity.im;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shangkang.msm.R;
import com.shangkang.msm.activity.LoginActivity;
import com.shangkang.msm.activity.MainActivity;
import com.shangkang.msm.manager.ContacterManager;
import com.shangkang.msm.manager.MessageManager;
import com.shangkang.msm.manager.XmppConnectionManager;
import com.shangkang.msm.model.IMMessage;
import com.shangkang.msm.model.User;
import com.shangkang.msm.util.StringUtil;

public class ChatActivity extends AChatActivity {
    private ImageView titleBack;
    private MessageListAdapter adapter = null;
    private EditText messageInput = null;
    private Button messageSendBtn = null;
    private ImageButton userInfo;
    private ListView listView;
    private int recordCount;
    private View listHead;
    private Button listHeadButton;
    private User user;// 聊天人
    private TextView tvChatTitle;
    private String to_name;
    private ImageView iv_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        init();
    }

    private void init() {
        titleBack = (ImageView) findViewById(R.id.title_back);
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // iv_status=findViewById(R.id.)
        // 与谁聊天
        tvChatTitle = (TextView) findViewById(R.id.to_chat_name);
        user = ContacterManager.getByUserJid(to, XmppConnectionManager
                .getInstance().getConnection());
        if (null == user) {
            to_name = StringUtil.getUserNameByJid(to);
        } else {
            to_name = user.getName() == null ? user.getJID() : user.getName();

        }
        tvChatTitle.setText(to_name);

        userInfo = (ImageButton) findViewById(R.id.user_info);
        userInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, FriendInfoActivity.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.chat_list);
        listView.setCacheColorHint(0);
        adapter = new MessageListAdapter(ChatActivity.this, getMessages(),
                listView);

        // 头

        LayoutInflater mynflater = LayoutInflater.from(context);
        listHead = mynflater.inflate(R.layout.chatlistheader, null);
        listHeadButton = (Button) listHead.findViewById(R.id.buttonChatHistory);
        listHeadButton.setOnClickListener(chatHistoryCk);
        listView.addHeaderView(listHead);
        listView.setAdapter(adapter);

        messageInput = (EditText) findViewById(R.id.chat_content);
        messageSendBtn = (Button) findViewById(R.id.chat_sendbtn);
        messageSendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message = messageInput.getText().toString();
                if ("".equals(message)) {
                    Toast.makeText(ChatActivity.this, "不能为空",
                            Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        sendMessage(message);
                        messageInput.setText("");
                    } catch (Exception e) {
                        showToast("信息发送失败");
                        messageInput.setText(message);
                    }
                    closeInput();
                }
            }
        });
    }

    @Override
    protected void receiveNewMessage(IMMessage message) {

    }

    @Override
    protected void refreshMessage(List<IMMessage> messages) {

        adapter.refreshList(messages);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recordCount = MessageManager.getInstance(context)
                .getChatCountWithSb(to);
        if (recordCount <= 0) {
            listHead.setVisibility(View.GONE);
        } else {
            listHead.setVisibility(View.VISIBLE);
        }
        adapter.refreshList(getMessages());
    }

    private class MessageListAdapter extends BaseAdapter {

        private List<IMMessage> items;
        private Context context;
        private ListView adapterList;
        private LayoutInflater inflater;

        public MessageListAdapter(Context context, List<IMMessage> items,
                                  ListView adapterList) {
            this.context = context;
            this.items = items;
            this.adapterList = adapterList;
        }

        public void refreshList(List<IMMessage> items) {
            this.items = items;
            this.notifyDataSetChanged();
            adapterList.setSelection(items.size() - 1);
        }

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            IMMessage message = items.get(position);
            if (message.getMsgType() == 0) {
                convertView = this.inflater.inflate(
                        R.layout.formclient_chat_in, null);
            } else {
                convertView = this.inflater.inflate(
                        R.layout.formclient_chat_out, null);
            }
            TextView useridView = (TextView) convertView
                    .findViewById(R.id.formclient_row_userid);
            TextView dateView = (TextView) convertView
                    .findViewById(R.id.formclient_row_date);
            TextView msgView = (TextView) convertView
                    .findViewById(R.id.formclient_row_msg);
            if (message.getMsgType() == 0) {
                if (null == user) {
                    useridView.setText(StringUtil.getUserNameByJid(to));
                } else {
                    useridView.setText(user.getName());
                }

            } else {
                useridView.setText("我");
            }
            dateView.setText(message.getTime());
            msgView.setText(message.getContent());
            return convertView;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.menu_return_main_page:
                intent.setClass(context, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_relogin:
                intent.setClass(context, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_exit:
                isExit();
                break;
        }
        return true;

    }

    /**
     * 点击进入聊天记录
     */
    private OnClickListener chatHistoryCk = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent in = new Intent(context, ChatHistoryActivity.class);
            in.putExtra("to", to);
            startActivity(in);
        }
    };

}