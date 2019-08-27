package com.sxjs.jd.composition.main.homefragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.util.ScreenUtil;
import com.sxjs.common.util.itemDecoration.SpacesItemDecoration;
import com.sxjs.common.widget.headerview.JDHeaderView;
import com.sxjs.common.widget.pulltorefresh.PtrFrameLayout;
import com.sxjs.common.widget.pulltorefresh.PtrHandler;
import com.sxjs.jd.MainDataManager;
import com.sxjs.jd.R;
import com.sxjs.common.base.BaseFragment;
import com.sxjs.jd.R2;
import com.sxjs.jd.entities.HomeIndex;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 作者：admin on 2017/3/15 10:47.
 */
public class MainHomeFragment extends BaseFragment implements JDHeaderView.RefreshDistanceListener, HomeContract.View, PositionChangedListener, BaseQuickAdapter.RequestLoadMoreListener {
	private static final String TAG = "MainHomeFragment";
	/**
	 * 改变titlebar中icon颜色时的距离
	 */
	private static int DISTANCE_WHEN_TO_SELECTED = 40;
	@BindView(R2.id.scanning_layout)
	LinearLayout scanningLayout;

	@BindView(R2.id.ll_bar)
	LinearLayout llBar;

	@BindView(R2.id.advisory_layout)
	LinearLayout advisoryLayout;
	@BindView(R2.id.home_title_bar_layout)
	FrameLayout homeTitleBarLayout;
	@BindView(R2.id.home_title_bar_bg_view)
	View homeTitleBarBgView;
	private View rootView = null;
	private RecyclerView recyclerView;
	private JDHeaderView mPtrFrame;
	private HomeMultipleRecycleAdapter adapter;
	private int distanceY;
	/**
	 * 加载首页样式标记
	 */
	private int flag = 1;

	@Inject
	HomePresenter mPresenter;

