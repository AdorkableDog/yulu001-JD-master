package com.sxjs.jd.entities;

import java.util.List;

/**
 * Created by Administrator on 2019/6/26 0026.
 */

public class ClassListBean {
	/**
	 * code : 200
	 * data : {"productClassifyList":[{"classify_id":124,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/8eeb6213-e8ff-4cae-b160-729fe32b6086.jpg","classify_name":"女装"},{"classify_id":125,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/f46c2085-b2de-4b8b-b827-9fe95d7a291e.jpg","classify_name":"男装"},{"classify_id":159,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/d2c4aeda-b4da-4ec6-92cf-0eb91df5d1a4.jpg","classify_name":"童装童鞋"},{"classify_id":147,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/b36550d0-80a2-4b05-8df3-a4504dd01212.jpg","classify_name":"鞋包"},{"classify_id":152,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/ff1efa7b-9c93-44f7-9067-14f8d829c512.jpg","classify_name":"手表配饰"},{"classify_id":158,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/0ddf15f2-e394-4786-bf7b-f27a9f8f53ef.jpg","classify_name":"手机数码"},{"classify_id":143,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/4edcc655-e333-47ba-b445-65fac77755ef.jpg","classify_name":"家居家纺"},{"classify_id":134,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/27540d34-acb3-4125-be9c-87ecd8a67557.jpg","classify_name":"美妆"},{"classify_id":121,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/06/5483dc31-08ec-44b3-a1c9-ef8bc3ade448.jpg","classify_name":"零食"},{"classify_id":119,"classify_img":"http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/05/5b89e334-c88a-4f37-af5a-cde8c9264f2c.jpg","classify_name":"水果蔬菜"}]}
	 * msg : 成功
	 */

	private String code;
	private DataBean data;
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static class DataBean {
		private List<ProductClassifyListBean> productClassifyList;

		public List<ProductClassifyListBean> getProductClassifyList() {
			return productClassifyList;
		}

		public void setProductClassifyList(List<ProductClassifyListBean> productClassifyList) {
			this.productClassifyList = productClassifyList;
		}

		public static class ProductClassifyListBean {
			/**
			 * classify_id : 124
			 * classify_img : http://wendi-imgs.oss-cn-beijing.aliyuncs.com//upload/productClassify/2019/06/10/8eeb6213-e8ff-4cae-b160-729fe32b6086.jpg
			 * classify_name : 女装
			 */

			private String classify_id;
			private String classify_img;
			private String classify_name;

			public String getClassify_id() {
				return classify_id;
			}

			public void setClassify_id(String classify_id) {
				this.classify_id = classify_id;
			}

			public String getClassify_img() {
				return classify_img;
			}

			public void setClassify_img(String classify_img) {
				this.classify_img = classify_img;
			}

			public String getClassify_name() {
				return classify_name;
			}

			public void setClassify_name(String classify_name) {
				this.classify_name = classify_name;
			}
		}
	}
}
