package com.mohit.shopebazardroid.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.utility.Utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UploadImagesWithDataAsyncTask extends AsyncTask<Object, Void, String>{

	public static Context context;
	private String url;
	private ApiResponse apiResponse;
	private int code;
	private String dialogMessage;
	private boolean isGET;
	private HashMap<String, String> postDataParams = new HashMap<String, String>();
	private ProgressDialog dialog = null;
	private ArrayList<HashMap<String, String>> imageArry = new ArrayList<>();
	public UploadImagesWithDataAsyncTask(Context context, String url, String dialogMessage, int code, HashMap<String, String> postDataParams, ArrayList<HashMap<String, String>> imageArry, boolean isGET) {

		this.context = context;
		this.url = url;
		this.dialogMessage = dialogMessage;
		this.code = code;
		this.isGET = isGET;
		this.postDataParams = postDataParams;
		this.imageArry =imageArry;
	}

	@Override
	protected void onPreExecute() {
		System.out.println("Request URL :" + url);
		if (!dialogMessage.equals("")) {
			dialog = Utility.setDialog(context);
			dialog.setMessage(dialogMessage);
			if (dialog != null) {
				if (!dialog.isShowing()) {
					dialog.show();
				} else {
					dialog.dismiss();
				}
			}

		}

	}

	@Override
	protected String doInBackground(Object... arg0) {
		// TODO Auto-generated method stub

		if (this.isCancelled())
			return null;

		apiResponse = (ApiResponse) arg0[0];
		String response = null;
		if (isGET) {

		} else {
			response = uploadFile(url, postDataParams,imageArry);//post method
		}
		return response;
	}

	public UploadImagesWithDataAsyncTask(Context context) {
		this.context = context;
	}

	@Override
	protected void onPostExecute(String response) {

		System.out.println("Response in ASYNC :" + response);
		try {
			if ((this.dialog != null) && this.dialog.isShowing()) {
				this.dialog.dismiss();
			}
		} catch (final IllegalArgumentException e) {
			// Handle or log or ignore
		} catch (final Exception e) {
			// Handle or log or ignore
		} finally {
			this.dialog = null;
		}

		if (response == null || response.isEmpty()) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			alertDialogBuilder.setTitle("Request TimeOut");
			alertDialogBuilder.setMessage("Please try again");
			alertDialogBuilder.setCancelable(false);
			alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

					Activity activity = (Activity) context;
					activity.finish();

				}
			});


			alertDialogBuilder.setPositiveButton("Try", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

					new UploadImagesWithDataAsyncTask(context, url, dialogMessage, code, postDataParams,imageArry, false).execute(context);

				}
			});

			alertDialogBuilder.show();


		} else {
			apiResponse.apiResponsePostProcessing(response, code);
		}


		this.cancel(true);
		return;
	}

	private String uploadFile(String requestURL,HashMap<String, String> postDataParams,ArrayList<HashMap<String, String>> imageArry) {
		// TODO Auto-generated method stub
		String req="data is sended successfully";
		String serverResponseMessage = "";
		String response_return = "";
		URL imageUrl = null;
		try {
			imageUrl = new URL(requestURL); // get WebService_URL
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		// generating byte[] boundary here

		HttpURLConnection conn = null;
		DataOutputStream outputStream = null;

		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 10 * 1024 * 1024;

		try {

			int serverResponseCode;
			conn = (HttpURLConnection) imageUrl.openConnection();
			conn.setConnectTimeout(500000);
			conn.setReadTimeout(500000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("http.keepAlive", "false");
			//conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			outputStream = new DataOutputStream(conn.getOutputStream());

			//////////////////////////////////////////////////////

			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream.writeBytes("Content-Disposition: form-data; name=\"json\"" + lineEnd);
			outputStream.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
			outputStream.writeBytes("Content-Length: " + req.length() + lineEnd);
			//outputStream.writeBytes(""+("Title=Hi").getBytes());
			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(req + lineEnd);
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			// json String request

			outputStream.writeBytes(twoHyphens + boundary + lineEnd);

			Log.i("images path==>", "" + imageArry); // get photo
			for (int i = 0; i < imageArry.size(); i++) {
				try {
					String file_extension = imageArry.get(i).get("file_extension");
					String filePath = imageArry.get(i).get("uploadedPath");
					Log.d("filePath==>", imageArry.get(i).get("uploadedPath") + "");
					FileInputStream fileInputStream = new FileInputStream(filePath);
					String lastOne = "Image";

					/////////////////////////////////////////////
					outputStream.writeBytes(twoHyphens + boundary + lineEnd);
					outputStream.writeBytes("Content-Disposition: attachment;  name=Image;" + "filename=" + lastOne + i + file_extension + lineEnd); // pass key & value of photo

					outputStream.writeBytes(lineEnd);
					bytesAvailable = fileInputStream.available(); // photo size bytes
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					buffer = new byte[bufferSize];
					// Read file
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);
					while (bytesRead > 0) {
						outputStream.write(buffer, 0, bufferSize);
						bytesAvailable = fileInputStream.available();
						bufferSize = Math.min(bytesAvailable, maxBufferSize);
						bytesRead = fileInputStream.read(buffer, 0, bufferSize);
					}
					outputStream.writeBytes(lineEnd);

					Log.d("posttemplate", "connection outputstream size is " + outputStream.size());
					fileInputStream.close();
				} catch (OutOfMemoryError o) {
					continue;
				}
			}

			//========== upload post data =========

			outputStream.writeBytes(twoHyphens + boundary + lineEnd);

			// for (Map.Entry<String,String> entry : postDataParams.entrySet()) {
			//System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
			Iterator myVeryOwnIterator = postDataParams.keySet().iterator();
			while(myVeryOwnIterator.hasNext()) {
				outputStream.writeBytes(twoHyphens + boundary + lineEnd);
				String key=(String)myVeryOwnIterator.next();
				String value=(String)postDataParams.get(key);

				outputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
				outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
				outputStream.writeBytes(lineEnd);
				outputStream.writeBytes(value);
				outputStream.writeBytes(lineEnd);
			}

			//=======================================

			outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			serverResponseCode = conn.getResponseCode();
			serverResponseMessage = conn.getResponseMessage();
			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response1 = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response1.append(line);
				response1.append('\r');
			}
			rd.close();
			response_return = response1.toString();

			Log.d("posttemplate", "server response code " + serverResponseCode);
			Log.d("posttemplate", "server response message "
					+ serverResponseMessage);



			outputStream.flush();
			outputStream.close();

			conn.disconnect();

		} catch (MalformedURLException e) {
			Log.d("posttemplate", "malformed url", e);
		} catch (IOException e) {
			Log.d("posttemplate", "ioexception", e);
		}
		Log.d("response--->", "****" + response_return);
		return response_return;
	}
}
