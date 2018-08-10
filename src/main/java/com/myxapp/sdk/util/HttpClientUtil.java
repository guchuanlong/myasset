package com.myxapp.sdk.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String sendPost(String url, String data, Map<String, String> header)
			throws IOException, URISyntaxException {


		return sendPost(url, data, header, ContentType.APPLICATION_JSON);

	}

	public static String sendPost(String url, String data, Map<String, String> header, ContentType contentype)
			throws IOException, URISyntaxException {
		if (!StringUtil.isBlank(url)) {
			Long random = System.currentTimeMillis();
			if (url.indexOf("?") == -1) {
				url = url + "?random=" + random;
			} else {
				url = url + "&random=" + random;
			}
		}
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(new URL(url).toURI());
		String charset = "utf-8";
		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpPost.setHeader(entry.getKey(), entry.getValue());
				if ("charset".equals(entry.getKey())) {
					charset = entry.getValue();
				}
			}

		}
		if (!StringUtil.isBlank(data)) {
			StringEntity dataEntity = new StringEntity(data, contentype);
			httpPost.setEntity(dataEntity);
		}
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String result = "";
				if (entity != null) {
					InputStream instream = entity.getContent();
					result = IOUtils.toString(instream, "UTF-8");
					instream.close();
				}
				return result;
			} else {
				throw new RuntimeException("error code " + response.getStatusLine().getStatusCode() + ":"
						+ response.getStatusLine().getReasonPhrase());
			}
		} finally {
			response.close();
			httpclient.close();
		}
	}

	public static String sendPost(String address, String param) {
		logger.info("restful address : " + address);
		logger.info("param : " + param);
		String result = "";
		try {
			result = HttpClientUtil.sendPost(address, param, null);
			logger.info("result : " + result);
		} catch (IOException e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
		} catch (URISyntaxException e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
		}
		// 请求发生异常后，result 为 空
		return result;
	}

	public static String sendPostXml(String address, String param) {
		logger.info("restful address : " + address);
		logger.info("param : " + param);
		String result = "";
		try {
			result = HttpClientUtil.sendPost(address, param, null, ContentType.APPLICATION_XML);
			logger.info("result : " + result);
		} catch (IOException e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
		} catch (URISyntaxException e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
		}
		// 请求发生异常后，result 为 空
		return result;
	}

	/**
	 * 发送GET请求
	 * 
	 * @param url
	 *            目的地址
	 * @param parameters
	 *            请求参数，Map类型。
	 * @return 远程响应结果
	 */
	public static String sendGet(String url, Map<String, String> parameters) {
		if (!StringUtil.isBlank(url)) {
			Long random = System.currentTimeMillis();
			if (url.indexOf("?") == -1) {
				url = url + "?random=" + random;
			} else {
				url = url + "&random=" + random;
			}
		}
		StringBuffer buffer = new StringBuffer();// 返回的结果
		InputStream is = null;

		ByteArrayOutputStream baos = null;
		StringBuffer sb = new StringBuffer();// 存储参数
		String params = "";// 编码之后的参数
		try {
			if (null != parameters) {
				// 编码请求参数
				if (parameters.size() == 1) {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
					}
					params = sb.toString();
				} else {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"))
								.append("&");
					}
					String temp_params = sb.toString();
					params = temp_params.substring(0, temp_params.length() - 1);
				}
			}
			String full_url = url + "?" + params;
			logger.info("restful address : " + full_url);
			// 创建URL对象
			URL connURL = new URL(full_url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
			// 建立实际的连接
			httpConn.connect();

			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			if (httpConn.getResponseCode() == 200) {
				is = httpConn.getInputStream();
				baos = new ByteArrayOutputStream();
				int len = -1;
				byte[] buf = new byte[4096];

				while ((len = is.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				baos.flush();
				buffer.append(baos.toString("UTF-8"));
			} else {
				throw new RuntimeException(
						"error code " + httpConn.getResponseCode() + ":" + httpConn.getResponseMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return buffer.toString();
	}

	public static String sendGet(String url, Map<String, Object> params, Map<String, Object> header) {
		if (!StringUtil.isBlank(url)) {
			Long random = System.currentTimeMillis();
			if (url.indexOf("?") == -1) {
				url = url + "?random=" + random;
			} else {
				url = url + "&random=" + random;
			}
		}
		String apiUrl = url;
		StringBuffer param = new StringBuffer();
		if (params != null) {
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
		}
		apiUrl += param;
		logger.info(apiUrl);
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(apiUrl);

			if (null != header) {
				for (Map.Entry<String, Object> entry : header.entrySet()) {
					httpGet.addHeader(entry.getKey(), (String) entry.getValue());
				}

			}

			HttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			logger.info("HttpGet statusCode: " + statusCode);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = IOUtils.toString(instream, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String sendPut(String address, String param) {
		logger.info("restful address : " + address);
		logger.info("param : " + param);
		String result = "";
		try {
			result = HttpClientUtil.sendPut(address, param, null);
			logger.info("result : " + result);
		} catch (IOException e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
		} catch (URISyntaxException e) {
			String errorMessage = e.getMessage();
			logger.error(errorMessage, e);
		}
		// 请求发生异常后，result 为 空
		return result;
	}

	public static String sendPut(String url, String data, Map<String, String> header)
			throws IOException, URISyntaxException {
		if (!StringUtil.isBlank(url)) {
			Long random = System.currentTimeMillis();
			if (url.indexOf("?") == -1) {
				url = url + "?random=" + random;
			} else {
				url = url + "&random=" + random;
			}
		}
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(new URL(url).toURI());
		String charset = "utf-8";
		if (header != null) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpPut.setHeader(entry.getKey(), entry.getValue());
				if ("charset".equals(entry.getKey())) {
					charset = entry.getValue();
				}
			}

		}
		StringEntity dataEntity = new StringEntity(data, ContentType.APPLICATION_JSON);
		httpPut.setEntity(dataEntity);
		CloseableHttpResponse response = httpclient.execute(httpPut);
		try {
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
				StringBuffer buffer = new StringBuffer();
				String tempStr;
				while ((tempStr = reader.readLine()) != null)
					buffer.append(tempStr);
				return buffer.toString();
			} else {
				throw new RuntimeException("error code " + response.getStatusLine().getStatusCode() + ":"
						+ response.getStatusLine().getReasonPhrase());
			}

		} finally {
			response.close();
			httpclient.close();
		}
	}

	public static void main(String[] args) throws IOException, URISyntaxException {

		// String result = HttpClientUtil.sendPostRequest(
		// "http://10.1.228.222:15101/serviceAgent/rest/ipaas/dubbo-testA/dubbo-test/testServiceMethod",
		// "{\"count\":1,\"SrcSysCode\":\"1005\"}");
		// System.out.println("++++++++++++ " + result);

		Map<String, String> headerValue = new HashMap<String, String>();
		headerValue.put("appkey", "03379980ba661ad9ba678d386e39c1ca");
		headerValue.put("sign", "12345");

		String result = HttpClientUtil.sendPost("http://10.1.235.246:8081/serviceAgent/http/BIS-3A-USERADD",
				"{\"loginname\":\"xj109\",\"orgcode\":\"4001\",\"password\":\"abcdEFG123\",\"status\":\"active\"}",
				headerValue);
		System.out.println("++++++++++++  " + result);

		// Map<String, String> headerValue = new HashMap<String, String>();
		// headerValue.put("appkey","893f09f81402f23bf5b2bd5596d668b0");
		// headerValue.put("sign","12346");
		//
		// String result = HttpClientUtil.sendPostRequest(
		// "http://10.1.235.246:8081/serviceAgent/http/RUNNER-QUERYCUSTIDBYPHONENUM-001",
		// "{\"tenantId\":\"HX\",\"custPhoneNum\":\"15930008252\"}",
		// headerValue);
		// System.out.println("++++++++++++ " + result);

	}
}
