package com.iems.core.util;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String response = HttpClientUtil.get("http://www.baidu.com", null);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String url, Map<String, String> data, String encoding, int timeout) throws IOException {
		NameValuePair[] nvpData = null;
		if (data != null) {
			nvpData = new NameValuePair[data.size()];
			int i = 0;
			for (String key : data.keySet()) {
				nvpData[i] = new NameValuePair(key, data.get(key));
				i++;
			}
		}
		
		return get(url, nvpData, encoding, timeout);
	}

	public static String get(String url, NameValuePair[] data) throws IOException {
		return get(url, data, "UTF-8");
	}

	public static String get(String url, NameValuePair[] data, String encoding) throws IOException {
		return get(url, data, encoding, 0);
	}

	@SuppressWarnings("deprecation")
	public static String get(String url, NameValuePair[] data, String encoding, int timeout) throws IOException {
		HttpClient client = new HttpClient();

		if (timeout > 0) {
			client.setTimeout(timeout);
		}

		GetMethod method = new GetMethod(url);

		if (data != null) {
			method.setQueryString(data);
		}
		client.executeMethod(method);

		// System.out.println(method.getStatusLine());

		// System.out.println(method.getResponseBodyAsString());

		// int status = method.getStatusCode();
		String response = method.getResponseBodyAsString();

		method.releaseConnection();

		return response;
	}

	public static String post(String url, NameValuePair[] data) throws IOException {
		return post(url, data, "UTF-8");
	}

	public static String post(String url, NameValuePair[] data, String encoding) throws IOException {
		return post(url, data, encoding, 0);
	}

	@SuppressWarnings("deprecation")
	public static String post(String url, NameValuePair[] data, String encoding, int timeout) throws IOException {
		HttpClient client = new HttpClient();

		if (timeout > 0) {
			client.setTimeout(timeout);
		}

		PostMethod method = new PostMethod(url);

		if (data != null) {
			method.setRequestBody(data);
		}
		client.executeMethod(method);

		System.out.println(method.getStatusLine());

		System.out.println(method.getResponseBodyAsString());

		String response = method.getResponseBodyAsString();

		method.releaseConnection();

		return response;
	}

	public class StatusResponse {
		private int statusCode;
		private String statusText;
		private String statusLine;

		private long responseContentLength;
		private String responseBody;
		private String responseCharSet;

		public StatusResponse() {

		}

		public StatusResponse(int statusCode, String statusText, String statusLine, long responseContentLength,
				String responseBody, String responseCharSet) {
			this.statusCode = statusCode;
			this.statusText = statusText;
			this.statusLine = statusLine;

			this.responseContentLength = responseContentLength;
			this.responseBody = responseBody;
			this.responseCharSet = responseCharSet;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public String getStatusText() {
			return statusText;
		}

		public void setStatusText(String statusText) {
			this.statusText = statusText;
		}

		public String getStatusLine() {
			return statusLine;
		}

		public void setStatusLine(String statusLine) {
			this.statusLine = statusLine;
		}

		public long getResponseContentLength() {
			return responseContentLength;
		}

		public void setResponseContentLength(long responseContentLength) {
			this.responseContentLength = responseContentLength;
		}

		public String getResponseBody() {
			return responseBody;
		}

		public void setResponseBody(String responseBody) {
			this.responseBody = responseBody;
		}

		public String getResponseCharSet() {
			return responseCharSet;
		}

		public void setResponseCharSet(String responseCharSet) {
			this.responseCharSet = responseCharSet;
		}
	}
}
