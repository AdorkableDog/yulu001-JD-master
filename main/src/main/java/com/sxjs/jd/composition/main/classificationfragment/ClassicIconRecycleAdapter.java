package com.sxjs.jd.composition.main.classificationfragment;

import android.graphics.Color;
import android.util.Log;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sxjs.common.base.baseadapter.BaseMultiItemQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.util.FrescoUtils;
import com.sxjs.common.util.UIUtils;
import com.sxjs.common.widget.WrapContentGridView;
import com.sxjs.jd.R;
import com.sxjs.jd.data.Constant;
import com.sxjs.jd.entities.ClassIndex;

/**
 * Created by Administrator on 2019/6/26 0026.
 */


public class ClassicIconRecycleAdapter extends BaseMultiItemQuickAdapter<ClassIndex.DataBean, BaseViewHolder> {


	private int maxHasLoadPosition;
	private WrapContentGridView gridView;
	private ClassifyContentGoodsAdapter adapter;

	public ClassicIconRecycleAdapter() {
//		setSpanSizeLookup(this);
		addItemType(Constant.TYPE_TOP_BANNER, R.layout.item_classify_top);
		addItemType(Constant.TYPE_ICON_LIST, R.layout.item_classify_gridview);
	}


	public void resetMaxHasLoadPosition() {
		maxHasLoadPosition = 0;
	}


	@Override
	protected void convert(BaseViewHolder helper, ClassIndex.DataBean item, int position) {
		if (maxHasLoadPosition < position) {
			maxHasLoadPosition = position;
		}
		if ("topBanner".equals(item.itemType) && maxHasLoadPosition <= position) {
			bindTopBannerData(helper, item, position);
		} else if ("iconList".equals(item.itemType) && maxHasLoadPosition <= position) {
			bindIconListData(helper, item, position);
		}
	}

	private void bindIconListData(BaseViewHolder helper, ClassIndex.DataBean item, int position) {
//		 helper.getView(R.id.tv_classList_title).setText();
		int size = item.classList.productClassifys.size();
		Log.i(TAG, "bindIconListData: " + position + "size : " + size);
//		helper.setText(R.id.tv_classList_title, item.listTitle);
		gridView = helper.getView(R.id.classify_content_gridview);
		adapter = new ClassifyContentGoodsAdapter(mContext);
		adapter.setData(item.classList.productClassifys);
		gridView.setAdapter(adapter);


	}

	private void bindTopBannerData(BaseViewHolder helper, ClassIndex.DataBean item, int position) {


		//初始化圆角圆形参数对象
		RoundingParams rp = new RoundingParams();
//设置图像是否为圆形
		rp.setRoundAsCircle(true);
		rp.setBorder(Color.RED, 10);

		GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
				//设置圆形圆角参数
				.setRoundingParams(rp)
				//设置圆角半径
				//.setRoundingParams(RoundingParams.fromCornersRadius(20))
				//分别设置左上角、右上角、左下角、右下角的圆角半径
				.setRoundingParams(RoundingParams.fromCornersRadii(10, 10, 10, 10))
				//分别设置（前2个）左上角、(3、4)右上角、(5、6)左下角、(7、8)右下角的圆角半径
				//.setRoundingParams(RoundingParams.fromCornersRadii(new float[]{20,25,30,35,40,45,50,55}))
				//设置圆形圆角参数；RoundingParams.asCircle()是将图像设置成圆形
				//.setRoundingParams(RoundingParams.asCircle())
				//设置淡入淡出动画持续时间(单位：毫秒ms)
				.setFadeDuration(2000)
				//构建
				.build();



		((SimpleDraweeView) helper.getView(R.id.type_item_middle_banner_content)).setImageURI(item.banner.banner_img);
		((SimpleDraweeView) helper.getView(R.id.type_item_middle_banner_content)).setHierarchy(hierarchy);
		FrescoUtils.setControllerListener(
				((SimpleDraweeView) helper.getView(R.id.type_item_middle_banner_content)),
				item.banner.banner_img,
				UIUtils.getScreenWidth(mContext));
//		helper.setText(R.id.recommended_title, item.itemContentList.get(0).itemTitle);
//		helper.setText(R.id.recommended_price, item.itemContentList.get(0).itemSubTitle);
	}

//	@Override
//	public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
//		return mData.get(position).getSpanSize();
//	}?
}