	public static MainHomeFragment newInstance() {
		return new MainHomeFragment();
	}


//	/**
//	 * 动态的设置状态栏  实现沉浸式状态栏
//	 */
//	private void initStateBar() {
//		llBar.setVisibility(View.VISIBLE);
//		//获取到状态栏的高度
//		int statusHeight = Utils.getStatusBarHeight(getActivity());
//		//动态的设置隐藏布局的高度
//		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llBar.getLayoutParams();
//		params.height = statusHeight;
//		llBar.setLayoutParams(params);
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_recycle, container, false);
		initBase();
		unbinder = ButterKnife.bind(this, rootView);
		initStateBar(llBar, android.R.color.transparent);
		setToolBarColor(getActivity(), true);
		return rootView;
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);

		if (!hidden) {
			if (distanceY > 200) {
				setToolBarColor(getActivity(), false);
			} else {
				setToolBarColor(getActivity(), true);
			}
		}
		Log.i(TAG, "onHiddenChanged: " + hidden);
	}


	private void initRvContent() {
		//添加ItemDecoration，item之间的间隔
		//rv_content.addItemDecoration(new SpacesItemDecoration(dip2px(1), dip2px(1), Color.BLUE));
	}


	public int dip2px(float dpValue) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
	}


	/**
	 * 初始化下拉刷新及滚动距离title发生的改变
	 */
	private void initBase() {
		DaggerHomeFragmentComponent.builder()
				.appComponent(getAppComponent())
				.homePresenterModule(new HomePresenterModule(this, MainDataManager.getInstance(mDataManager)))
				.build()
				.inject(this);

		initPtrFrame();
		recyclerView = (RecyclerView) this.rootView.findViewById(R.id.recyclerView);
		GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 4, GridLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(gridLayoutManager);
//        int leftRight = dip2px(10);
//        int topBottom = dip2px(10);
		recyclerView.addItemDecoration(new SpacesItemDecoration(dip2px(10), dip2px(7), 0, 1));
//        recyclerView.addItemDecoration(new SpaceItemDecoration(ScreenUtil.dip2px(getContext(),3)));
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				distanceY += dy;
				getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
				if (distanceY > ScreenUtil.dip2px(mActivity, 20)) {
					homeTitleBarBgView.setBackgroundColor(getResources().getColor(R.color.white));
					llBar.setBackgroundColor(getResources().getColor(R.color.white));

					if (Build.VERSION.SDK_INT > 10) {
						homeTitleBarBgView.setAlpha(distanceY * 1.0f / ScreenUtil.dip2px(mActivity, 100));
						llBar.setAlpha(distanceY * 1.0f / ScreenUtil.dip2px(mActivity, 100));
					} else {
						DISTANCE_WHEN_TO_SELECTED = 20;
					}
				} else {
					homeTitleBarBgView.setBackgroundColor(0);
					llBar.setBackgroundColor(0);
					getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
				}

				Log.i(TAG, "onScrolled: " + distanceY);

				if (distanceY > ScreenUtil.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED) && !scanningLayout.isSelected()) {
					scanningLayout.setSelected(true);
					advisoryLayout.setSelected(true);

				} else if (distanceY <= ScreenUtil.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED) && scanningLayout.isSelected()) {
					scanningLayout.setSelected(false);
					advisoryLayout.setSelected(false);
				}
			}
		});
		adapter = new HomeMultipleRecycleAdapter();

		adapter.setOnLoadMoreListener(this);
		adapter.setEnableLoadMore(true);
		adapter.setListener(this);
		recyclerView.setAdapter(adapter);
		mPresenter.getHomeIndexData(flag);
		flag = 0;
	}

	/**
	 * 初始化下拉刷新
	 */
	private void initPtrFrame() {
		mPtrFrame = (JDHeaderView) rootView.findViewById(R.id.rotate_header_list_view_frame);
		mPtrFrame.setOnRefreshDistanceListener(this);
		mPtrFrame.setPtrHandler(new PtrHandler() {
			@Override
			public void onRefreshBegin(PtrFrameLayout frame) {
				updateData();
			}
		});

		// 是否进入页面就开始显示刷新动作
		/*mPtrFrame.postDelayed(new Runnable() {
		    @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);*/
	}

	/**
	 * 下拉后刷新数据
	 */
	private void updateData() {
		mPtrFrame.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPresenter.getHomeIndexData(flag);
				if (flag == 0) {
					flag = 1;
				} else {
					flag = 0;
				}
			}
		}, 1000);
	}

	@Override
	public void onStart() {
		super.onStart();

	}


	@Override
	public void onPositionChange(int currentPosY) {
		if (currentPosY > 0) {
			if (homeTitleBarLayout.getVisibility() == View.VISIBLE) {
				homeTitleBarLayout.setVisibility(View.GONE);
			}
		} else {
			if (homeTitleBarLayout.getVisibility() == View.GONE) {
				homeTitleBarLayout.setVisibility(View.VISIBLE);
				distanceY = 0;
			}
		}
	}


	@Override
	public void setHomeIndexData(HomeIndex homeIndex) {
		if (homeIndex == null) {
			mPtrFrame.refreshComplete();
			return;
		}
		adapter.getData().clear();
		adapter.resetMaxHasLoadPosition();
		adapter.setNewData(homeIndex.itemInfoList);
		mPtrFrame.refreshComplete();
	}

	@Override
	public void setRecommendedWares(HomeIndex recommendedProducts) {
		adapter.getData().addAll(recommendedProducts.itemInfoList);
		adapter.loadMoreComplete();
	}

	@Override
	public void setMoreRecommendedWares(HomeIndex moreRecommendedProducts) {
		adapter.getData().addAll(moreRecommendedProducts.itemInfoList);
		adapter.loadMoreComplete();
	}

	/**
	 * 当前recyclerView 的position的回调
	 *
	 * @param position
	 */
	@Override
	public void currentPosition(int position) {

	}

	@Override
	public void onLoadMoreRequested() {
		recyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (adapter.getData().size() >= 90) {
					adapter.loadMoreEnd(false);
				} else {
					mPresenter.getRecommendedWares();
				}
			}
		}, 1000);
	}
}
