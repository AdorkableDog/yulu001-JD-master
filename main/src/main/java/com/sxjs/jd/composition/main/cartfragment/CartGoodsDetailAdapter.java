package com.sxjs.jd.composition.main.cartfragment;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.sxjs.jd.entities.CartsEntity;

import java.util.List;

import com.sxjs.jd.R;

/**
 * Created by Administrator on 2019/8/26 0026.
 */

public class CartGoodsDetailAdapter extends BaseQuickAdapter<CartsEntity.DataBean.ProCartsBean.ShopProArrayBean, BaseViewHolder> {


	public CartGoodsDetailAdapter(int layoutResId, List<CartsEntity.DataBean.ProCartsBean.ShopProArrayBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, CartsEntity.DataBean.ProCartsBean.ShopProArrayBean item, int position) {
		((ExpandImageView) helper.getView(R.id.expand_img)).setImageURI(item.getProImg());
		helper.setText(R.id.tv_store_title, item.getProName());
		helper.setText(R.id.tv_store_standard, item.getSkuPropertiesName());
		helper.setText(R.id.tv_store_pics, item.getSkuPrice());

	}


}
