package com.sxjs.jd.composition.main.classificationfragment;

import android.util.Log;

import com.sxjs.common.base.rxjava.ErrorDisposableObserver;
import com.sxjs.jd.MainDataManager;
import com.sxjs.jd.composition.BasePresenter;
import com.sxjs.jd.entities.ClassIndex;
import com.sxjs.jd.entities.ClassListBean;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by admin on 2017/3/22.
 */

public class ClassificationPresenter extends BasePresenter implements ClassificationContract.Presenter {
	private MainDataManager mDataManager;

	private ClassificationContract.View mClassificationView;

	@Inject
	public ClassificationPresenter(MainDataManager mDataManager, ClassificationContract.View view) {
		this.mDataManager = mDataManager;
		this.mClassificationView = view;

	}

	@Override
	public void getTypeOfNameData() {
		List<String> typeOfNameData = mDataManager.getTypeOfNameData();
		mClassificationView.setTypeOfNameData(typeOfNameData);
	}

	@Override
	public void getTitleListData() {
		addDisposabe(mDataManager.getData(new ErrorDisposableObserver<ClassListBean>() {
			@Override
			public void onNext(ClassListBean classTitle) {
				mClassificationView.setHomeIndexData(classTitle);
			}

			@Override
			public void onError(Throwable e) {
				Log.e("TAG", "onError: "+e );
				mClassificationView.setHomeIndexData(null);
			}

			@Override
			public void onComplete() {
			}
		},ClassListBean.class,"classTitleData.txt"));
	}




	@Override
	public void getTypeIconsData() {

		addDisposabe(mDataManager.getData(new ErrorDisposableObserver<ClassIndex>() {
			@Override
			public void onNext(ClassIndex classIndex) {
				mClassificationView.setTyptListIconData(classIndex);
			}

			@Override
			public void onError(Throwable e) {
				Log.e("TAG", "onError: "+e );
				mClassificationView.setHomeIndexData(null);
			}

			@Override
			public void onComplete() {
			}
		},ClassIndex.class,"classIconList.txt"));


	}
}
