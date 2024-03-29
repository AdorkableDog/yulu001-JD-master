package com.sxjs.jd.composition.main.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sxjs.app_common.service.ITestService;
import com.sxjs.common.base.BaseFragment;
import com.sxjs.jd.R;
import com.sxjs.jd.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author liuxiaodong
 * @date 2018/12/1
 * @description
 */
public class MyFragment extends BaseFragment {
	@BindView(R2.id.ll_bar)
	LinearLayout llBar;
	private TextView textView;
	private View view;

	public static MyFragment newInstance() {
		return new MyFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_my, container, false);
		unbinder = ButterKnife.bind(this, view);
		initStateBar(llBar, android.R.color.white);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		textView = view.findViewById(R.id.serviceText);
		view.findViewById(R.id.testService).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ITestService service = ARouter.getInstance().navigation(ITestService.class);
				if (service != null) {
					String testPackageName = service.getTestPackageName();
					textView.setText(testPackageName);
				} else {
					Toast.makeText(getContext(), "该服务所在模块未参加编译", Toast.LENGTH_LONG).show();
				}

			}
		});
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) setToolBarColor(getActivity(), hidden);
	}
}
