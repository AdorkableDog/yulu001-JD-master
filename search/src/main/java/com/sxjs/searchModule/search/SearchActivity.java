package com.sxjs.searchModule.search;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sxjs.jd.entities.Test;
import com.sxjs.searchModule.R;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sxjs.common.base.BaseActivity;
import com.sxjs.searchModule.R2;

import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/8/31 0031.
 */
@Route(path = "/search/activity")
public class SearchActivity extends BaseActivity {


	@Autowired
	String name;

	@Autowired
	int age;

	@Autowired(name = "test")
	Test testBean;

//	@BindView(R2.id.tv_search_content)
//	TextView textView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		ARouter.getInstance().inject(this);
		Log.e("TAG=== ", " name:" + name + "\n " + " age:" + age + "\n" + testBean.name + " ====== " + testBean.age);
	}
}
