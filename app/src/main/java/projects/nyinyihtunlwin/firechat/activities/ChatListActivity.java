package projects.nyinyihtunlwin.firechat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.firechat.R;
import projects.nyinyihtunlwin.firechat.adapters.ChatListAdapter;
import projects.nyinyihtunlwin.firechat.components.EmptyViewPod;
import projects.nyinyihtunlwin.firechat.components.SmartRecyclerView;
import projects.nyinyihtunlwin.firechat.components.SmartScrollListener;
import projects.nyinyihtunlwin.firechat.data.models.FireChatModel;
import projects.nyinyihtunlwin.firechat.dialogs.SignInDialog;
import projects.nyinyihtunlwin.firechat.events.RestApiEvents;
import projects.nyinyihtunlwin.firechat.events.TapChatEvent;

public class ChatListActivity extends BaseActivity {

    @BindView(R.id.rv_chat_list)
    SmartRecyclerView rvChatList;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private SmartScrollListener mSmartScrollListener;

    private ChatListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ButterKnife.bind(this, this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        rvChatList.setEmptyView(vpEmpty);
        rvChatList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatListAdapter(getApplicationContext());
        rvChatList.setAdapter(adapter);
        rvChatList.setHasFixedSize(true);

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReached() {

            }
        });
        rvChatList.addOnScrollListener(mSmartScrollListener);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChatListDataLoaded(RestApiEvents.UserDataLoadedEvent event) {
        adapter.appendNewData(event.getLoadedUser());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTapConversation(TapChatEvent event) {
  /*      Intent intent = ConversationActivity.newIntent(getApplicationContext(), event.getConversationId());
        startActivity(intent);*/
        SignInDialog dialog = new SignInDialog();
        dialog.show(getSupportFragmentManager(), "Authenticate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        if (FireChatModel.getInstance().getmChatList() != null && FireChatModel.getInstance().getmChatList().size() > 0) {
            adapter.appendNewData(FireChatModel.getInstance().getmUserList());
        } else {
            swipeRefreshLayout.setRefreshing(true);
            FireChatModel.getInstance().startLoadingRegisteredUser(getApplicationContext());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
