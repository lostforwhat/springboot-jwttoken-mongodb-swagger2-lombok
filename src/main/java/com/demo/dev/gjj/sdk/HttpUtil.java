package com.demo.dev.gjj.sdk;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HttpUtil {
	
		protected static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

		public static String encKey = "05Xd318IsU1xoI1j";
	    
		public static String centerId = "00071700";//城市代码
		public static String userId = CryptoAes.encrypt("222018819800412", encKey);//渠道登录用户名,需加密
		public static String usertype = "10";//渠道登录用户名类型
		public static String deviceType = "3";//设备类型1-iOS 2-Android 3-pc
		public static String deviceToken = "";//设备识别码 
		public static String currenVersion = "1.0";//对应渠道当前版本号，默认上传1.0
		public static String channel = "82";//渠道标识 
		public static String appid = CryptoAes.encrypt("zhbapp82",encKey);//分配应用标识,需加密
		public static String appkey = CryptoAes.encrypt("d2b1f3df238803b42b52223a88ee1faf",encKey);//应用KEY,需加密
		public static String appToken= "";//服务器端同步最新APPTOKEN：使用“应用标识”与“应用KEY”信息到综合	服务平台取得最新APPTOKEN，超时需要重新获取
		public static String clientIp = "";	//客户端IP地址 
		//public static String buzType= "6533";//#服务编码 （根据具体交易配置不同编码）
        
		//public static String headpara = centerId + "," + userId + "," + usertype + "," + deviceType + "," + deviceToken + "," + currenVersion + "," + channel + "," + appid + "," + appkey + "," + appToken + "," + clientIp + "," + buzType;

		public static String send(String action, String buzType, String params, String paramValues){
			try {
				clientIp = InetAddress.getLocalHost().getHostAddress().toString();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			String headpara = "centerId,userId,usertype,deviceType,deviceToken,currenVersion,channel,appid,appkey,appToken,clientIp,buzType";
			
			String headparaMD5   = "centerId=00071700&userId="+userId+"&usertype=10&deviceType=3&deviceToken=&currenVersion=1.0&channel="+channel+"&appid="+appid+"&appkey="+appkey+"&appToken=&clientIp="+clientIp+"&buzType=" + buzType;
			String uploadmessage = "centerId=00071700&userId="+userId+"&usertype=10&deviceType=3&deviceToken=&currenVersion=1.0&channel="+channel+"&appid="+appid+"&appkey="+appkey+"&appToken=&clientIp="+clientIp+"&buzType=" + buzType;
			headpara += params;
			headparaMD5 += paramValues;
			uploadmessage += paramValues;
			logger.info(headpara);
			logger.info(uploadmessage);
			//String url="http://192.168.0.110:1080/YBMAPZH/";
			String url = "http://10.27.0.16:7001/YBMAPZH/";  //测试
			//String url = "http://10.27.23.197:7003/YBMAPZH/";   //正式
			// 进行MD5二次加密
			try {
				headparaMD5 = RSASignature.sign(CryptoMd5.encrypt(headparaMD5), "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(headparaMD5);
			String realurl = url + action;
			Map<String, String> prop = new HashMap<String, String>();
			prop.put("headpara", headpara);
			prop.put("headparaMD5", headparaMD5);
			String ret = ApacheHttpUtil.send_post(realurl, uploadmessage, prop);
			
			logger.info(realurl);
			logger.info(ret);
			
			return ret;
		}
		
		public static String send(){
			String grzh = CryptoAes.encrypt("80356949",encKey);
			String zjhm = CryptoAes.encrypt("420521198410110098",encKey);
			String pwd = CryptoAes.encrypt("123456",encKey);
			String cfmqrypwd = CryptoAes.encrypt("123456",encKey);
			String password = CryptoAes.encrypt("123456",encKey);
			String newpassword = CryptoAes.encrypt("123456",encKey);
			String cfmqrypwd2 = CryptoAes.encrypt("123456",encKey);
			String newphone = CryptoAes.encrypt("18008608484", encKey);
			String oldphone = CryptoAes.encrypt("18008608485", encKey);
			//账号信息返显
			String res = send("appapi91333.json", "5021", ",grzh,channelSeq", "&grzh="+grzh+"&channelSeq=5");
			
			//验证码
//			String res = send("appapi91303.json", "6533", ",errreason,sjhm,xingming", "&errreason=手机号码绑定&sjhm=15623730076&xingming=文呈盼");
			//手机号码
//			String res = send("appapi50036.json", "5473", ",grzh,newPhone,oldphone,opcode,ywlx", "&grzh="+grzh+"&newPhone="+newphone+"&oldphone="+oldphone+"&opcode=1234&ywlx=06");
			//银行卡
//			String res = send("appapi91332.json", "5020", ",grzh,bankaccnum,bankcode,yhklx,ywlx,pwd,cfmqrypwd", "&grzh=80356949&bankaccnum=6217211807001051744&bankcode=0201&yhklx=01&ywlx=01&pwd="+pwd+"&cfmqrypwd=" + cfmqrypwd);
			//修改密码
//			String res = send("appapi50024.json", "5468", ",grzh,klx,ywlx,cardno,xingming,zjhm,password,newpassword,cfmqrypwd2", "&grzh="+grzh+"&klx=2&ywlx=1&cardno=6217211807001051744&xingming=程哲宇&zjhm="+zjhm+"&password="+password+"&newpassword="+newpassword+"&cfmqrypwd2="+cfmqrypwd2);
			//开户
//			String res = HttpUtil.send("appapi91334.json", "5020", ",grzh,bankaccnum,bankcode,yhklx,ywlx,pwd,cfmqrypwd,newphone,opcode,zjhm,channelSeq", "&grzh="+grzh+"&bankaccnum=6217211807001051744&bankcode=0210&yhklx=01&ywlx=01&pwd="+pwd+"&cfmqrypwd="+cfmqrypwd+"&newphone=18008608485&opcode=123456&zjhm=" + zjhm +"&channelSeq=2");
			
			return res;
		}
		
		public static void main(String args[]) {
			String result = send();
//			String result = CryptoAes.encrypt("password",encKey);
			System.out.println(result);
		}

}
