package com.demo.dev.gjj.sdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class ApacheHttpUtil {
	/**
	 * 发送http GET请求
	 * @param url http地址
	 * @param param 传递变量
	 * @return
	 * @throws IOException
	 */
	public static String send_get(String url,String param){
		return send_get(url,param,null);
	}
	/**
	 * 发送http GET请求
	 * @param url http地址
	 * @param param 传递变量
	 * @param prop 请求参数
	 * @return
	 * @throws IOException
	 */
	public static String send_get(String url,String param,Map<String,String> prop){
		return send(url,"GET",param,prop);
	}
	/**
	 * 发送http POST请求
	 * @param url http地址
	 * @param param 传递变量
	 * @return
	 * @throws IOException
	 */
	public static String send_post(String url,String param){
		return send_post(url,param,null);
	}
	/**
	 * 发送http POST请求
	 * @param url http地址
	 * @param param 传递变量
	 * @param prop 请求参数
	 * @return
	 * @throws IOException
	 */
	public static String send_post(String url,String param,Map<String,String> prop){
		return send(url,"POST",param,prop);
	}
	/**
	 * 发送HTTP请求
	 * @param urlString http地址
	 * @param method 请求类型 GET或POST
	 * @param param 传递变量
	 * @param prop 请求参数
	 * @return
	 * @throws IOException
	 */
	public static String send(String urlString,String method,String param,Map<String,String> prop){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response= null;
		try{
			//设置请求和传输超时时间为10秒
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(70000)
					.setConnectTimeout(70000)
					.setConnectionRequestTimeout(70000).build();
			if (method.equalsIgnoreCase("GET")) {
				//get请求
				if(param!=null&&param.length()>0){
					if( urlString.indexOf("?")>-1 ){
						urlString += "&" + param;
					}else{
						urlString += "?" + param;
					}
				}
				HttpGet httpget = new HttpGet(urlString);
				httpget.setConfig(requestConfig);
				if( prop!=null ){
		        	for(String key : prop.keySet()){
		        		httpget.setHeader(key, prop.get(key));
		        	}
				}
				response = httpclient.execute(httpget);
	        }else{
	        	//post请求
	        	HttpPost httppost = new HttpPost(urlString);
	        	httppost.setConfig(requestConfig);
	        	if( prop!=null ){
		        	for(String key : prop.keySet()){
		        		httppost.setHeader(key, prop.get(key));
		        	}
				}
	        	if(param!=null&&param.length()>0){
	        		//HttpEntity entity = new StringEntity(param,"utf-8");
	        		String[] params=param.split("&");
	        		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        		for(int i=0;i<params.length;i++){
	        			//formparams.add(new BasicNameValuePair(params[i].split("=")[0], params[i].split("=").length>1?params[i].substring(params[i].indexOf("=")+1):""));
	        			formparams.add(new BasicNameValuePair(params[i].split("=")[0], params[i].split("=").length>1?params[i].substring(params[i].indexOf("=")+1):""));
	        		}
	        		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
	        		httppost.setEntity(entity);
	        	}
				response = httpclient.execute(httppost);
	        }
			
			if(response!=null&&response.getEntity()!=null){
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"utf-8"));
		        StringBuffer temp = new StringBuffer();
		        String sep=System.getProperty("line.separator");
		        String line = bufferedReader.readLine();
		        while (line != null) {  
		            temp.append(line).append(sep);
		            line = bufferedReader.readLine();
		        }
		        bufferedReader.close();
		        return temp.toString();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(response!=null){
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
