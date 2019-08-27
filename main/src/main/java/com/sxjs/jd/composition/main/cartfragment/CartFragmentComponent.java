package com.sxjs.jd.composition.main.cartfragment;

import com.sxjs.common.AppComponent;
import com.sxjs.common.PerFragment;

import dagger.Component;

/**
 * Created by Administrator on 2019/8/21 0021.
 */
@PerFragment
@Component(dependencies = AppComponent.class , modules = CartPresenterModule.class)
public interface CartFragmentComponent {
	void inject(CartFragment fragment);
}
