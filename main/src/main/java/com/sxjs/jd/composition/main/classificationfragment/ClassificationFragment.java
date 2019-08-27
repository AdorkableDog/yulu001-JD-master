package com.sxjs.jd.composition.main.classificationfragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.CommonModule;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.jd.MainDataManager;
import com.sxjs.jd.R;
import com.sxjs.common.base.BaseFragment;
import com.sxjs.jd.R2;
import com.sxjs.jd.entities.ClassIndex;
import com.sxjs.jd.entities.ClassListBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/3/21.
 */

public class ClassificationFragment extends BaseFragment implements ClassificationContract.View, BaseQuickAdapter.OnItemClickListener {
	@Inject
	ClassificationPresenter presenter;

	@BindView(R2.id.scanning_img)
	ImageView scanningImg;

	@BindView(R2.id.ll_bar)
	LinearLayout llBar;

	@BindView(R2.id.advisory_img)
	ImageView advisoryImg;
	@BindView(R2.id.classic_recycle)
	RecyclerView typeOfGoodsNameView;
	@BindView(R2.id.classic_icon_view)
	RecyclerView classicIconView;
	private TypeOfGoodsNameAdapter adapter;
	private ClassicIconRecycleAdapter iconAdapter;
	private TextView tvTitleList;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_classification, container, false);
		unbinder = ButterKnife.bind(this, view);
		initView();
		initData();
		initStateBar(llBar, android.R.color.white);
		return view;
	}


	/**
	 * 视图是否已经对用户可见，系统的方法
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		Log.i(TAG, "setUserVisibleHint: " + isVisibleToUser);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) setToolBarColor(getActivity(), hidden);
	}


	public static ClassificationFragment newInstance() {
		ClassificationFragment classificationFragment = new ClassificationFragment();
		return classificationFragment;
	}

	public void initView() {

		DaggerClassificationFragmentComponent.builder()
				.appComponent(getAppComponent())
				.classificationPresenterModule(new ClassificationPresenterModule(this, MainDataManager.getInstance(mDataManager)))
				.build()
				.inject(this);

		typeOfGoodsNameView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
		classicIconView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
		adapter = new TypeOfGoodsNameAdapter(R.layout.type_of_goods_name_view_item);
		iconAdapter = new ClassicIconRecycleAdapter();
		classicIconView.setAdapter(iconAdapter);
		adapter.setOnItemClickListener(this);
		typeOfGoodsNameView.setAdapter(adapter);

	}

	public void initData() {
		presenter.getTitleListData();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

	}

	/**
	 * @param list
	 */
	@Override
	public void setTypeOfNameData(final List<String> list) {
//        adapter.addData(list);
//        adapter.notifyDataSetChanged();
	}

	@Override
	public void setHomeIndexData(ClassListBean classTitle) {
		List<String> titleList = new ArrayList<>();
		for (int i = 0; i < classTitle.getData().getProductClassifyList().size(); i++) {
			titleList.add(classTitle.getData().getProductClassifyList().get(i).getClassify_name());
		}
		presenter.getTypeIconsData();
		adapter.setNewData(titleList);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void setTypeIconsData(final String text) {


	}

	@Override
	public void setTyptListIconData(ClassIndex classIndex) {
		iconAdapter.setNewData(classIndex.data);
		iconAdapter.resetMaxHasLoadPosition();
	}

	/**
	 * 上一个被点击的item
	 */
	private View lastClikeView = null;
	private TextView lastClikeTextView = null;
	private static final String TAG = "ClassificationFragment";
	private int recycleViewCanShowHeight;

	@Override
	public void onItemClick(final BaseQuickAdapter adapter, View view, int position) {

		tvTitleList = (TextView) view.findViewById(R.id.goods_type_name);

		if (view.equals(lastClikeView)) return;
		if (recycleViewCanShowHeight == 0) {
			recycleViewCanShowHeight = typeOfGoodsNameView.getHeight();
		}


		view.setSelected(true);
		tvTitleList.setTypeface(CommonModule.mBoldTypeface);
		tvTitleList.setTextSize(15);
		view.setBackgroundColor(getResources().getColor(R.color.white));
//		showShortToast("" + position);
		if (recycleViewCanShowHeight > 0 && Build.VERSION.SDK_INT > 10) {
			typeOfGoodsNameView.smoothScrollBy(0, (int) (view.getY() - recycleViewCanShowHeight / 2 + view.getPivotY()));
		}
		if (lastClikeView != null) {
			lastClikeView.setBackgroundColor(0);
			lastClikeView.setSelected(false);
		}

		if (lastClikeTextView != null) {
			lastClikeTextView.setTextSize(12);
			lastClikeTextView.setTypeface(CommonModule.mMedTypeface);
		}

		lastClikeView = view;
		lastClikeTextView = tvTitleList;
	}
}
