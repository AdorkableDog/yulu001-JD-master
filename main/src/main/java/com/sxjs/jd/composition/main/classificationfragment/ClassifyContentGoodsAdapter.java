package com.sxjs.jd.composition.main.classificationfragment;

import android.content.Context;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sxjs.common.base.baseadapter.BaseAdapter;
import com.sxjs.jd.R;
import com.sxjs.jd.entities.ClassIndex;

/**
 * Created by Suo on 2017/6/6.
 */

public class ClassifyContentGoodsAdapter extends BaseAdapter {

	public ClassifyContentGoodsAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public int getItemLayoutId(int getItem) {
		return R.layout.item_classify_content_goods;
	}

	@Override
	public void handleItem(int itemViewType, int position, Object data, ViewHolder viewHolder, boolean isRecycle) {
		TextView tvTitle = viewHolder.get(R.id.classify_content_goods_tv, TextView.class);
		SimpleDraweeView ivGoods = viewHolder.get(R.id.classify_content_goods_iv, SimpleDraweeView.class);
		ClassIndex.DataBean.ClassListBean.ProductClassifysBean datas = (ClassIndex.DataBean.ClassListBean.ProductClassifysBean) data;
		tvTitle.setText(datas.classify_name);
		ivGoods.setImageURI(datas.classify_img);
	}
}