package com.sxjs.jd.composition.main.cartfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.jd.R;
import com.sxjs.jd.entities.CartsEntity;
import com.sxjs.jd.entities.FindsBean;

/**
 * Created by admin on 2017/3/22.
 */

public class CartsStoreListAdapter extends BaseQuickAdapter<CartsEntity.DataBean.ProCartsBean, BaseViewHolder> {

	public CartsStoreListAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(BaseViewHolder helper, final CartsEntity.DataBean.ProCartsBean bean, int position) {

		((RecyclerView) helper.getView(R.id.gridView)).setLayoutManager(
				new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
		CartGoodsDetailAdapter cartGoodsDetailAdapter = new
				CartGoodsDetailAdapter(
				R.layout.item_cart_recyclerview_item,
				bean.getShopProArray());
		((RecyclerView) helper.getView(R.id.gridView)).setAdapter(cartGoodsDetailAdapter);

//        helper.setText(R.id.author_name , bean.authorName);
//        helper.setText(R.id.time_text , bean.showTime);
//        helper.setText(R.id.page_view_count , ""+bean.pageView);
//        SimpleDraweeView simpleDraweeView = helper.getView(R.id.content_img);
//        SimpleDraweeView authorImg = helper.getView(R.id.author_img);
//        simpleDraweeView.setImageURI(bean.indexImage);
//        authorImg.setImageURI(bean.authorPic);
		helper.addOnClickListener(R.id.find_item_layout);
		setOnItemChildClickListener(new OnItemChildClickListener() {
			@Override
			public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
				Toast.makeText(mContext, "第" + position + "页 ", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}


}
