package com.feng.digitacoin.ui.components;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feng.digitacoin.R;


/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/21--12:11 PM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
public class DateAdapter extends RecyclerView.Adapter<BinderViewHolder> {

    private SortedList<String> dataSource;

    public DateAdapter(SortedList<String> data) {
        this.dataSource = data == null ? new SortedList<String>(String.class, null) : data;
    }

    @NonNull
    @Override
    public BinderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(R.layout.item_date, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BinderViewHolder holder, int position) {
        holder.bind(dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(SortedList<String> data) {
        this.dataSource = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends BinderViewHolder {
        TextView textView;

        public ViewHolder(int resId, ViewGroup parent) {
            super(resId, parent);
            textView = (TextView) findViewById(R.id.text);
        }

        @Override
        public void bind(@Nullable Object data, @NonNull Bundle addition) {
            textView.setText(data.toString());
        }
    }
}
