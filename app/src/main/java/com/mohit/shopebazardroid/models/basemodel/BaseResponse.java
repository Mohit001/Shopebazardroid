package com.mohit.shopebazardroid.models.basemodel;

import java.io.Serializable;

public class BaseResponse <T> extends ApiBaseModel implements Serializable{

	private T info;

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}
	
	
}
