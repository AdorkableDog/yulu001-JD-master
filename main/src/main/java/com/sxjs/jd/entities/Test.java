package com.sxjs.jd.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2019/8/31 0031.
 */

public class Test implements Parcelable {

	public static String name;
	public static int age;

	public Test(String name, int age) {
		this.name = name;
		this.age = age;
	}

	protected Test(Parcel in) {
		name = in.readString();
		age = in.readInt();
	}

	public static final Creator<Test> CREATOR = new Creator<Test>() {
		@Override
		public Test createFromParcel(Parcel in) {
			return new Test(in);
		}

		@Override
		public Test[] newArray(int size) {
			return new Test[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
	}
}
