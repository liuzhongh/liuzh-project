package com.shangkang.msm.activity.im;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shangkang.msm.R;
import com.shangkang.msm.activity.LoginActivity;
import com.shangkang.msm.comm.Constant;
import com.shangkang.msm.manager.ContacterManager;
import com.shangkang.msm.manager.ContacterManager.MRosterGroup;
import com.shangkang.msm.manager.MessageManager;
import com.shangkang.msm.manager.NoticeManager;
import com.shangkang.msm.manager.XmppConnectionManager;
import com.shangkang.msm.model.ChartHistory;
import com.shangkang.msm.model.Notice;
import com.shangkang.msm.model.User;
import com.shangkang.msm.util.StringUtil;
import com.shangkang.msm.view.ContacterExpandAdapter;
import com.shangkang.msm.view.LayoutChangeListener;
import com.shangkang.msm.view.RecentChartAdapter;
import com.shangkang.msm.view.ScrollLayout;

public class ContacterMainActivity extends AContacterActivity implements
        LayoutChangeListener, OnClickListener {
    private final static String TAG = "ContacterMainActivity";
    private LayoutInflater inflater;
    private ScrollLayout layout;
    private ImageView imageView;
    private ImageView tab1;
    private ImageView tab2;
    private ImageView tab3;
    private ExpandableListView contacterList = null;
    private ContacterExpandAdapter expandAdapter = null;
    private ListView inviteList = null;
    private RecentChartAdapter noticeAdapter = null;
    private List<ChartHistory> inviteNotices = new ArrayList<ChartHistory>();
    private ImageView headIcon;
    private TextView noticePaopao;
    private List<String> groupNames;
    private List<String> newNames = new ArrayList<String>();
    private List<MRosterGroup> rGroups;
    private ImageView iv_status;
    private User clickUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacter_main);
        init();
    }

    private void init() {
        try {
            groupNames = ContacterManager.getGroupNames(XmppConnectionManager
                    .getInstance().getConnection().getRoster());
            rGroups = ContacterManager.getGroups(XmppConnectionManager
                    .getInstance().getConnection().getRoster());
        } catch (Exception e) {
            groupNames = new ArrayList<String>();
            rGroups = new ArrayList<MRosterGroup>();
        }
        iv_status = (ImageView) findViewById(R.id.imageView1);
        getEimApplication().addActivity(this);
        inflater = LayoutInflater.from(context);
        layout = (ScrollLayout) findViewById(R.id.scrolllayout);
        layout.addChangeListener(this);
        tab1 = (ImageView) findViewById(R.id.tab1);
        tab2 = (ImageView) findViewById(R.id.tab2);
        tab3 = (ImageView) findViewById(R.id.tab3);
        noticePaopao = (TextView) findViewById(R.id.notice_paopao);

        imageView = (ImageView) findViewById(R.id.top_bar_select);

        View contacterTab1 = inflater.inflate(R.layout.contacter_tab1, null);
        View contacterTab2 = inflater.inflate(R.layout.contacter_tab2, null);
        View contacterTab3 = inflater.inflate(R.layout.contacter_tab3, null);
        layout.addView(contacterTab1);
        layout.addView(contacterTab2);
        layout.addView(contacterTab3);
        layout.setToScreen(1);

        contacterList = (ExpandableListView) findViewById(R.id.main_expand_list);
        ImageView titleBack = (ImageView) findViewById(R.id.title_back);
        headIcon = (ImageView) findViewById(R.id.head_icon);
        headIcon.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, UserInfoActivity.class);
                startActivity(intent);
            }
        });
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 联系人
        expandAdapter = new ContacterExpandAdapter(context, rGroups);
        contacterList.setAdapter(expandAdapter);
        contacterList
                .setOnCreateContextMenuListener(onCreateContextMenuListener);
        contacterList.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                createChat((User) v.findViewById(R.id.username).getTag());
                return false;
            }
        });
        // 未读信息
        inviteList = (ListView) findViewById(R.id.main_invite_list);
        inviteNotices = MessageManager.getInstance(context)
                .getRecentContactsWithLastMsg();
        noticeAdapter = new RecentChartAdapter(context, inviteNotices);
        inviteList.setAdapter(noticeAdapter);
        noticeAdapter.setOnClickListener(contacterOnClickJ);

    }

    /**
     * 有新消息进来
     */
    @Override
    protected void msgReceive(Notice notice) {
        for (ChartHistory ch : inviteNotices) {
            if (ch.getFrom().equals(notice.getFrom())) {
                ch.setContent(notice.getContent());
                ch.setNoticeTime(notice.getNoticeTime());
                Integer x = ch.getNoticeSum() == null ? 0 : ch.getNoticeSum();
                ch.setNoticeSum(x + 1);
            }
        }
        noticeAdapter.setNoticeList(inviteNotices);
        noticeAdapter.notifyDataSetChanged();
        setPaoPao();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
        if (getUserOnlineState()) {
            iv_status.setImageDrawable(getResources().getDrawable(
                    R.drawable.status_online));
        } else {
            iv_status.setImageDrawable(getResources().getDrawable(
                    R.drawable.status_offline));
        }

    }

    /**
     * 通知点击
     */
    private OnClickListener contacterOnClickJ = new OnClickListener() {

        @Override
        public void onClick(View v) {
            createChat((User) v.findViewById(R.id.new_content).getTag());

        }
    };

    /**
     * 设置昵称
     *
     * @param user
     */
    private void setNickname(final User user) {
        final EditText name_input = new EditText(context);
        name_input.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        name_input.setHint("输入昵称");
        new AlertDialog.Builder(context).setTitle("修改昵称").setView(name_input)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = name_input.getText().toString();
                        if (!"".equals(name))
                            setNickname(user, name);
                    }
                }).setNegativeButton("取消", null).show();
    }

    /**
     * 添加好友
     */
    private void addSubscriber() {
        final EditText name_input = new EditText(context);
        name_input.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        name_input.setHint(getResources().getString(R.string.input_username));
        final EditText nickname = new EditText(context);
        nickname.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        nickname.setHint(getResources().getString(R.string.set_nickname));
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        layout.addView(name_input);
        layout.addView(nickname);
        new AlertDialog.Builder(context).setTitle("添加好友").setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userName = name_input.getText().toString();
                        String nickname_in = nickname.getText().toString();
                        if (StringUtil.empty(userName)) {
                            showToast(getResources().getString(
                                    R.string.username_not_null));
                            return;
                        }
                        userName = StringUtil.doEmpty(userName);
                        if (StringUtil.empty(nickname_in)) {
                            nickname_in = null;
                        }

                        if (isExitJid(StringUtil.getJidByName(userName),
                                rGroups)) {
                            showToast(getResources().getString(
                                    R.string.username_exist));
                            return;
                        }
                        try {
                            createSubscriber(StringUtil.getJidByName(userName),
                                    nickname_in, null);
                        } catch (XMPPException e) {
                        }
                    }
                }).setNegativeButton("取消", null).show();
    }

    /**
     * 加入组
     *
     * @param user
     */
    private void addToGroup(final User user) {
        LayoutInflater inflaterx = LayoutInflater.from(context);
        View dialogView = inflaterx.inflate(R.layout.yd_group_dialog, null);
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.list);
        ArrayAdapter<String> ada = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item, groupNames);
        spinner.setAdapter(ada);

        new AlertDialog.Builder(context).setTitle("移动" + "至分组")
                .setView(dialogView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String groupName = (spinner.getSelectedItem())
                                .toString();
                        if (StringUtil.notEmpty(groupName)) {
                            groupName = StringUtil.doEmpty(groupName);
                            if (newNames.contains(groupName)) {
                                newNames.remove(groupName);
                            }
                            // UI级把用户移到某组
                            addUserGroupUI(user, groupName);

                            // api移入组
                            addUserToGroup(user, groupName);
                        }
                    }
                }).setNegativeButton("取消", null).show();
    }

    /**
     * 修改组名
     *
     * @param groupName
     */
    private void updateGroupNameA(final String groupName) {
        final EditText name_input = new EditText(context);
        name_input.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        name_input.setHint("输入组名");
        new AlertDialog.Builder(context).setTitle("修改组名").setView(name_input)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String gNewName = name_input.getText().toString();
                        if (newNames.contains(gNewName)
                                || groupNames.contains(gNewName)) {
                            showToast("组名已存在");
                            return;
                        }
                        // UI级修改操作
                        updateGroupNameUI(groupName, gNewName);
                        // UIAPI
                        updateGroupName(groupName, gNewName);
                    }
                }).setNegativeButton("取消", null).show();
    }

    private OnItemClickListener inviteListClick = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int arg2,
                                long arg3) {
            final Notice notice = (Notice) view.findViewById(R.id.new_content)
                    .getTag();
            if (notice.getNoticeType() == Notice.CHAT_MSG) {
                User user = new User();
                user.setJID("admin@zkost.com");
                createChat(user);
            } else {
                final String subFrom = notice.getFrom();
                new AlertDialog.Builder(context)
                        .setMessage(subFrom + "请求添加您为好友")
                        .setTitle("提示")
                        .setPositiveButton("添加",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // 接受请求
                                        sendSubscribe(Presence.Type.subscribed,
                                                subFrom);
                                        sendSubscribe(Presence.Type.subscribe,
                                                subFrom);
                                        refreshList();

                                    }
                                })
                        .setNegativeButton("拒绝",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        sendSubscribe(
                                                Presence.Type.unsubscribe,
                                                subFrom);
                                    }
                                }).show();
            }

        }
    };

    /**
     * 刷新当前的列表
     */
    private void refreshList() {
        rGroups = ContacterManager.getGroups(XmppConnectionManager
                .getInstance().getConnection().getRoster());
        for (String newGroupName : newNames) {
            MRosterGroup mg = new MRosterGroup(newGroupName,
                    new ArrayList<User>());
            rGroups.add(rGroups.size() - 1, mg);
        }
        expandAdapter.setContacter(rGroups);
        expandAdapter.notifyDataSetChanged();

        // 刷新notice信息
        inviteNotices = MessageManager.getInstance(context)
                .getRecentContactsWithLastMsg();
        noticeAdapter.setNoticeList(inviteNotices);
        noticeAdapter.notifyDataSetChanged();
        /**
         * 有新消息进来的气泡设置
         */
        setPaoPao();

    }

    @Override
    protected void addUserReceive(User user) {
        refreshList();
    }

    @Override
    protected void deleteUserReceive(User user) {
        if (user == null)
            return;
        Toast.makeText(
                context,
                (user.getName() == null) ? user.getJID() : user.getName()
                        + "被删除了", Toast.LENGTH_SHORT).show();
        refreshList();
    }

    @Override
    protected void changePresenceReceive(User user) {
        if (user == null)
            return;
        if (ContacterManager.contacters.get(user.getJID()) == null)
            return;
        // 下线
        if (!user.isAvailable())
            if (ContacterManager.contacters.get(user.getJID()).isAvailable())
                Toast.makeText(
                        context,
                        (user.getName() == null) ? user.getJID() : user
                                .getName() + "上线了", Toast.LENGTH_SHORT).show();
        // 上线
        if (user.isAvailable())
            if (!ContacterManager.contacters.get(user.getJID()).isAvailable())
                Toast.makeText(
                        context,
                        (user.getName() == null) ? user.getJID() : user
                                .getName() + "下线了", Toast.LENGTH_SHORT).show();
        refreshList();
    }

    @Override
    protected void updateUserReceive(User user) {
        refreshList();
    }

    @Override
    protected void subscripUserReceive(final String subFrom) {
        Notice notice = new Notice();
        notice.setFrom(subFrom);
        notice.setNoticeType(Notice.CHAT_MSG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contacter_menu, menu);
        return true;
    }

    /**
     * 修改状态
     */
    private void modifyState() {
        String[] states = new String[] { "在线", "隐身", "吃饭", "睡觉" };
        new AlertDialog.Builder(this)
                .setItems(states, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Presence presence = new Presence(
                                Presence.Type.available);
                        switch (which) {
                            case 0:
                                break;
                            case 1:
                                presence.setType(Presence.Type.unavailable);
                                break;
                            case 2:
                                presence.setStatus("吃饭");
                                break;
                            case 3:
                                presence.setStatus("睡觉");
                                break;
                        }
                        XmppConnectionManager.getInstance().getConnection()
                                .sendPacket(presence);
                    }
                }).setPositiveButton("取消", null).setTitle("修改状态").show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.menu_add_subscriber:
                addSubscriber();
                break;
            case R.id.menu_modify_state:
                modifyState();
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doChange(int lastIndex, int currentIndex) {
        if (lastIndex != currentIndex) {
            TranslateAnimation animation = null;
            LinearLayout layout = null;
            switch (currentIndex) {
                case 0:
                    if (lastIndex == 1) {
                        layout = (LinearLayout) tab1.getParent();
                        animation = new TranslateAnimation(0, -layout.getWidth(),
                                0, 0);
                    } else if (lastIndex == 2) {
                        layout = (LinearLayout) tab2.getParent();
                        animation = new TranslateAnimation(layout.getLeft(),
                                -((LinearLayout) tab1.getParent()).getWidth(), 0, 0);
                    }
                    break;
                case 1:
                    if (lastIndex < 1) {
                        // 左到中
                        layout = (LinearLayout) tab1.getParent();
                        animation = new TranslateAnimation(-layout.getWidth(), 0,
                                0, 0);
                    } else if (lastIndex > 1) {
                        // 右到中
                        layout = (LinearLayout) tab2.getParent();
                        animation = new TranslateAnimation(layout.getLeft(), 0, 0,
                                0);
                    }
                    break;
                case 2:
                    if (lastIndex == 1) {
                        layout = (LinearLayout) tab2.getParent();
                        animation = new TranslateAnimation(0, layout.getLeft(), 0,
                                0);
                    } else if (lastIndex == 0) {
                        layout = (LinearLayout) tab2.getParent();
                        animation = new TranslateAnimation(
                                -((LinearLayout) tab1.getParent()).getWidth(),
                                layout.getLeft(), 0, 0);
                    }
                    break;
            }
            animation.setDuration(300);
            animation.setFillAfter(true);
            imageView.startAnimation(animation);
        }
    }

    @Override
    public void onClick(View v) {

        if (v == tab1) {
            layout.snapToScreen(0);

        } else if (v == tab2) {
            layout.snapToScreen(1);

        } else if (v == tab3) {
            layout.snapToScreen(2);

        }
    }

    /**
     * 上面滚动条上的气泡设置 有新消息来的通知气泡，数量设置,
     */
    private void setPaoPao() {
        if (null != inviteNotices && inviteNotices.size() > 0) {
            int paoCount = 0;
            for (ChartHistory c : inviteNotices) {
                Integer countx = c.getNoticeSum();
                paoCount += (countx == null ? 0 : countx);
            }
            if (paoCount == 0) {
                noticePaopao.setVisibility(View.GONE);
                return;
            }
            noticePaopao.setText(paoCount + "");
            noticePaopao.setVisibility(View.VISIBLE);
        } else {
            noticePaopao.setVisibility(View.GONE);
        }
    }

    /**
     * 加入组
     *
     */
    private void addNewGroup() {
        final EditText name_input = new EditText(context);
        name_input.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        name_input.setHint("输入组名");
        new AlertDialog.Builder(context).setTitle("加入组").setView(name_input)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String groupName = name_input.getText().toString();
                        if (StringUtil.empty(groupName)) {
                            // showToast("组名不能为空");
                            return;
                        }
                        // ui上增加数据
                        if (groupNames.contains(groupName)) {
                            // showToast("组名已经存在");
                            return;
                        }
                        addGroupNamesUi(groupName);

                    }
                }).setNegativeButton("取消", null).show();
    }

    OnCreateContextMenuListener onCreateContextMenuListener = new OnCreateContextMenuListener() {
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenuInfo menuInfo) {

            ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

            // 类型，0代表是group类，1代表是child类
            int type = ExpandableListView
                    .getPackedPositionType(info.packedPosition);

            if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                int gId = ExpandableListView
                        .getPackedPositionGroup(info.packedPosition);
                String[] longClickItems = null;
                final String groupName = rGroups.get(gId).getName();
                if (StringUtil.notEmpty(groupName)
                        && !Constant.ALL_FRIEND.equals(groupName)
                        && !Constant.NO_GROUP_FRIEND.equals(groupName)) {
                    longClickItems = new String[] { "添加分组", "更改组名", };
                } else {
                    longClickItems = new String[] { "添加分组" };

                }
                new AlertDialog.Builder(context)
                        .setItems(longClickItems,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (which) {
                                            case 0:// 添加分组
                                                addNewGroup();
                                                break;

                                            case 1:// 更改组名
                                                updateGroupNameA(groupName);
                                                break;
                                        }
                                    }
                                }).setTitle("选项").show();
            } else if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

                String[] longClickItems = null;

                View vx = info.targetView;
                clickUser = (User) vx.findViewById(R.id.username).getTag();
                showToast(clickUser.getJID() + "---");

                if (StringUtil.notEmpty(clickUser.getGroupName())
                        && !Constant.ALL_FRIEND
                        .equals(clickUser.getGroupName())
                        && !Constant.NO_GROUP_FRIEND.equals(clickUser
                        .getGroupName())) {
                    longClickItems = new String[] { "设置昵称", "添加好友", "删除好友",
                            "移动到分组", "退出该组" };
                } else {
                    longClickItems = new String[] { "设置昵称", "添加好友", "删除好友",
                            "移动到分组" };
                }
                new AlertDialog.Builder(context)
                        .setItems(longClickItems,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (which) {
                                            case 0:// 设置昵称
                                                setNickname(clickUser);
                                                break;
                                            case 1:// 添加好友
                                                addSubscriber();
                                                break;
                                            case 2:// 删除好友

                                                showDeleteDialog(clickUser);

                                                break;
                                            case 3:// 移动到分组 （1.先移除本组，2移入某组）
                                                /**
                                                 * ui移除old组
                                                 */
                                                removeUserFromGroupUI(clickUser);

                                                removeUserFromGroup(clickUser,
                                                        clickUser.getGroupName());
                                                addToGroup(clickUser);

                                                break;

                                            case 4:// 移出组
                                                /**
                                                 * ui移除old组
                                                 */
                                                removeUserFromGroupUI(clickUser);
                                                /**
                                                 * api级出某组
                                                 */
                                                removeUserFromGroup(clickUser,
                                                        clickUser.getGroupName());
                                                break;
                                        }
                                    }

                                }).setTitle("选项").show();
            }
        }

    };

    public void delayRefresh() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                // TODO Auto-generated method stub
                refreshList();

            }
        });
    }

    /**
     * UI级添加分组 这里用一句话描述这个方法的作用.
     *
     * @author Liuzh
     */
    public void addGroupNamesUi(String newGroupName) {
        groupNames.add(newGroupName);
        newNames.add(newGroupName);
        MRosterGroup mg = new MRosterGroup(newGroupName, new ArrayList<User>());
        rGroups.add(rGroups.size() - 1, mg);
        // 刷新用户信息
        expandAdapter.setContacter(rGroups);
        expandAdapter.notifyDataSetChanged();
    }

    /**
     * UI级删除用户
     */
    private void deleteUserUI(User user) {
        for (MRosterGroup g : rGroups) {
            List<User> us = g.getUsers();
            if (us != null && us.size() > 0) {
                if (us.contains(user)) {
                    us.remove(user);
                    g.setUsers(us);
                }
            }
        }
        expandAdapter.setContacter(rGroups);
        expandAdapter.notifyDataSetChanged();

    }

    /**
     * UI级移动用户，把用户移除某组
     */

    private void removeUserFromGroupUI(User user) {

        for (MRosterGroup g : rGroups) {
            if (g.getUsers().contains(user)) {
                if (StringUtil.notEmpty(g.getName())
                        && !Constant.ALL_FRIEND.equals(g.getName())) {
                    List<User> users = g.getUsers();
                    users.remove(user);
                    g.setUsers(users);

                }
            }
        }
        expandAdapter.setContacter(rGroups);
        expandAdapter.notifyDataSetChanged();
    }

    /**
     * UI级移动用户，把用户加入某组
     */

    private void addUserGroupUI(User user, String groupName) {
        for (MRosterGroup g : rGroups) {
            if (groupName.equals(g.getName())) {
                List<User> users = g.getUsers();
                users.add(user);
                g.setUsers(users);
            }
        }
        expandAdapter.setContacter(rGroups);
        expandAdapter.notifyDataSetChanged();

    }

    /**
     * UI更改组名
     */

    private void updateGroupNameUI(String old, String newGroupName) {

        if (StringUtil.empty(old) || Constant.ALL_FRIEND.equals(old)
                || Constant.NO_GROUP_FRIEND.equals(old)) {
            return;
        }
        // 虽然没必要，但是如果输入忘记限制
        if (StringUtil.empty(newGroupName)
                || Constant.ALL_FRIEND.equals(newGroupName)
                || Constant.NO_GROUP_FRIEND.equals(newGroupName)) {
            return;
        }

        // 要修改的组名是新添加的但是没有添加到服务器端的，只是ui级添加的，如下操作
        if (newNames.contains(old)) {
            newNames.remove(old);
            newNames.add(newGroupName);
            return;
        }
        // 列表修改;
        for (MRosterGroup g : rGroups) {
            if (g.getName().equals(old)) {
                g.setName(newGroupName);
            }
        }
        expandAdapter.notifyDataSetChanged();

    }

    /**
     * 删除用户
     *
     * @param clickUser
     */
    private void showDeleteDialog(final User clickUser) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(
                getResources().getString(R.string.delete_user_confim))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                // ui删除
                                deleteUserUI(clickUser);
                                // api删除
                                try {
                                    removeSubscriber(clickUser.getJID());
                                } catch (XMPPException e) {
                                    Log.e(TAG, "", e);
                                }
                                // 删除数据库
                                NoticeManager.getInstance(context)
                                        .delNoticeHisWithSb(clickUser.getJID());
                                MessageManager.getInstance(context)
                                        .delChatHisWithSb(clickUser.getJID());
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    protected void handReConnect(boolean isSuccess) {
        // 成功了连接
        if (Constant.RECONNECT_STATE_SUCCESS == isSuccess) {
            iv_status.setImageDrawable(getResources().getDrawable(
                    R.drawable.status_online));

        } else if (Constant.RECONNECT_STATE_FAIL == isSuccess) {// 失败
            iv_status.setImageDrawable(getResources().getDrawable(
                    R.drawable.status_offline));
        }
    }

}