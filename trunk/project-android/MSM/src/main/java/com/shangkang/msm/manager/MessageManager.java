package com.shangkang.msm.manager;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import com.shangkang.msm.comm.Constant;
import com.shangkang.msm.db.DBManager;
import com.shangkang.msm.db.SQLiteTemplate;
import com.shangkang.msm.db.SQLiteTemplate.RowMapper;
import com.shangkang.msm.model.ChartHistory;
import com.shangkang.msm.model.IMMessage;
import com.shangkang.msm.model.Notice;
import com.shangkang.msm.util.StringUtil;

/**
 *
 * 消息历史记录，
 *
 * @author Liuzh
 */
public class MessageManager {
    private static MessageManager messageManager = null;
    private static DBManager manager = null;

    private MessageManager(Context context) {
        SharedPreferences sharedPre = context.getSharedPreferences(
                Constant.LOGIN_SET, Context.MODE_PRIVATE);
        String databaseName = sharedPre.getString(Constant.USERNAME, null);
        manager = DBManager.getInstance(context, databaseName);
    }

    public static MessageManager getInstance(Context context) {

        if (messageManager == null) {
            messageManager = new MessageManager(context);
        }

        return messageManager;
    }

    /**
     *
     * 保存消息.
     *
     * @param msg
     */
    public long saveIMMessage(IMMessage msg) {
        SQLiteTemplate st = SQLiteTemplate.getInstance(manager, false);
        ContentValues contentValues = new ContentValues();
        if (StringUtil.notEmpty(msg.getContent())) {
            contentValues.put("content", StringUtil.doEmpty(msg.getContent()));
        }
        if (StringUtil.notEmpty(msg.getFromSubJid())) {
            contentValues.put("msg_from",
                    StringUtil.doEmpty(msg.getFromSubJid()));
        }
        contentValues.put("msg_type", msg.getMsgType());
        contentValues.put("msg_time", msg.getTime());
        return st.insert("im_msg_his", contentValues);
    }

    /**
     *
     * 更新状态.
     *
     * @param status
     */
    public void updateStatus(String id, Integer status) {
        SQLiteTemplate st = SQLiteTemplate.getInstance(manager, false);
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", status);
        st.updateById("im_msg_his", id, contentValues);
    }

    /**
     *
     * 查找与某人的聊天记录聊天记录
     *
     * @param pageNum
     *            第几页
     * @param pageSize
     *            要查的记录条数
     * @return
     */
    public List<IMMessage> getMessageListByFrom(String fromUser, int pageNum,
                                                int pageSize) {
        if (StringUtil.empty(fromUser)) {
            return null;
        }
        int fromIndex = (pageNum - 1) * pageSize;
        SQLiteTemplate st = SQLiteTemplate.getInstance(manager, false);
        List<IMMessage> list = st.queryForList(
                new RowMapper<IMMessage>() {
                    @Override
                    public IMMessage mapRow(Cursor cursor, int index) {
                        IMMessage msg = new IMMessage();
                        msg.setContent(cursor.getString(cursor
                                .getColumnIndex("content")));
                        msg.setFromSubJid(cursor.getString(cursor
                                .getColumnIndex("msg_from")));
                        msg.setMsgType(cursor.getInt(cursor
                                .getColumnIndex("msg_type")));
                        msg.setTime(cursor.getString(cursor
                                .getColumnIndex("msg_time")));
                        return msg;
                    }
                },
                "select content,msg_from, msg_type,msg_time from im_msg_his where msg_from=? order by msg_time desc limit ? , ? ",
                new String[] { "" + fromUser, "" + fromIndex, "" + pageSize });
        return list;

    }

    /**
     *
     * 查找与某人的聊天记录总数
     *
     * @return
     */
    public int getChatCountWithSb(String fromUser) {
        if (StringUtil.empty(fromUser)) {
            return 0;
        }
        SQLiteTemplate st = SQLiteTemplate.getInstance(manager, false);
        return st
                .getCount(
                        "select _id,content,msg_from msg_type  from im_msg_his where msg_from=?",
                        new String[] { "" + fromUser });

    }

    /**
     * 删除与某人的聊天记录 author Liuzh
     *
     * @param fromUser
     */
    public int delChatHisWithSb(String fromUser) {
        if (StringUtil.empty(fromUser)) {
            return 0;
        }
        SQLiteTemplate st = SQLiteTemplate.getInstance(manager, false);
        return st.deleteByCondition("im_msg_his", "msg_from=?",
                new String[] { "" + fromUser });
    }

    /**
     *
     * 获取最近聊天人聊天最后一条消息和未读消息总数
     *
     * @return
     */
    public List<ChartHistory> getRecentContactsWithLastMsg() {
        SQLiteTemplate st = SQLiteTemplate.getInstance(manager, false);
        List<ChartHistory> list = st
                .queryForList(
                        new RowMapper<ChartHistory>() {

                            @Override
                            public ChartHistory mapRow(Cursor cursor, int index) {
                                ChartHistory notice = new ChartHistory();
                                notice.setId(cursor.getString(cursor
                                        .getColumnIndex("_id")));
                                notice.setContent(cursor.getString(cursor
                                        .getColumnIndex("content")));
                                notice.setFrom(cursor.getString(cursor
                                        .getColumnIndex("msg_from")));
                                notice.setNoticeTime(cursor.getString(cursor
                                        .getColumnIndex("msg_time")));
                                return notice;
                            }
                        },
                        "select m.[_id],m.[content],m.[msg_time],m.msg_from from im_msg_his  m join (select msg_from,max(msg_time) as time from im_msg_his group by msg_from) as tem  on  tem.time=m.msg_time and tem.msg_from=m.msg_from ",
                        null);
        for (ChartHistory b : list) {
            int count = st
                    .getCount(
                            "select _id from im_notice where status=? and type=? and notice_from=?",
                            new String[] { "" + Notice.UNREAD,
                                    "" + Notice.CHAT_MSG, b.getFrom() });
            b.setNoticeSum(count);
        }
        return list;
    }

}