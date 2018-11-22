package com.feng.digitacoin.ui.components;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/9/26--下午3:25
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
public abstract class BinderViewHolder<T> extends RecyclerView.ViewHolder {
	public BinderViewHolder(int resId, ViewGroup parent) {
		super(LayoutInflater.from(parent.getContext())
				.inflate(resId, parent, false));
	}

	public abstract void bind(@Nullable T data, @NonNull Bundle addition);

	public void bind(@Nullable T data) {
		bind(data, Bundle.EMPTY);
	}

	protected <T extends View> T findViewById(int id) {
		return itemView.findViewById(id);
	}

	protected Context getContext() {
		return itemView.getContext();
	}

	protected String getString(int stringResId) {
		return getContext().getString(stringResId);
	}


	public String getString(int stringResId, Object... args) {
		return getContext().getString(stringResId, args);
	}
}
