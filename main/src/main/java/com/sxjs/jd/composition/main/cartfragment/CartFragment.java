package com.sxjs.jd.composition.main.cartfragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseFragment;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.util.AppUtils;
import com.sxjs.common.listener.PowerGroupListener;
import com.sxjs.common.widget.SectionDecoration;
import com.sxjs.common.widget.headerview.JDHeaderView;
import com.sxjs.common.widget.pulltorefresh.PtrFrameLayout;
import com.sxjs.common.widget.pulltorefresh.PtrHandler;
import com.sxjs.jd.MainDataManager;
import com.sxjs.jd.R;
import com.sxjs.jd.R2;
import com.sxjs.jd.entities.CartsEntity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author liuxiaodong
 * @date 2018/12/1
 * @description
 */
public class CartFragment extends BaseFragment implements CartContract.View, PtrHandler, BaseQuickAdapter.RequestLoadMoreListener {

	@Inject
	CartPresenter cartPresenter;

	private TextView textView;
	private View view;
	private String TAG = "CartFragment";

	@BindView(R2.id.find_recyclerview)
	RecyclerView cartRecyclerView;
	@BindView(R2.id.tv_cart_title)
	TextView tvCartTitle;

	@BindView(R2.id.ll_bar)
	LinearLayout llBar;
	@BindView(R2.id.rl_cart_head)
	RelativeLayout cartHead;


	@BindView(R2.id.find_pull_refresh_header)
	JDHeaderView findPullRefreshHeader;

//	private int tempY;

	int barStatusBarHeight = 45;//假设状态栏高度
	int titleHeight = 45;//假设title高度
	int hgt = 190 - barStatusBarHeight - titleHeight;//假设特别背景高度250
	int distance = 0;


	private CartsStoreListAdapter adapter;
	private CartsEntity cartData;

	public static CartFragment newInstance() {
		return new CartFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_cart, container, false);
		unbinder = ButterKnife.bind(this, view);
		initStateBar(llBar, android.R.color.white);
		initView();
		initData();
		return view;
	}


	private void initView() {
		DaggerCartFragmentComponent.builder()
				.appComponent(getAppComponent())
				.cartPresenterModule(new CartPresenterModule(this, MainDataManager.getInstance(mDataManager)))
				.build()
				.inject(this);

		findPullRefreshHeader.setPtrHandler(this);
		cartRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
		adapter = new CartsStoreListAdapter(R.layout.item_cart_recyclerview);
		adapter.setOnLoadMoreListener(this);
		adapter.setEnableLoadMore(true);
		cartRecyclerView.setAdapter(adapter);
		//RecycleView添加悬浮布局
		initDecoration();

		final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cartHead.getLayoutParams();
		cartHead.post(new Runnable() {
			@Override
			public void run() {
				int height = cartHead.getHeight();
				Log.d(TAG, "initView() returned: " + height);
			}
		});
// params.setMargins(dip2px(MainActivity.this, 1), 0, 0, 0); // 可以实现设置位置信息，如居左距离，其它类推  
// params.leftMargin = dip2px(MainActivity.this, 1);  
//		params.height = dip2px( height);
//		cartHead.setLayoutParams(params);

		cartRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
//				tempY += dy;
				distance += dy;
				//上下滑动的时候背景变
				if (distance <= hgt) {
//					params.height = (140 - (dip2px(distance) / 5));
					((View) cartHead.getParent()).setScrollY(distance);
//					cartHead.offsetTopAndBottom(-distance);
					Log.e(TAG, "onScrolled:======11--------- " + (140 - (dip2px(distance) / 5)));
				} else if (distance > hgt) {
					((View) cartHead.getParent()).setScrollY(hgt);
//					cartHead.offsetTopAndBottom(-hgt);
//					params.height = 80 /*+ (dip2px(hgt) / 4)*/;
					Log.e(TAG, "onScrolled:======22--------- ");
				}
				LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
				int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
				if (firstCompletelyVisibleItemPosition == 0) {
					distance = 0;
					((View) cartHead.getParent()).setScrollY(distance);

				}
//				cartHead.setLayoutParams(params);
//				lp.topMargin = cartHead.getTop() + tempY;
//				cartHead.setLayoutParams(lp);
//				Log.i(TAG, "onScrolled: " + dy + " ==== tempY: ==== " + distance);
			}
		});
	}


	/**
	 *  
	 *      * dp转为px 
	 *      * @param context  上下文 
	 *      * @param dipValue dp值 
	 *      * @return 
	 *      
	 */
	public int dip2px(float dipValue) {
		Resources resources = getContext().getResources();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, resources.getDisplayMetrics());
	}


	/**
	 * 添加悬浮布局
	 */
	private void initDecoration() {
		SectionDecoration sectionDecoration = SectionDecoration.Builder.init(new PowerGroupListener() {
			@Override
			public String getGroupName(int position) {
				//获取组的名称，用于判断是否是同一组的数据
				if (cartData != null) {
					if (cartData.getData().getProCarts().size() > position) {
						return cartData.getData().getProCarts().get(position).getShopName();

					}
				}
				return null;
			}

			@Override
			public View getGroupView(int position) {
				//获取自定义的组数据
				if (cartData != null) {
					if (cartData.getData().getProCarts().size() > position) {
						View view = getActivity().getLayoutInflater().inflate(R.layout.cart_item_store_title_info, null, false);
						((TextView) view.findViewById(R.id.tv)).setText(cartData.getData().getProCarts().get(position).getShopName());
//						((ImageView) view.findViewById(R.id.iv)).setImageResource(dataList.get(position).getIcon());
						return view;
					}
				}

				return null;
			}
		}).setGroupHeight(AppUtils.dip2px(getContext(), 40)).build();
		cartRecyclerView.addItemDecoration(sectionDecoration);
	}

	public void initData() {
		cartPresenter.getMyCartListData();
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

	}

	/**
	 * 手机顶部的标题栏
	 *
	 * @param hidden
	 */
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) setToolBarColor(getActivity(), hidden);
	}


	@Override
	public void setMyCartData(CartsEntity cartData) {
		this.cartData = cartData;
		Log.i(TAG, "setMyCartData: " + cartData.getMsg());
		adapter.addData(cartData.getData().getProCarts());
	}

	@Override
	public void setMyCartMoreData(CartsEntity cartMoreData) {
//		adapter.getData().addAll(cartMoreData.getData().getProCarts());
//		adapter.loadMoreComplete();
	}


	@Override
	public void onRefreshBegin(final PtrFrameLayout frame) {
		frame.postDelayed(new Runnable() {
			@Override
			public void run() {
				frame.refreshComplete();
			}
		}, 2000);
	}


	@Override
	public void onLoadMoreRequested() {
		cartRecyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
//				if (adapter.getData().size() >= 90) {
//					adapter.loadMoreEnd(false);
//				} else {
//					cartPresenter.getMyCartMoreData();
//				}
				adapter.loadMoreEnd(false);
			}
		}, 1000);
	}
}
