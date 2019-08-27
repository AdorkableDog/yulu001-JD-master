package com.sxjs.jd.composition.main.cartfragment;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.jd.entities.CartsEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/26 0026.
 */

public class CartGoodsDetailAdapter extends BaseQuickAdapter<CartsEntity.DataBean.ProCartsBean.ShopProArrayBean, BaseViewHolder> {


	public CartGoodsDetailAdapter(int layoutResId, List<CartsEntity.DataBean.ProCartsBean.ShopProArrayBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, CartsEntity.DataBean.ProCartsBean.ShopProArrayBean item, int position) {

	}



}
