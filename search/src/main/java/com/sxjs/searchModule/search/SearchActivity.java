package com.sxjs.searchModule.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kongzue.stacklabelview.StackLabel;
import com.sxjs.common.base.BaseActivity;
import com.sxjs.jd.entities.Test;
import com.sxjs.searchModule.R;
import com.sxjs.searchModule.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

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

	@BindView(R2.id.search_history_stackLabelView)
	StackLabel historyStackLabelView;
	@BindView(R2.id.search_recom_stackLabelView)
	StackLabel recomStackLabelView;


//	@BindView(R2.id.tv_search_content)
//	TextView textView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		ARouter.getInstance().inject(this);
		unbinder = ButterKnife.bind(this);
		Log.e("TAG=== ", " name:" + name + "\n " + " age:" + age + "\n" + testBean.name + " ====== " + testBean.age);
		setToolBarColor(this,false);
		historyStackLabelView.setLabels(new String[]{"佳明","Nike","咖啡","浪琴手表","SONY","iphoneX"});
		recomStackLabelView.setLabels(new String[]{"小米9","钥匙环","手表","运动","中秋超级品牌日","户外运动","阿迪达斯","任天堂游戏机"});
	}
}
