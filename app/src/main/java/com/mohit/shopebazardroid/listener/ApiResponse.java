package com.mohit.shopebazardroid.listener;

public interface ApiResponse {
	public void apiResponsePostProcessing(String response, int apiCode);
	public void networkError(int apiCode);
	public void responseError(int apiCode);
}
