package com.sxjs.common.base.baseadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/6/27 0027.
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {


	private Context mContext;
	private LayoutInflater mInflater;
	private List<T> mData = new ArrayList<T>();

	public BaseAdapter(Context mContext) {
		this.mContext = mContext;
		mInflater = LayoutInflater.from(mContext);
	}

	final public void setData(List<T> data) {
		this.mData = data;
	}

	final public List<T> getData() {
		if (mData == null) {
			throw new NullPointerException("Data type is empty, or data is not initialized.");
		}
		return mData;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public abstract int getItemViewType(int position);

	public abstract int getItemLayoutId(int getItem);

	public abstract void handleItem(int itemViewType, int position, T item, ViewHolder viewHolder, boolean isRecycle);


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int itemViewType = getItemViewType(position);
		ViewHolder viewHolder;
		boolean isRecylc = false;
		if (convertView == null) {
			convertView = mInflater.inflate(getItemLayoutId(itemViewType), null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			isRecylc = true;
		}
		handleItem(itemViewType, position, mData.get(position), viewHolder, isRecylc);
		return convertView;
	}

	public static class ViewHolder {

		View mRootView;

		SparseArray<View> mViews = new SparseArray<>();


		public ViewHolder(View mRootView) {
			this.mRootView = mRootView;
		}

		public View getView() {
			return mRootView;
		}


		public <T extends View> T get(int viewId) {
			View childView = mViews.get(viewId);
			if (childView == null) {
				childView = mRootView.findViewById(viewId);
				mViews.put(viewId, childView);
			}
			return (T) childView;
		}

		public <T extends View> T get(int viewId, Class<T> viewClas) {
			View childView = mViews.get(viewId);
			if (childView == null) {
				childView = mRootView.findViewById(viewId);
				mViews.put(viewId, childView);
			}
			return (T) childView;
		}
	}
}
