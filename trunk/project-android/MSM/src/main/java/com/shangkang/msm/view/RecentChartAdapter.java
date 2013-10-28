package com.shangkang.msm.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shangkang.msm.R;
import com.shangkang.msm.manager.ContacterManager;
import com.shangkang.msm.manager.XmppConnectionManager;
import com.shangkang.msm.model.ChartHistory;
import com.shangkang.msm.model.User;

public class RecentChartAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ChartHistory> inviteUsers;
    private Context context;
    private OnClickListener contacterOnClick;

    public RecentChartAdapter(Context context, List<ChartHistory> inviteUsers) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.inviteUsers = inviteUsers;
    }

    public void setNoticeList(List<ChartHistory> inviteUsers) {
        this.inviteUsers = inviteUsers;
    }

    @Override
    public int getCount() {
        return inviteUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return inviteUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChartHistory notice = inviteUsers.get(position);
        Integer ppCount = notice.getNoticeSum();
        ViewHolderx holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.recent_chart_item, null);
            holder = new ViewHolderx();
            holder.newTitle = (TextView) convertView
                    .findViewById(R.id.new_title);
            holder.itemIcon = (ImageView) convertView
                    .findViewById(R.id.new_icon);
            holder.newContent = (TextView) convertView
                    .findViewById(R.id.new_content);
            holder.newDate = (TextView) convertView.findViewById(R.id.new_date);
            holder.paopao = (TextView) convertView.findViewById(R.id.paopao);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderx) convertView.getTag();
        }
        // connection.getRoster().getEntry(user)
        String jid = notice.getFrom();
        User u = ContacterManager.getNickname(jid, XmppConnectionManager
                .getInstance().getConnection());
        if (null == u) {
            u = new User();
            u.setJID(jid);
            u.setName(jid);
        }

        holder.newTitle.setText(u.getName());
        holder.itemIcon.setBackgroundResource(R.drawable.h001);
        holder.newContent.setText(notice.getContent());
        holder.newContent.setTag(u);
        holder.newDate.setText(notice.getNoticeTime().substring(5, 16));

        if (ppCount != null && ppCount > 0) {
            holder.paopao.setText(ppCount + "");
            holder.paopao.setVisibility(View.VISIBLE);

        } else {
            holder.paopao.setVisibility(View.GONE);
        }
        convertView.setOnClickListener(contacterOnClick);

        return convertView;
    }

    public class ViewHolderx {
        public ImageView itemIcon;
        public TextView newTitle;
        public TextView newContent;
        public TextView newDate;
        public TextView paopao;

    }

    /**
     * 这里用一句话描述这个方法的作用.
     *
     * @param contacterOnClick
     */
    public void setOnClickListener(OnClickListener contacterOnClick) {

        this.contacterOnClick = contacterOnClick;
    }
}