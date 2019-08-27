package com.sxjs.jd.composition.main.cartfragment;

import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.jd.MainDataManager;
import com.sxjs.jd.composition.BasePresenter;
import com.sxjs.jd.entities.CartsEntity;
import com.sxjs.jd.entities.FindsBean;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Administrator on 2019/8/21 0021.
 */

public class CartPresenter extends BasePresenter implements CartContract.Presenter {

	private CartContract.View mCartView;
	private MainDataManager mDataManager;

	@Inject
	public CartPresenter(MainDataManager mDataManager, CartContract.View view) {
		this.mDataManager = mDataManager;
		this.mCartView = view;
	}


	@Override
	public void getMyCartListData() {
		addDisposabe(mDataManager.getData(new ErrorDisposableObserver<CartsEntity>() {
			@Override
			public void onNext(CartsEntity cartsEntity) {
				mCartView.setMyCartData(cartsEntity);
			}

			@Override
			public void onComplete() {

			}
		},CartsEntity.class, "mycartData.txt"));
	}



	@Override
	public void getMyCartMoreData() {
		addDisposabe(mDataManager.getData(new DisposableObserver<CartsEntity>() {
			@Override
			public void onNext(CartsEntity cartsEntity) {
//				mCartView.setMyCartMoreData(cartsEntity);
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onComplete() {

			}
		},CartsEntity.class, "mycartData.txt"));
	}


}
