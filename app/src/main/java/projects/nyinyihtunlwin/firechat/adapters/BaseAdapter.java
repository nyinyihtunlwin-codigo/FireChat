package projects.nyinyihtunlwin.firechat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.firechat.viewholders.BaseViewHolder;

/**
 * Created by Dell on 1/30/2018.
 */

public abstract class BaseAdapter<D extends BaseViewHolder, T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mData;
    protected LayoutInflater mLayoutInflater;

    public BaseAdapter(Context context) {
        mData = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<T> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    public void appendNewData(List<T> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public T getItemAt(int position) {
        if (position < mData.size() - 1)
            return mData.get(position);

        return null;
    }

    public List<T> getItems() {
        if (mData == null)
            return new ArrayList<>();

        return mData;
    }

    public void removeData(T data) {
        mData.remove(data);
        notifyDataSetChanged();
    }

    public void addNewData(T data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData = new ArrayList<>();
        notifyDataSetChanged();
    }
}
