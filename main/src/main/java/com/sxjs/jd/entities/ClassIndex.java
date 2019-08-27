package com.sxjs.jd.entities;

import com.sxjs.common.base.baseadapter.entity.MultiItemEntity;
import com.sxjs.jd.data.Constant;

import java.util.List;

/**
 * Created by Administrator on 2019/6/26 0026.
 */

public class ClassIndex {

	/**
	 * code : 200
	 * data : [{"banner":{"banner_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/dd2d2851-e989-4a03-81b5-c618682abf72.jpg","banner_url":"","product_id":""},"itemType":"topBanner","module":"topBanner"},{"classList":{"productClassifys":[{"classify_id":129,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/4f13e668-8b35-425d-8f28-ea57393b7265.jpg","classify_name":"裤子"},{"classify_id":128,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/924304e1-bb99-4e68-960e-64d173878ca4.jpg","classify_name":"外套"},{"classify_id":127,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/39265d60-2886-44b6-a5c4-4ed8bad8e0ce.jpg","classify_name":"T恤"},{"classify_id":126,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/38c8371f-9fa6-42ff-be9e-d25021009e69.jpg","classify_name":"裙子"}]},"itemType":"iconList","listTitle":"热门分类","module":"iconList"},{"classList":[{"listData":{"productClassifys":[{"classify_id":129,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/4f13e668-8b35-425d-8f28-ea57393b7265.jpg","classify_name":"裤子"},{"classify_id":128,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/924304e1-bb99-4e68-960e-64d173878ca4.jpg","classify_name":"外套"},{"classify_id":127,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/39265d60-2886-44b6-a5c4-4ed8bad8e0ce.jpg","classify_name":"T恤"},{"classify_id":126,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/38c8371f-9fa6-42ff-be9e-d25021009e69.jpg","classify_name":"裙子"}]}}],"itemType":"iconList","listTitle":"精品推荐","module":"iconList"},{"classList":[{"listData":{"productClassifys":[{"classify_id":129,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/4f13e668-8b35-425d-8f28-ea57393b7265.jpg","classify_name":"裤子"},{"classify_id":128,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/924304e1-bb99-4e68-960e-64d173878ca4.jpg","classify_name":"外套"},{"classify_id":127,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/39265d60-2886-44b6-a5c4-4ed8bad8e0ce.jpg","classify_name":"T恤"},{"classify_id":126,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/38c8371f-9fa6-42ff-be9e-d25021009e69.jpg","classify_name":"裙子"}]}}],"itemType":"iconList","listTitle":"优选商品","module":"iconList"}]
	 * msg : 成功
	 */

	public String code;
	public String msg;
	public List<DataBean> data;



	public static class DataBean implements MultiItemEntity{
		/**
		 * banner : {"banner_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/dd2d2851-e989-4a03-81b5-c618682abf72.jpg","banner_url":"","product_id":""}
		 * itemType : topBanner
		 * module : topBanner
		 * classList : {"productClassifys":[{"classify_id":129,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/4f13e668-8b35-425d-8f28-ea57393b7265.jpg","classify_name":"裤子"},{"classify_id":128,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/924304e1-bb99-4e68-960e-64d173878ca4.jpg","classify_name":"外套"},{"classify_id":127,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/39265d60-2886-44b6-a5c4-4ed8bad8e0ce.jpg","classify_name":"T恤"},{"classify_id":126,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/38c8371f-9fa6-42ff-be9e-d25021009e69.jpg","classify_name":"裙子"}]}
		 * listTitle : 热门分类
		 */

		public BannerBean banner;
		public String itemType;
		public String module;
		public ClassListBean classList;
		public String listTitle;

		@Override
		public int getItemType() {
			if("topBanner".equals(itemType)){
				return Constant.TYPE_TOP_BANNER;
			}
			else if("iconList".equals(itemType)){
				return Constant.TYPE_ICON_LIST;
			}
			return Constant.TYPE_TITLE;
		}


		public static class BannerBean {
			/**
			 * banner_img : http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/dd2d2851-e989-4a03-81b5-c618682abf72.jpg
			 * banner_url :
			 * product_id :
			 */
			public String banner_img;
			public String banner_url;
			public String product_id;

		}

		public static class ClassListBean {
			public List<ProductClassifysBean> productClassifys;


			public static class ProductClassifysBean {
				/**
				 * classify_id : 129
				 * classify_img : http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/4f13e668-8b35-425d-8f28-ea57393b7265.jpg
				 * classify_name : 裤子
				 */

				public String classify_id;
				public String classify_img;
				public String classify_name;


			}
		}
	}
}
