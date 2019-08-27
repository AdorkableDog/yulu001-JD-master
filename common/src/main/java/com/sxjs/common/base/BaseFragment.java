package com.sxjs.common.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sxjs.common.AppComponent;
import com.sxjs.common.GlobalAppComponent;
import com.sxjs.common.model.DataManager;
import com.sxjs.common.util.DialogUtil;
import com.sxjs.common.util.Utils;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/3/15.
 */

public class BaseFragment extends Fragment {
	protected Activity mActivity;
	protected Unbinder unbinder;
	protected Context mContext;
	protected boolean isInit = true;
	protected boolean isLoad = false;

	protected DataManager mDataManager;
	/**
	 * gif_logo进度dialog
	 */
	private Dialog dialog;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
		mContext = getAppComponent().getContext();
		mDataManager = getAppComponent().getDataManager();
		isCanLoadData();
	}

	protected void showShortToast(String message) {
		Toast.makeText(mActivity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}

	protected void showLongToast(String message) {
		Toast.makeText(mActivity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}

	protected AppComponent getAppComponent() {
		return GlobalAppComponent.getAppComponent();
	}

	protected void showJDLoadingDialog() {
		if (dialog == null)
			dialog = DialogUtil.createJDLoadingDialog(mActivity, null);
		if (!dialog.isShowing()) {
			dialog.show();
		}
	}

	/**
	 * 动态的设置状态栏  实现沉浸式状态栏
	 */
	public void initStateBar(View mView, int colorRes) {
		mView.setVisibility(View.VISIBLE);
		mView.setBackgroundColor(getResources().getColor(colorRes));
		//获取到状态栏的高度
		int statusHeight = Utils.getStatusBarHeight(getActivity());
		//动态的设置隐藏布局的高度
		ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) mView.getLayoutParams();
		params.height = statusHeight;
		mView.setLayoutParams(params);
	}

	public void setToolBarColor(Activity activity, boolean withoutUseStatusBarColor) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !withoutUseStatusBarColor) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//			activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
		}else {
			activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
		}
	}

	/**
	 * 视图是否已经对用户可见，系统的方法
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		isCanLoadData();
	}


	/**
	 * 是否可以加载数据
	 * 可以加载数据的条件：
	 * 1.视图已经初始化
	 * 2.视图对用户可见
	 */
	private void isCanLoadData() {
		if (!isInit) {
			return;
		}

		if (getUserVisibleHint()) {
			lazyLoad();
			isLoad = true;
		} else {
			if (isLoad) {
				stopLoad();
			}
		}
	}

	/**
	 * 当视图初始化并且对用户可见的时候去真正的加载数据
	 */
	public  void lazyLoad(){}

	/**
	 * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
	 */
	public  void stopLoad() {

	}





	protected void hideJDLoadingDialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	/**
	 * 视图销毁的时候讲Fragment是否初始化的状态变为false
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		isInit = false;
		isLoad = false;
		if (unbinder != null) {
			unbinder.unbind();
		}
		if (dialog != null) {
			if (dialog.isShowing()) dialog.dismiss();
			dialog = null;
		}
	}
}
