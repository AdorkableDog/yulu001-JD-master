package com.sxjs.jd.composition.main.cartfragment;


import com.sxjs.jd.entities.CartsEntity;

/**
 * Created by Administrator on 2019/8/21 0021.
 */

public interface CartContract {
	interface View {
		void setMyCartData(CartsEntity cartData);

		void setMyCartMoreData(CartsEntity cartData);
	}

	interface Presenter {
		/**
		 * 获取我的 购物车数据列表
		 */
		void getMyCartListData();

		void getMyCartMoreData();
	}

}
