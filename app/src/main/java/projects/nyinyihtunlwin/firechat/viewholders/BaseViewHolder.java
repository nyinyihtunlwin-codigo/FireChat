package projects.nyinyihtunlwin.firechat.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dell on 1/30/2018.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    T mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(T mData);
}
