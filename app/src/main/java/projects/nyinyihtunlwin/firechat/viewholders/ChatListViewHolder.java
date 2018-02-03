package projects.nyinyihtunlwin.firechat.viewholders;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import projects.nyinyihtunlwin.firechat.R;
import projects.nyinyihtunlwin.firechat.data.vo.ChatVO;
import projects.nyinyihtunlwin.firechat.data.vo.UserVO;
import projects.nyinyihtunlwin.firechat.events.RestApiEvents;
import projects.nyinyihtunlwin.firechat.events.TapChatEvent;

/**
 * Created by Dell on 1/30/2018.
 */

public class ChatListViewHolder extends BaseViewHolder<UserVO> implements View.OnClickListener {

    @BindView(R.id.iv_friend)
    CircleImageView ivFriend;

    @BindView(R.id.tv_friend_name)
    TextView tvFriendName;

    @BindView(R.id.tv_conversations)
    TextView tvConversation;

    UserVO mData;

    public ChatListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void setData(UserVO mData) {
        this.mData = mData;
        tvFriendName.setText(mData.getName());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop();
        Glide.with(itemView.getRootView().getContext()).load(mData.getProfileUrl()).apply(requestOptions).into(ivFriend);

    }

    @Override
    public void onClick(View view) {
        EventBus.getDefault().post(new TapChatEvent(mData.getUserId()));
    }
}
