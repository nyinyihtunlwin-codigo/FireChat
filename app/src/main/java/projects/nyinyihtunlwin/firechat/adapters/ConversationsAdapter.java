package projects.nyinyihtunlwin.firechat.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.firechat.R;
import projects.nyinyihtunlwin.firechat.data.vo.ConversationVO;
import projects.nyinyihtunlwin.firechat.viewholders.BaseViewHolder;
import projects.nyinyihtunlwin.firechat.viewholders.ConversationsViewHolder;

/**
 * Created by Dell on 2/3/2018.
 */

public class ConversationsAdapter extends BaseAdapter<ConversationsViewHolder, ConversationVO> {
    public ConversationsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_chat_data, parent, false);
        return new ConversationsViewHolder(view);
    }
}
