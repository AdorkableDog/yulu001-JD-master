package com.sxjs.jd.composition.main.cartfragment;

import com.sxjs.jd.MainDataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2019/8/21 0021.
 */
@Module
public class CartPresenterModule {
	private  CartContract.View view;
	private  MainDataManager mainDataManager;

	public CartPresenterModule(CartContract.View view, MainDataManager mainDataManager) {
		this.view = view;
		this.mainDataManager = mainDataManager;
	}

	@Provides
	CartContract.View providerCartView(){
		return view;
	}

	@Provides
	MainDataManager providerMainDataManager() {
		return mainDataManager;
	}


}
