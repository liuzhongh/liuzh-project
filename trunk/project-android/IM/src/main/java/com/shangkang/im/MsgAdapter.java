package com.shangkang.im;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liuzh on 13-10-17.
 */
public class MsgAdapter extends BaseAdapter {

    private Context cxt;
    private List<ChatActivity.Msg> msgs;

    public MsgAdapter(ChatActivity client, List<ChatActivity.Msg> msgs) {
        this.cxt = client;
        this.msgs = msgs;
    }

    @Override
    public int getCount() {
        return msgs.size();
    }

    @Override
    public Object getItem(int position) {
        return msgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //显示消息的布局：内容、背景、用户、时间
        if(convertView == null)
            convertView = LayoutInflater.from(this.cxt).inflate(R.layout.chat_lv_item, null);


        TextView userIdView = (TextView) convertView.findViewById(R.id.item_userId);
        TextView dateView = (TextView) convertView.findViewById(R.id.item_date);
        TextView msgView = (TextView) convertView.findViewById(R.id.item_msg);

        userIdView.setText(msgs.get(position).userId);
        dateView.setText(msgs.get(position).date);
        msgView.setText(msgs.get(position).msg);

        return convertView;
    }
}
