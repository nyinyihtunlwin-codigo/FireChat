package projects.nyinyihtunlwin.firechat.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.firechat.R;
import projects.nyinyihtunlwin.firechat.data.vo.ChatVO;
import projects.nyinyihtunlwin.firechat.data.vo.UserVO;
import projects.nyinyihtunlwin.firechat.viewholders.BaseViewHolder;
import projects.nyinyihtunlwin.firechat.viewholders.ChatListViewHolder;

/**
 * Created by Dell on 1/30/2018.
 */

public class ChatListAdapter extends BaseAdapter<ChatListViewHolder, UserVO> {
    public ChatListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_chat_list, parent, false);
        return new ChatListViewHolder(view);
    }

}
