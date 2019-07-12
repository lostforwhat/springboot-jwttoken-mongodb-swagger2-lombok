package com.demo.dev.gjj;




import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.demo.dev.gjj.sdk.CryptoAes;
import com.demo.dev.gjj.sdk.HttpUtil;
import com.demo.dev.gjj.sdk.HttpUtilWT;


@RestController
@RequestMapping("/gjj")
public class GjjController {

	private String encKey = "05Xd318IsU1xoI1j";
	
	
	/**
	 * 获取实例号
	 * @return instance
	 */
	private String getSeq() {
		String params = "";
		String paramValues = "";
		String res = HttpUtil.send("appapi91009.json", "6015", params, paramValues);
		System.out.println(res);
		if(res != null) {
			JSONObject json = (JSONObject) JSONObject.parse(res);
			if("000000".equals(json.getString("recode"))) {
				String seq = json.getString("instance");
				return seq;
			}
			
		}
		return null;
	}
	
	/**
	 * 获取手机号码
	 * @param grzh
	 * @return
	 */
	@PostMapping("getPhone")

	public String getPhone(@RequestParam(value = "grzh") String grzh) {
		grzh = CryptoAes.encrypt(grzh, encKey);
		String params = ",grzh";
		String paramValues = "&grzh=" + grzh;
		String res = HttpUtil.send("appapi91330.json", "5456", params, paramValues);
		return res;
	}

	/**
	 * 个人账号信息返显
	 * 
	 * @param grzh
	 * @return
	 */
	@PostMapping("getInfo")

	public String getInfo(@RequestParam(value = "grzh") String grzh) {
		grzh = CryptoAes.encrypt(grzh, encKey);
		String params = ",grzh,channelSeq";
		String paramValues = "&grzh=" + grzh + "&channelSeq=" + getSeq();
		String res = HttpUtil.send("appapi91333.json", "5021", params, paramValues);
		return res;
	}
	
	/**
	 * 发送验证码
	 * @param sjhm
	 * @param xingming
	 * @param reason
	 * @return
	 */
	@PostMapping("sendCheckCode")
	
	public String sendCheckCode(@RequestParam(value = "sjhm") String sjhm, @RequestParam(value = "xingming") String xingming, @RequestParam(value = "reason") String reason) {
		String params = ",errreason,sjhm,xingming";
		String paramValues = "&errreason=" + reason + "&sjhm=" + sjhm + "&xingming=" + xingming;
		String res = HttpUtil.send("appapi91303.json", "6533", params, paramValues);
		return res;
	}

	/**
	 * 银行卡业务
	 * @param grzh
	 * @param bankaccnum
	 * @param bankcode
	 * @param yhklx
	 * @param pwd
	 * @param cfmqrypwd
	 * @param ywlx
	 * @return
	 */
	@PostMapping("bankcard")

	public String bdyhk(@RequestParam(value = "grzh") String grzh, @RequestParam(value = "bankaccnum") String bankaccnum, @RequestParam(value = "bankcode") String bankcode, @RequestParam(value = "yhklx") String yhklx, @RequestParam(value = "pwd") String pwd, @RequestParam(value = "cfmqrypwd") String cfmqrypwd, @RequestParam(value = "ywlx") String ywlx) {
		grzh = CryptoAes.encrypt(grzh, encKey);
		pwd = CryptoAes.encrypt(pwd, encKey);
		cfmqrypwd = CryptoAes.encrypt(cfmqrypwd, encKey);
		String params = ",grzh,bankaccnum,bankcode,yhklx,ywlx,pwd,cfmqrypwd";
		String paramValues = "&grzh="+grzh+"&bankaccnum="+bankaccnum+"&bankcode="+bankcode+"&yhklx="+yhklx+"&ywlx="+ywlx+"&pwd="+pwd+"&cfmqrypwd="+cfmqrypwd;
		String res = HttpUtil.send("appapi91332.json", "5020", params, paramValues);
		return res;
	}
	
	/**
	 * 手机号码业务
	 * @param grzh
	 * @param newphone
	 * @param oldphone
	 * @param opcode
	 * @param ywlx
	 * @return
	 */
	@PostMapping("phone")

	public String phone(@RequestParam(value = "grzh") String grzh, @RequestParam(value = "newphone")String newphone, @RequestParam(value = "oldphone")String oldphone, @RequestParam(value = "opcode")String opcode, @RequestParam(value = "ywlx")String ywlx) {
		grzh = CryptoAes.encrypt(grzh, encKey);
		newphone = CryptoAes.encrypt(newphone, encKey);
		oldphone = CryptoAes.encrypt(oldphone, encKey);
		String params = ",grzh,newPhone,oldphone,opcode,ywlx";
		String paramValues = "&grzh="+grzh+"&newPhone="+newphone+"&oldphone="+oldphone+"&opcode="+opcode+"&ywlx="+ywlx;
		String res = HttpUtil.send("appapi50036.json", "5473", params, paramValues);
		return res;
	}
	
	/**
	 * 修改交易密码
	 * @param grzh
	 * @param klx
	 * @param ywlx
	 * @param cardno
	 * @param xingming
	 * @param zjhm
	 * @param password
	 * @param newpassword
	 * @param cfmqrypwd2
	 * @return
	 */
	@PostMapping("password")

	public String password(@RequestParam(value = "grzh") String grzh, @RequestParam(value = "klx") String klx, @RequestParam(value = "ywlx") String ywlx, @RequestParam(value = "cardno") String cardno, @RequestParam(value = "xingming") String xingming, @RequestParam(value = "zjhm") String zjhm, @RequestParam(value = "password") String password, @RequestParam(value = "newpassword") String newpassword, @RequestParam(value = "cfmqrypwd2") String cfmqrypwd2) {
		grzh = CryptoAes.encrypt(grzh, encKey);
		zjhm = CryptoAes.encrypt(zjhm, encKey);
		password = CryptoAes.encrypt(password, encKey);
		newpassword = CryptoAes.encrypt(newpassword, encKey);
		cfmqrypwd2 = CryptoAes.encrypt(cfmqrypwd2, encKey);
		String params = ",grzh,klx,ywlx,cardno,xingming,zjhm,password,newpassword,cfmqrypwd2";
		String paramValues = "&grzh="+grzh+"&klx="+klx+"&ywlx="+ywlx+"&cardno="+cardno+"&xingming="+xingming+"&zjhm="+zjhm+"&password="+password+"&newpassword="+newpassword+"&cfmqrypwd2=" + cfmqrypwd2;
		String res = HttpUtil.send("appapi50024.json", "5468", params, paramValues);
		return res;
	}
	
	/**
	 * 
	 * @param grzh
	 * @param bankaccnum
	 * @param bankcode
	 * @param yhklx
	 * @param pwd
	 * @param cfmqrypwd
	 * @param ywlx
	 * @param newphone
	 * @param opcode
	 * @param zjhm
	 * @return
	 */
	@PostMapping("open")

	public String open(@RequestParam(value = "grzh") String grzh, @RequestParam(value = "bankaccnum") String bankaccnum, @RequestParam(value = "bankcode") String bankcode, @RequestParam(value = "yhklx") String yhklx, @RequestParam(value = "pwd") String pwd, @RequestParam(value = "cfmqrypwd") String cfmqrypwd, @RequestParam(value = "ywlx") String ywlx, @RequestParam(value = "newphone")String newphone, @RequestParam(value = "opcode")String opcode, @RequestParam(value = "zjhm") String zjhm, @RequestParam(value = "flag") String flag) {
		grzh = CryptoAes.encrypt(grzh, encKey);
		pwd = CryptoAes.encrypt(pwd, encKey);
		cfmqrypwd = CryptoAes.encrypt(cfmqrypwd, encKey);
		zjhm = CryptoAes.encrypt(zjhm, encKey);
		String params = ",grzh,bankaccnum,bankcode,yhklx,ywlx,pwd,cfmqrypwd,newphone,opcode,zjhm,flag,channelSeq";
		String paramValues = "&grzh="+grzh+"&bankaccnum="+bankaccnum+"&bankcode="+bankcode+"&yhklx="+yhklx+"&ywlx="+ywlx+"&pwd="+pwd+"&cfmqrypwd="+cfmqrypwd+"&newphone="+newphone+"&opcode="+opcode+"&zjhm=" + zjhm+"&flag=" + flag + "&channelSeq=" + getSeq();
		String res = HttpUtil.send("appapi91334.json", "5020", params, paramValues);
		return res;
	}
	
	@PostMapping("query_info")

	public String query_info(@RequestParam(value = "dwzh") String dwzh, @RequestParam(value = "grzh") String grzh, @RequestParam(value = "zjhm") String zjhm) {
		grzh = CryptoAes.encrypt(grzh, encKey);
	    zjhm = CryptoAes.encrypt(zjhm, encKey);
		String params = ",dwzh,grzh,zjhm";
		String paramValues = "&dwzh="+dwzh+"&grzh="+grzh+"&zjhm=" + zjhm;
		String res = HttpUtil.send("appapi00101.json", "5001", params, paramValues);
		return res;
	}
	
	/**
	 * 获取婚姻信息
	 * @param sfzhm
	 * @param xingming
	 * @return
	 */
	@PostMapping("getMarriedInfo")

	public String getMarriedInfo(@RequestParam(value = "sfzhm") String sfzhm, @RequestParam(value = "xingming") String xingming) {
		//sfzhm = CryptoAes.encrypt(sfzhm, encKey);
		//xingming = "程哲宇";
		String params = ",sfzhm,xingming";
		String paramValues = "&sfzhm="+sfzhm+"&xingming="+xingming;
		String res = HttpUtil.send("appapi91313.json", "6542", params, paramValues);
		return res;
	}
	
	/**
	 * 获取房屋信息
	 * @param sfzhm
	 * @param xingming
	 * @param zjhm
	 * @return
	 */
	@PostMapping("getHouseInfo")

	public String getHouseInfo(@RequestParam(value = "qydm") String qydm, @RequestParam(value = "xingming") String xingming, @RequestParam(value = "zjhm") String zjhm) {
		//sfzhm = CryptoAes.encrypt(sfzhm, encKey);
		String channelSeq = getSeq();
		String params = ",qydm,xingming,zjhm,channelSeq";
		String paramValues = "&qydm="+qydm+"&xingming="+xingming+"&zjhm="+zjhm+"&channelSeq="+ channelSeq;
		String res = HttpUtil.send("appapi91314.json", "6539", params, paramValues);
		if(res != null) {
			JSONObject json = (JSONObject) JSONObject.parse(res);
			if("000000".equals(json.getString("recode"))) {
				json.put("instance", channelSeq);
				res = json.toJSONString();
			}
			
		}
		return res;
	}
	
	/**
	 * 获取房屋数量
	 * @param 实例号
	 * @return
	 */
	@PostMapping("getHouseCount")

	public String getHouseCount(@RequestParam(value = "instance") String instance) {
		//sfzhm = CryptoAes.encrypt(sfzhm, encKey);
		String channelSeq = instance;
		String params = ",instance,channelSeq";
		String paramValues = "&instance="+instance+"&channelSeq="+ channelSeq;
		String res = HttpUtil.send("appapi91310.json", "6539", params, paramValues);
		return res;
	}
	/**
	 * 提取在途检查
	 * @param 
	 * @return
	 */
	@PostMapping("tiquCheck")

	public String tiquCheck(@RequestParam(value = "grzh") String grzh) {
		grzh = CryptoAes.encrypt(grzh, encKey);
		String channelSeq = getSeq();
		String params = ",grzh,channelSeq";
		String paramValues = "&grzh="+grzh+"&channelSeq="+ channelSeq;
		String res = HttpUtil.send("appapi56003.json", "6557", params, paramValues);
		return res;
	}
	/**
	 * 
	 * @param begpayym：起始汇缴年月
	 * @param cfmqrypwd：确认密码
	 * @param dwdz：单位地址
	 * @param dwdzxx：单位电子信箱
	 * @param dwfrdbxm：单位法人代表姓名
	 * @param dwfrdbzjhm：单位法人代表证件号码
	 * @param dwfrdbzjlx：单位法人代表证件类型
	 * @param dwfxr：单位发薪日
	 * @param dwjcbl：单位缴存比例
	 * @param dwjjlx：单位经济类型
	 * @param dwlsgx：单位隶属关系
	 * @param dwmc：单位名称
	 * @param dwslrq：单位设立日期
	 * @param dwsshy：单位所属行业
	 * @param dwyb：单位邮编
	 * @param flag：标志
	 * @param fundsouflag：资金来源标志
	 * @param grjcbl：个人缴存比例
	 * @param jbrgddhhm：经办人固定电话号码
	 * @param jbrsjhm：经办人手机号码
	 * @param jbrxm：经办人姓名
	 * @param jbrzjhm：经办人证件号码
	 * @param jbrzjlx：经办人证件类型
	 * @param licensenum：营业执照号码
	 * @param linkmanmsn：联系人MSN
	 * @param linkmanqq：联系人QQ
	 * @param pwd：密码
	 * @param styhdm：受托银行代码
	 * @param styhmc：受托银行名称
	 * @param unitareacode：所在地区
	 * @param unitkind：单位性质
	 * @param zzjgdm：组织机构代码
	 * 
	 * @return
	 */
	@PostMapping("openComAcc")

	public String openComAcc(@RequestParam(value = "begpayym") String begpayym,//begpayym：起始汇缴年月
							 @RequestParam(value = "cfmqrypwd") String cfmqrypwd,//cfmqrypwd：确认密码
							 @RequestParam(value = "dwdz") String dwdz,//dwdz：单位地址
							 @RequestParam(value = "dwdzxx") String dwdzxx,//dwdzxx：单位电子信箱
							 @RequestParam(value = "dwfrdbxm") String dwfrdbxm,//dwfrdbxm：单位法人代表姓名
							 @RequestParam(value = "dwfrdbzjhm") String dwfrdbzjhm,//dwfrdbzjhm：单位法人代表证件号码
							 @RequestParam(value = "dwfrdbzjlx") String dwfrdbzjlx,//dwfrdbzjlx：单位法人代表证件类型
							 @RequestParam(value = "dwfxr") String dwfxr,//dwfxr：单位发薪日
							 @RequestParam(value = "dwjcbl") String dwjcbl,//dwjcbl：单位缴存比例
							 @RequestParam(value = "dwjjlx") String dwjjlx,//dwjjlx：单位经济类型
							 @RequestParam(value = "dwlsgx") String dwlsgx,//dwlsgx：单位隶属关系
							 @RequestParam(value = "dwmc") String dwmc,//dwmc：单位名称
							 @RequestParam(value = "dwslrq") String dwslrq,//dwslrq：单位设立日期
							 @RequestParam(value = "dwsshy") String dwsshy,//dwsshy：单位所属行业
							 @RequestParam(value = "dwyb") String dwyb,//dwyb：单位邮编
							 @RequestParam(value = "flag") String flag,//flag：标志
							 @RequestParam(value = "fundsouflag") String fundsouflag,//fundsouflag：资金来源标志
							 @RequestParam(value = "grjcbl") String grjcbl,//grjcbl：个人缴存比例
							 @RequestParam(value = "jbrgddhhm") String jbrgddhhm,//jbrgddhhm：经办人固定电话号码
							 @RequestParam(value = "jbrsjhm") String jbrsjhm,//jbrsjhm：经办人手机号码
							 @RequestParam(value = "jbrxm") String jbrxm,//jbrxm：经办人姓名
							 @RequestParam(value = "jbrzjhm") String jbrzjhm,//jbrzjhm：经办人证件号码
							 @RequestParam(value = "jbrzjlx") String jbrzjlx,//jbrzjlx：经办人证件类型
							 @RequestParam(value = "licensenum") String licensenum,//licensenum：营业执照号码
							 @RequestParam(value = "linkmanmsn") String linkmanmsn,//linkmanmsn：联系人MSN
							 @RequestParam(value = "linkmanqq") String linkmanqq,//linkmanqq：联系人QQ
							 @RequestParam(value = "pwd") String pwd,//pwd：密码
							 @RequestParam(value = "styhdm") String styhdm,//styhdm：受托银行代码
							 @RequestParam(value = "styhmc") String styhmc,//styhmc：受托银行名称
							 @RequestParam(value = "unitareacode") String unitareacode,//unitareacode：所在地区
							 @RequestParam(value = "unitkind") String unitkind,//unitkind：单位性质
							 @RequestParam(value = "zzjgdm") String zzjgdm,//zzjgdm：组织机构代码
							 @RequestParam(value = "accinstcode") String accinstcode//accinstcode：账户所在机构
			) {
		dwfrdbzjhm = CryptoAes.encrypt(dwfrdbzjhm, encKey);
		jbrzjhm = CryptoAes.encrypt(jbrzjhm, encKey);
		pwd = CryptoAes.encrypt(pwd, encKey);
		cfmqrypwd = CryptoAes.encrypt(cfmqrypwd, encKey);
		String params = ",begpayym,cfmqrypwd,dwdz,dwdzxx,dwfrdbxm,dwfrdbzjhm,dwfrdbzjlx,dwfxr,dwjcbl,dwjjlx,dwlsgx"
		+",dwmc,dwslrq,dwsshy,dwyb,flag,fundsouflag,grjcbl,jbrgddhhm,jbrsjhm,jbrxm,jbrzjhm,jbrzjlx,licensenum,"
		+"linkmanmsn,linkmanqq,pwd,styhdm,styhmc,unitareacode,unitkind,zzjgdm,accinstcode,channelSeq";
		String paramValues = "&begpayym="+begpayym + "&cfmqrypwd="+cfmqrypwd + "&dwdz="+dwdz
				+"&dwdzxx="+dwdzxx + "&dwfrdbxm="+dwfrdbxm + "&dwfrdbzjhm="+dwfrdbzjhm
				+"&dwfrdbzjlx="+dwfrdbzjlx + "&dwfxr="+dwfxr + "&dwjcbl="+dwjcbl
				+"&dwjjlx="+dwjjlx + "&dwlsgx="+dwlsgx + "&dwmc="+dwmc
				+"&dwslrq="+dwslrq + "&dwsshy="+dwsshy + "&dwyb="+dwyb
				+"&flag="+flag + "&fundsouflag="+fundsouflag + "&grjcbl="+grjcbl
				+"&jbrgddhhm="+jbrgddhhm + "&jbrsjhm="+jbrsjhm + "&jbrxm="+jbrxm
				+"&jbrzjhm="+jbrzjhm + "&jbrzjlx="+jbrzjlx + "&licensenum="+licensenum
				+"&linkmanmsn="+linkmanmsn + "&linkmanqq="+linkmanqq + "&pwd="+pwd
				+"&styhdm="+styhdm + "&styhmc="+styhmc + "&unitareacode="+unitareacode
				+"&unitkind="+unitkind + "&zzjgdm="+zzjgdm + "&accinstcode="+accinstcode + "&channelSeq=" + getSeq();
		String res = HttpUtilWT.send("appapi03803.json", "6556", params, paramValues);
		return res;
	}
	/**
	 * 租房提取信息反显
	 * @param grzh:个人账户
	 * @param marstatus:婚姻状态
	 * @param matecertinum:配偶证件号码
	 * @param matename:配偶姓名
	 * @param zjhm:身份证号码
	 * 
	 * @return
	 */
	@PostMapping("get_zf_info")

	public String get_zf_info(@RequestParam(value = "grzh") String grzh,
			                  @RequestParam(value = "marstatus") String marstatus,
			                  @RequestParam(value = "matecertinum") String matecertinum,
			                  @RequestParam(value = "matename") String matename,
			                  @RequestParam(value = "zjhm") String zjhm) 
	{
		grzh= CryptoAes.encrypt(grzh, encKey);
		zjhm= CryptoAes.encrypt(zjhm, encKey);
		String params = ",drawreason,grzh,marstatus,matecertinum,matename,zjhm";
		String paramValues = "&drawreason=004&grzh="+grzh+"&marstatus="+marstatus+"&matecertinum="+matecertinum+"&matename="+matename+"&zjhm="+ zjhm;
		String res = HttpUtil.send("appapi91307.json", "6531", params, paramValues);
		return res;
	}
	/**
	 * 绑卡手机号及密码检查
	 * @param grzh:个人账户
	 * @param marstatus:婚姻状态
	 * @param matecertinum:配偶证件号码
	 * @param matename:配偶姓名
	 * @param zjhm:身份证号码
	 * 
	 * @return
	 */
	@PostMapping("tq_mm_check")

	public String zftq_mm_check(@RequestParam(value = "busipwd") String busipwd,
			                  @RequestParam(value = "grzh") String grzh,
			                  @RequestParam(value = "rspcode") String rspcode)
			               
	{
		busipwd= CryptoAes.encrypt(busipwd,encKey);
		grzh= CryptoAes.encrypt(grzh, encKey);
		rspcode= CryptoAes.encrypt(rspcode, encKey);
	    String params = ",busipwd,grzh,rspcode";
		String paramValues = "&busipwd="+busipwd+"&grzh="+grzh+"&rspcode="+rspcode;
		String res = HttpUtil.send("appapi91302.json", "6532", params, paramValues);
		return res;
	}
	/**
	 * 提取提交(公共方法)
	 * @param drawamt:用户录入的金额
	 * @param drawreasoncode1:提取依据
	 * @param firstflag:提取依据是否首次办理 2:首次  1：非首次
	 * @param grzh:个人账户
	 * @param grzhye：个人账户余额
	 * @param inputamt:客户录入的提取金额 
	 * @param linkphone:手机号码
	 * @param payeebankacc0:收款账户
	 * @param payeebankcode:收款银行编码
	 * @param payeebankname
	 * @param payerbankcode 
	 * @param procode
	 * @param xingming
	 * @param zjhm
	 * @param money
	 * @param payeebankaccnm0
	 * @param marstatus
	 * @param matecertinum
	*/
	@PostMapping("tq_submit")
	

	public String tq_submit(@RequestParam(value = "drawamt") String drawamt,
			                  @RequestParam(value = "drawreasoncode1") String drawreasoncode1,
			                  @RequestParam(value = "firstflag") String firstflag,
			                  @RequestParam(value = "grzh") String grzh,
			                  @RequestParam(value = "grzhye") String grzhye,
			                  @RequestParam(value = "inputamt") String inputamt,
			                  @RequestParam(value = "linkphone") String linkphone,
			                  @RequestParam(value = "payeebankacc0") String payeebankacc0,
			                  @RequestParam(value = "payeebankaccnm0") String payeebankaccnm0,
			                  @RequestParam(value = "payeebankcode") String payeebankcode,
			                  @RequestParam(value = "payeebankname") String payeebankname,
			                  @RequestParam(value = "payerbankcode") String payerbankcode,
			                  @RequestParam(value = "procode") String procode,
			                  @RequestParam(value = "drawreason") String drawreason,
			                  @RequestParam(value = "xingming") String xingming,
			                  @RequestParam(value = "zjhm") String zjhm,
			                  @RequestParam(value = "money") String money,
			                  @RequestParam(value = "marstatus") String marstatus,
			                  @RequestParam(value = "matecertinum") String matecertinum,
			                  @RequestParam(value = "matename") String matename,
			                  @RequestParam(value = "tqyy") String tqyy,
			                  @RequestParam(value = "channelSeq") String channelSeq,
			                  @RequestParam(value = "stepseqno") String stepseqno,
			                  @RequestParam(value = "apprnum") String apprnum,
			                  @RequestParam(value = "increbal") String increbal,
			                  @RequestParam(value = "keepbal") String keepbal,
			                  @RequestParam(value = "calintamt") String calintamt,
			                  @RequestParam(value = "prodcode") String prodcode,
			                  @RequestParam(value = "agentop") String agentop,
			                  @RequestParam(value = "inputop") String inputop
			                 )
			               
	{
	    //String agentop="5555";//网页8888， 移动端5555
	    String drawcanflag="0";
	    //String inputop="5555";//网页8888， 移动端5555
	    String paperrelation="01";
	    String porttype="2";
	    String settlemode="5";
	    if(channelSeq.length()<2)
	    {
	    	channelSeq=getSeq();
	    }
	    //String stepseqno="1";

	    matecertinum= CryptoAes.encrypt(matecertinum, encKey);
		money= CryptoAes.encrypt(money,encKey);
		grzh= CryptoAes.encrypt(grzh, encKey);
		zjhm= CryptoAes.encrypt(zjhm, encKey);
	    String params = ",agentop,marstatus,matecertinum,matename,drawamt,drawcanflag,drawreason,drawreasoncode1"
	    		+ ",firstflag,grzh,grzhye,inputamt,inputop"
	            + ",linkphone,paperrelation,payeebankacc0,payeebankaccnm0,payeebankcode"
	            + ",payeebankname,payerbankcode,porttype,procode,settlemode"
	            + ",stepseqno,tqyy,xingming,zjhm,money,channelSeq,apprnum,increbal,keepbal,calintamt,prodcode";
		String paramValues = "&agentop="+agentop+"&marstatus="+marstatus+"&matecertinum="+matecertinum+"&matename="+matename+"&drawamt="+drawamt+"&drawcanflag="+drawcanflag+"&drawreason="+drawreason+"&drawreasoncode1="+drawreasoncode1
				+"&firstflag="+firstflag+"&grzh="+grzh+"&grzhye="+grzhye+"&inputamt="+inputamt+"&inputop="+inputop
				+"&linkphone="+linkphone+"&paperrelation="+paperrelation+"&payeebankacc0="+payeebankacc0+"&payeebankaccnm0="+payeebankaccnm0+"&payeebankcode="+payeebankcode
				+"&payeebankname="+payerbankcode+"&payerbankcode="+payerbankcode+"&porttype="+porttype+"&procode="+procode+"&settlemode="+settlemode
		        +"&stepseqno="+stepseqno+"&tqyy="+tqyy+"&xingming="+xingming+"&zjhm="+zjhm+"&money="+money+"&channelSeq=" + channelSeq+"&apprnum=" + apprnum+"&increbal=" + increbal+"&keepbal=" + keepbal+"&calintamt=" + calintamt+"&prodcode=" + prodcode;
		String res = HttpUtil.send("appapi91219.json", "6518", params, paramValues);
		JSONObject json = (JSONObject) JSONObject.parse(res);
		json.put("channelSeq", channelSeq);
		res = json.toJSONString();
		return res;
	}
	/**
	 * 撤销贷款申请第一步
	 * @param apprnum
	 * @return
	 */
	@PostMapping("chexiao_step1")

	public String chexiao_step1(@RequestParam(value = "apprnum") String apprnum,@RequestParam(value = "channelSeq") String channelSeq) {
		//apprnum= CryptoAes.encrypt(apprnum, encKey);
		String params = ",apprnum,channelSeq";
		String paramValues = "&apprnum="+apprnum+"&channelSeq="+ channelSeq;
		String res = HttpUtil.send("appapi91222.json", "6521", params, paramValues);
		return res;
	}
	/**
	 * 偿还贷款提取信息返显
	 * @param grzh
	 * @param zjhm
	 * @return
	 */
	@PostMapping("get_chdk_info")

	public String get_chdk_info(@RequestParam(value = "grzh") String grzh, @RequestParam(value = "zjhm") String zjhm, @RequestParam(value = "drawreason") String drawreason) {
		grzh= CryptoAes.encrypt(grzh, encKey);
		zjhm= CryptoAes.encrypt(zjhm, encKey);
		String params = ",drawreason,grzh,zjhm";
		String paramValues = "&drawreason="+drawreason+"&grzh="+grzh+"&zjhm="+ zjhm;
		String res = HttpUtil.send("appapi91311.json", "6540", params, paramValues);
		return res;
	}
	
	/**
	 * 商贷委扣提取查询
	 * @param bankcode
	 * @param zjhm
	 * @param begdate
	 * @param dkzh
	 * @param enddate
	 * @param xingming
	 * @return
	 */
	@PostMapping("get_sdwktqcx")

	public String get_sdwktqcx(@RequestParam(value = "bankcode") String bankcode, @RequestParam(value = "zjhm") String zjhm, @RequestParam(value = "begdate") String begdate, 
			@RequestParam(value = "dkzh") String dkzh, @RequestParam(value = "enddate") String enddate, @RequestParam(value = "xingming") String xingming) {
		
		zjhm= CryptoAes.encrypt(zjhm, encKey);
		String params = ",begdate,bankcode,zjhm,dkzh,enddate,xingming";
		String paramValues = "&begdate="+begdate+"&bankcode="+bankcode+"&zjhm="+ zjhm+"&dkzh="+ dkzh+"&enddate="+ enddate+"&xingming="+ xingming;
		String res = HttpUtil.send("appapi03410.json", "6531", params, paramValues);
		return res;
	}
	
	/**
	 * 更新商贷信息
	 * @param dkyhdm
	 * @param zjhm
	 * @param begdate
	 * @param dkzh
	 * @param enddate
	 * @param xingming
	 * @param drawreason
	 * @param procode
	 * @return
	 */
	@PostMapping("updateSdxx")

	public String updateSdxx(@RequestParam(value = "dkyhdm") String dkyhdm, @RequestParam(value = "zjhm") String zjhm, @RequestParam(value = "begdate") String begdate, 
			@RequestParam(value = "dkzh") String dkzh, @RequestParam(value = "enddate") String enddate, @RequestParam(value = "xingming") String xingming, 
			@RequestParam(value = "drawreason") String drawreason, @RequestParam(value = "procode") String procode) {
		
		zjhm= CryptoAes.encrypt(zjhm, encKey);
		String params = ",begdate,dkyhdm,zjhm,dkzh,enddate,xingming,drawreason,procode";
		String paramValues = "&begdate="+begdate+"&dkyhdm="+dkyhdm+"&zjhm="+ zjhm+"&dkzh="+ dkzh+"&enddate="+ enddate+"&xingming="+ xingming+"&drawreason="+ drawreason+"&procode="+ procode;
		String res = HttpUtil.send("appapi91320.json", "6531", params, paramValues);
		return res;
	}
	
	/**
	 * 计算公积金贷款，物业费提取可用金额
	 * @param grzh
	 * @param zjhm
	 * @param drawreason
	 * @param famt
	 * @return
	 */
	@PostMapping("getDrawamt")

	public String getDrawamt(@RequestParam(value = "grzh") String grzh, @RequestParam(value = "zjhm") String zjhm, @RequestParam(value = "drawreason") String drawreason, @RequestParam(value = "famt") String famt) {
		grzh= CryptoAes.encrypt(grzh, encKey);
		zjhm= CryptoAes.encrypt(zjhm, encKey);
		String params = ",drawreason,grzh,zjhm,famt";
		String paramValues = "&drawreason="+drawreason+"&grzh="+grzh+"&zjhm="+ zjhm+"&famt="+ famt;
		String res = HttpUtil.send("appapi00402.json", "6538", params, paramValues);
		return res;
	}
	
	/**
	 * 公积金明细
	 * @param dwzh
	 * @param grzh
	 * @param zjhm
	 * @param begdate
	 * @param enddate
	 * @param inaccflag
	 * @param summarycode
	 * @return
	 */
	@PostMapping("get_gjj_detail")

	public String get_gjj_detail(@RequestParam(value = "dwzh") String dwzh, @RequestParam(value = "grzh") String grzh, @RequestParam(value = "zjhm") String zjhm, @RequestParam(value = "begdate") String begdate, @RequestParam(value = "enddate") String enddate, @RequestParam(value = "inaccflag") String inaccflag, @RequestParam(value = "summarycode") String summarycode) {
		grzh= CryptoAes.encrypt(grzh, encKey);
		zjhm= CryptoAes.encrypt(zjhm, encKey);
		String params = ",dwzh,grzh,zjhm,begdate,enddate,inaccflag,summarycode";
		String paramValues = "&dwzh="+dwzh+"&grzh="+grzh+"&zjhm="+ zjhm+"&begdate="+ begdate+"&enddate="+ enddate+"&inaccflag="+ inaccflag+"&summarycode="+ summarycode;
		String res = HttpUtil.send("appapi00201.json", "5002", params, paramValues);
		return res;
	}
	
	/**
	 * 贷款信息
	 * @param grzh
	 * @param jkhtbh
	 * @return
	 */
	@PostMapping("get_gjjdk_info")

	public String get_gjjdk_info(@RequestParam(value = "grzh") String grzh, @RequestParam(value = "jkhtbh") String jkhtbh) {
		grzh= CryptoAes.encrypt(grzh, encKey);
		String params = ",grzh,jkhtbh";
		String paramValues = "&grzh="+grzh+"&jkhtbh="+ jkhtbh;
		String res = HttpUtil.send("appapi00601.json", "5071", params, paramValues);
		return res;
	}
	
	/**
	 * 贷款详细
	 * @param begdate
	 * @param enddate
	 * @param loancontrcode
	 * @return
	 */
	@PostMapping("get_gjjdk_detail")

	public String get_gjjdk_detail(@RequestParam(value = "begdate") String begdate, @RequestParam(value = "enddate") String enddate, @RequestParam(value = "loancontrcode") String loancontrcode) {
		
		String params = ",begdate,enddate,loancontrcode";
		String paramValues = "&begdate="+begdate+"&enddate="+ enddate+"&loancontrcode="+ loancontrcode;
		String res = HttpUtil.send("appapi00701.json", "5072", params, paramValues);
		return res;
	}
	
	/**
	 * 当年还款计划查询
	 * @param begdate
	 * @param enddate
	 * @param loancontrcode
	 * @return
	 */
	@PostMapping("get_hkjh")

	public String get_hkjh(@RequestParam(value = "begdate") String begdate, @RequestParam(value = "enddate") String enddate, @RequestParam(value = "loancontrcode") String loancontrcode) {
		
		String params = ",begdate,enddate,loancontrcode";
		String paramValues = "&begdate="+begdate+"&enddate="+ enddate+"&loancontrcode="+ loancontrcode;
		String res = HttpUtil.send("appapi00702.json", "5073", params, paramValues);
		return res;
	}
	
	/**
	 * 贷款审批进度查询
	 * @param jkrgjjzh
	 * @param jkrxm
	 * @param jkrzjh
	 * @return
	 */
	@PostMapping("get_dkspjd")

	public String get_dkspjd(@RequestParam(value = "jkrgjjzh") String jkrgjjzh, @RequestParam(value = "jkrxm") String jkrxm, @RequestParam(value = "jkrzjh") String jkrzjh) {
		jkrgjjzh = CryptoAes.encrypt(jkrgjjzh, encKey);
		jkrzjh = CryptoAes.encrypt(jkrzjh, encKey);
		String params = ",jkrgjjzh,jkrxm,jkrzjh";
		String paramValues = "&jkrgjjzh="+jkrgjjzh+"&jkrxm="+ jkrxm+"&jkrzjh="+ jkrzjh;
		String res = HttpUtil.send("appapi91305.json", "6535", params, paramValues);
		return res;
	}
	
	/*--------------------以下为贷款申请预约接口----------------------------*/
	
	/**
	 * 获取贷款银行信息
	 * @param accinstcode
	 * @return
	 */
	@PostMapping("getBankInfo")

	public String getBankInfo(@RequestParam(value = "accinstcode") String accinstcode) {
		
		String params = ",accinstcode";
		String paramValues = "&accinstcode="+accinstcode;
		String res = HttpUtil.send("appapi91340.json", "5016", params, paramValues);
		return res;
	}
	
	/**
	 * 获取楼盘信息
	 * @param accinstcode
	 * @param projectname
	 * @return
	 */
	@PostMapping("getLpInfo")

	public String getLpInfo(@RequestParam(value = "accinstcode") String accinstcode, @RequestParam(value = "projectname") String projectname) {
		
		String params = ",accinstcode,projectname";
		String paramValues = "&accinstcode="+accinstcode+"&projectname="+projectname;
		String res = HttpUtil.send("appapi91341.json", "5081", params, paramValues);
		return res;
	}
	
	/**
	 * 贷款申请预约
	 * @param bankcode
	 * @param dklx
	 * @param flag
	 * @param instcode
	 * @param polenum
	 * @param xmmc
	 * @param zjhm
	 * @return
	 */
	@PostMapping("dksqyy")

	public String dksqyy(@RequestParam(value = "bankcode") String bankcode, @RequestParam(value = "dklx") String dklx, 
			@RequestParam(value = "flag") String flag, @RequestParam(value = "instcode") String instcode,
			@RequestParam(value = "polenum") String polenum,@RequestParam(value = "xmmc") String xmmc,@RequestParam(value = "zjhm") String zjhm) {
		
		String params = ",bankcode,dklx,flag,instcode,polenum,xmmc,zjhm";
		String paramValues = "&bankcode="+bankcode+"&dklx="+ dklx+"&flag="+ flag+"&instcode="+ instcode+"&polenum="+ polenum+"&xmmc="+ xmmc+"&zjhm="+ zjhm;
		String res = HttpUtil.send("appapi91325.json", "6549", params, paramValues);
		return res;
	}
	
	/**
	 * 贷款预约前检查
	 * @param agentbankcode
	 * @param bankaddr
	 * @param bankcode
	 * @param closedate
	 * @param clsaccdate
	 * @param ctlflag
	 * @param dcflag
	 * @param dklx
	 * @param flag
	 * @param housetype
	 * @param instcode
	 * @param jkrxm
	 * @param polenum
	 * @param ratetype
	 * @param sjhm
	 * @param speflag
	 * @param stepseqno
	 * @param transdate
	 * @param type
	 * @param xmmc
	 * @param zjhm
	 * @return
	 */
	@PostMapping("dksqyy_check")

	public String dksqyy_check(@RequestParam(value = "agentbankcode") String agentbankcode, @RequestParam(value = "bankaddr") String bankaddr, 
			@RequestParam(value = "bankcode") String bankcode, @RequestParam(value = "closedate") String closedate,
			@RequestParam(value = "clsaccdate") String clsaccdate, @RequestParam(value = "ctlflag") String ctlflag,
			@RequestParam(value = "dcflag") String dcflag, @RequestParam(value = "dklx") String dklx,
			@RequestParam(value = "flag") String flag, @RequestParam(value = "housetype") String housetype,
			@RequestParam(value = "instcode") String instcode, @RequestParam(value = "jkrxm") String jkrxm,
			@RequestParam(value = "polenum") String polenum, @RequestParam(value = "ratetype") String ratetype,
			@RequestParam(value = "sjhm") String sjhm, @RequestParam(value = "speflag") String speflag,
			@RequestParam(value = "stepseqno") String stepseqno, @RequestParam(value = "transdate") String transdate,
			@RequestParam(value = "type") String type,@RequestParam(value = "xmmc") String xmmc,@RequestParam(value = "zjhm") String zjhm) {
		
		String params = ",agentbankcode,bankaddr,bankcode,closedate,clsaccdate,ctlflag,dcflag";
		params += ",dklx,flag,housetype,instcode,jkrxm,polenum,ratetype";
		params += ",sjhm,speflag,stepseqno,transdate,type,xmmc,zjhm";
		String paramValues = "&agentbankcode="+agentbankcode+"&bankaddr="+ bankaddr+"&bankcode="+ bankcode+"&closedate="+ closedate+"&clsaccdate="+ clsaccdate+"&ctlflag="+ ctlflag+"&dcflag="+ dcflag;
		paramValues += "&dklx="+dklx+"&flag="+ flag+"&housetype="+ housetype+"&instcode="+ instcode+"&jkrxm="+ jkrxm+"&polenum="+ polenum+"&ratetype="+ ratetype;
		paramValues += "&sjhm="+sjhm+"&speflag="+ speflag+"&stepseqno="+ stepseqno+"&transdate="+ transdate+"&type="+ type+"&xmmc="+ xmmc+"&zjhm="+ zjhm;
		String res = HttpUtil.send("appapi91326.json", "6549", params, paramValues);
		return res;
	}
	
	
	/* -------------------------- 以下为贷款申请接口          -------------------*/
	
	/**
	 * 贷款人信息返显
	 * @param flag
	 * @param zjhm
	 * @return
	 */
	@PostMapping("getDkrInfo")

	public String getDkrInfo(@RequestParam(value = "flag") String flag, @RequestParam(value = "zjhm") String zjhm) {
		
		String params = ",flag,zjhm";
		String paramValues = "&flag="+flag+"&zjhm="+zjhm;
		String res = HttpUtil.send("appapi91335.json", "6512", params, paramValues);
		return res;
	}
	
	/**
	 * 贷款额度试算
	 * @param amt1             amt1         : 借款人每月公积金缴存额
	 * @param amt2             amt2         : 配偶每月公积金缴存额,
	 * @param apploanterm      apploanterm  : 申请贷款期限,
	 * @param apploanamt       apploanamt   : 0.00,
	 * @param buyhousearea     buyhousearea : 房屋建筑面积,
	 * @param dwjcbl           dwjcbl       : 借款人单位缴存比例,
	 * @param dwzhye           dwzhye       : 配偶住房公积金账户余额：,
	 * @param finishyear       finishyear   : 房屋建成年份,
	 * @param flag             flag         : '0',
	 * @param fwzj             fwzj         : 房屋总价,
	 * @param grjcbl           grjcbl       : 借款人个人缴存比例,
	 * @param grzh             grzh         : 借款人个人账号,
	 * @param grzhye           grzhye       : 借款人每月公积金缴存额/(借款人个人缴存比例+借款人单位缴存比例),
	 * @param housetype        housetype    : 房屋类型,
	 * @param indiprop         indiprop     : 配偶个人缴存比例,
	 * @param jcsjxs           jcsjxs       : 借款人连续缴存时间,
	 * @param loanhousenum     loanhousenum : 本次为家庭第几套住房,
	 * @param peoplenum        peoplenum    : 人数,
	 * @param remainamt        remainamt    : '',
	 * @param repaymode        repaymode    : 还款方式,
	 * @param skipflag         skipflag     : '1',
	 * @param stepseqno        stepseqno  : '1',
	 * @param unitprop         unitprop     : 配偶单位缴存比例,
	 * @param useflag          useflag      : 是否使用配偶额度,
	 * @param xingbie          xingbie      : '',
	 * @param xingming         xingming     : 借款人姓名,
	 * @param ye               ye           : 借款人住房公积金账户余额,
	 * @param yue              yue          : 配偶住房公积金账户余额,
	 * @param zjhm             zjhm         : 借款人证件号码,
	 * @param zjlx             zjlx         : '',
	 * @param zzzdmj           zzzdmj       : 配偶缴存时间系数
	 * @return
	 */
	@PostMapping("dkss")

	public String dkss(@RequestParam(value = "amt1") String amt1,
			@RequestParam(value = "amt2") String amt2,
			@RequestParam(value = "apploanterm") String apploanterm,
			@RequestParam(value = "apploanamt") String apploanamt,
			@RequestParam(value = "buyhousearea") String buyhousearea,
			@RequestParam(value = "dwjcbl") String dwjcbl,
			@RequestParam(value = "dwzhye") String dwzhye,
			@RequestParam(value = "finishyear") String finishyear,
			@RequestParam(value = "flag") String flag,
			@RequestParam(value = "fwzj") String fwzj,
			@RequestParam(value = "grjcbl") String grjcbl,
			@RequestParam(value = "grzh") String grzh,
			@RequestParam(value = "grzhye") String grzhye,
			@RequestParam(value = "housetype") String housetype,
			@RequestParam(value = "indiprop") String indiprop,
			@RequestParam(value = "jcsjxs") String jcsjxs,
			@RequestParam(value = "loanhousenum") String loanhousenum,
			@RequestParam(value = "peoplenum") String peoplenum,
			@RequestParam(value = "remainamt") String remainamt,
			@RequestParam(value = "repaymode") String repaymode,
			@RequestParam(value = "skipflag") String skipflag,
			@RequestParam(value = "stepseqno") String stepseqno,
			@RequestParam(value = "unitprop") String unitprop,
			@RequestParam(value = "useflag") String useflag,
			@RequestParam(value = "xingbie") String xingbie,
			@RequestParam(value = "xingming") String xingming,
			@RequestParam(value = "ye") String ye,
			@RequestParam(value = "yue") String yue,
			@RequestParam(value = "zjhm") String zjhm,
			@RequestParam(value = "zjlx") String zjlx,
			@RequestParam(value = "zzzdmj") String zzzdmj) {
		
		String params = ",amt1,amt2,apploanterm,apploanamt,buyhousearea,dwjcbl,dwzhye,finishyear,flag,fwzj";
		params += ",grjcbl,grzh,grzhye,housetype,indiprop,jcsjxs,loanhousenum,peoplenum,remainamt,repaymode";
		params += ",skipflag,stepseqno,unitprop,useflag,xingbie,xingming,ye,yue,zjhm,zjlx,zzzdmj";
		String paramValues = "&amt1="+amt1+"&amt2="+amt2 + "&apploanterm="+apploanterm+"&apploanamt="+apploanamt+ "&buyhousearea="+buyhousearea+"&dwjcbl="+dwjcbl;
		paramValues += "&dwzhye="+dwzhye+"&finishyear="+finishyear + "&flag="+flag+"&fwzj="+fwzj+ "&grjcbl="+grjcbl+"&grzh="+grzh;
		paramValues += "&grzhye="+grzhye+"&housetype="+housetype + "&indiprop="+indiprop+"&jcsjxs="+jcsjxs+ "&loanhousenum="+loanhousenum+"&peoplenum="+peoplenum;
		paramValues += "&remainamt="+remainamt+"&repaymode="+repaymode+"&skipflag="+skipflag+"&stepseqno="+stepseqno+"&unitprop="+unitprop+"&useflag="+useflag;
		paramValues += "&xingbie="+xingbie+"&xingming="+xingming+"&ye="+ye+"&yue="+yue+"&zjhm="+zjhm+"&zjlx="+zjlx+"&zzzdmj="+zzzdmj;
		String res = HttpUtil.send("appapi00607.json", "5090", params, paramValues);
		return res;
	}
	
	/* ------------------ 以下为提前还款接口----------------------*/
	
	/**
	 * 网厅住房公积金委扣协议签订
	 * @param agentop
	 * @param pubaccnum
	 * @return
	 */
	@PostMapping("wkxyqd")

	public String wkxyqd(@RequestParam(value = "agentop") String agentop, @RequestParam(value = "pubaccnum") String pubaccnum) {
		pubaccnum= CryptoAes.encrypt(pubaccnum, encKey);
		String channelSeq = getSeq();
		String params = ",instcode,agentop,pubaccnum,channelSeq";
		String paramValues = "&instcode=00000010&agentop="+agentop+"&pubaccnum="+pubaccnum+"&channelSeq="+channelSeq;
		String res = HttpUtil.send("appapi00111.json", "5019", params, paramValues);
		if(res != null) {
			JSONObject json = (JSONObject) JSONObject.parse(res);
			if("000000".equals(json.getString("recode"))) {
				json.put("channelSeq", channelSeq);
				res = json.toJSONString();
			}
		}
		return res;
		//贷款类型：01公积金贷款，02组合贷款，03贴息贷款，99其他
		//{"recode":"000000","msg":"交易处理成功...","result":[{"title":"借款人姓名","name":"jkrxm","format":"1","info":"龚成成"},{"title":"借款合同编号","name":"jkhtbh","format":"1","info":"2180125020100007"},{"title":"贷款还款方式","name":"dkhkfs","format":"1","info":"等额本息"},{"title":"贷款金额","name":"dkje","format":"1","info":"222000.00"},{"title":"贷款类型","name":"dklx","format":"1","info":"公积金贷款"},{"title":"贷款期数","name":"dkqs","format":"1","info":"132"},{"title":"贷款余额","name":"dkye","format":"1","info":"219245.46"},{"title":"约定到期日期","name":"yddqrq","format":"1","info":"2029-01-24"},{"title":"约定放款日期","name":"ydfkrq","format":"1","info":"2018-01-25"},{"title":"还款日","name":"ydhkr","format":"1","info":"20"},{"title":"姓名","name":"xingming","format":"2","info":"龚成成"},{"title":"证件号码","name":"zjhm","format":"2","info":"420529198502101210"},{"title":"个人账号","name":"grzh","format":"2","info":"80430241"},{"title":"单位名称","name":"dwmc","format":"2","info":"五峰土家族自治县人民医院"},{"title":"个人缴存额","name":"gryjce","format":"2","info":"1160.00"},{"title":"个人缴存比例(%)","name":"grjcbl","format":"2","info":"12.00"},{"title":"个人缴存基数","name":"grjcjs","format":"2","info":"4833.00"},{"title":"个人账户余额","name":"grzhye","format":"2","info":"51905.41"},{"title":"委托代扣协议标志","name":"dedprotoflag","format":"2","info":"未签约"},{"title":"利率浮动比例","name":"llfdbl","format":"","info":"3.58"},{"title":"手机号码","name":"sjhm","format":"","info":"13886679925"},{"title":"贷款账号","name":"dkzh","format":"","info":"020102180000007"}]}
	}
	
	/**
	 * 公积金代扣协议维护
	 * @param agentop
	 * @param pubaccnum
	 * @return
	 */
	@PostMapping("gjjdkxywh")

	public String gjjdkxywh(@RequestParam(value = "agentop") String agentop, @RequestParam(value = "skipflag") String skipflag, @RequestParam(value = "channelSeq") String channelSeq) {
		String params = ",instcode,agentop,skipflag,channelSeq";
		String paramValues = "&instcode=00000010&agentop="+agentop+"&skipflag="+skipflag+"&channelSeq="+channelSeq;
		String res = HttpUtil.send("appapi91331.json", "5019", params, paramValues);
		return res;
	}
	
	/**
	 * 提前还款(隐式提交)
	 * @param flag //1公积金 ，3银行卡，5公积金+银行卡
	 * @param loancontrcode
	 * @return
	 */
	@PostMapping("tqhk_sub")
   
	public String tqhk_sub(@RequestParam(value = "flag") String flag, @RequestParam(value = "loancontrcode") String loancontrcode) {
		String channelSeq = getSeq();
		String params = ",currflag,flag,loancontrcode,channelSeq";
		String paramValues = "&currflag=1&flag="+flag+"&loancontrcode="+loancontrcode+"&channelSeq="+channelSeq;
		String res = HttpUtil.send("appapi91318.json", "6545", params, paramValues);
		if(res != null) {
			JSONObject json = (JSONObject) JSONObject.parse(res);
			if("000000".equals(json.getString("recode"))) {
				json.put("channelSeq", channelSeq);
				res = json.toJSONString();
			}
		}
		return res;
	}
	
	
	/**
	 * 提前还款
	 * @param agentop
	 * @param ahdrepayamt
	 * @param cashsum
	 * @param dedbankaccnum
	 * @param dedbankcode
	 * @param filename
	 * @param flag
	 * @param instcode
	 * @param loancontrcode
	 * @param newloanterm
	 * @param newrepaymode
	 * @param repayorder
	 * @param repaytolamt
	 * @param repaytype//还款类型 ：2提前全部还款，3提前部分还款
	 * @param settlemode//结算方式：01等额本息，02等额本金，03一次性还本付息
	 * @param shttermflag
	 * @param stepseqno
	 * @param transfsum
	 * @param famt1
	 * @param famt2
	 * @param famt3
	 * @param famt4
	 * @param famt5
	 * @param famt6
	 * @param grzh
	 * @param accnum
	 * @param adjiaccnum
	 * @param adjoaccnum
	 * @param mergiaccnum
	 * @param mergoaccnum
	 * @return
	 */
	@PostMapping("tqhk")

	public String tqhk(@RequestParam(value = "agentop") String agentop, //8888
			@RequestParam(value = "ahdrepayamt") String ahdrepayamt, //提前还本金
			@RequestParam(value = "cashsum") String cashsum, //现金总额
			@RequestParam(value = "dedbankaccnum") String dedbankaccnum, //银行扣款账号
			@RequestParam(value = "dedbankcode") String dedbankcode,//代扣银行 
			@RequestParam(value = "filename") String filename, //文件名称
			@RequestParam(value = "flag") String flag, //1
			@RequestParam(value = "instcode") String instcode, //机构代码
			@RequestParam(value = "loancontrcode") String loancontrcode, //合同代码
			@RequestParam(value = "newloanterm") String newloanterm, //新贷款期限
			@RequestParam(value = "newrepaymode") String newrepaymode,//新还款方式 
			@RequestParam(value = "repayorder") String repayorder, //还款顺序
			@RequestParam(value = "repaytolamt") String repaytolamt, //还款总金额
			@RequestParam(value = "repaytype") String repaytype, //还款类型
			@RequestParam(value = "settlemode") String settlemode, //结算方式
			@RequestParam(value = "shttermflag") String shttermflag, //贷款缩期标志
			@RequestParam(value = "stepseqno") String stepseqno, //1
			@RequestParam(value = "transfsum") String transfsum, //转账总额
			@RequestParam(value = "famt1") String famt1, //
			@RequestParam(value = "famt2") String famt2, 
			@RequestParam(value = "famt3") String famt3, 
			@RequestParam(value = "famt4") String famt4, 
			@RequestParam(value = "famt5") String famt5, 
			@RequestParam(value = "famt6") String famt6, 
			@RequestParam(value = "grzh") String grzh, 
			@RequestParam(value = "accnum") String accnum, 
			@RequestParam(value = "adjiaccnum") String adjiaccnum, 
			@RequestParam(value = "adjoaccnum") String adjoaccnum, 
			@RequestParam(value = "mergiaccnum") String mergiaccnum, 
			@RequestParam(value = "mergoaccnum") String mergoaccnum,
			@RequestParam(value = "channelSeq") String channelSeq
			) {
		
		String params = ",agentop,ahdrepayamt,cashsum,dedbankaccnum,dedbankcode,filename";
		params += ",flag,instcode,loancontrcode,newloanterm,newrepaymode,repayorder";
		params += ",repaytolamt,repaytype,settlemode,shttermflag,stepseqno,transfsum";
		params += ",famt1,famt2,famt3,famt4,famt5,famt6";
		params += ",grzh,accnum,adjiaccnum,adjoaccnum,mergiaccnum,mergoaccnum,channelSeq";
		String paramValues = "&agentop="+agentop+"&ahdrepayamt="+ahdrepayamt+"&cashsum="+cashsum;
		paramValues += "&dedbankaccnum="+dedbankaccnum+"&dedbankcode="+dedbankcode+"&filename="+filename;
		paramValues += "&flag="+flag+"&instcode="+instcode+"&loancontrcode="+loancontrcode;
		paramValues += "&newloanterm="+newloanterm+"&newrepaymode="+newrepaymode+"&repayorder="+repayorder;
		paramValues += "&repaytolamt="+repaytolamt+"&repaytype="+repaytype+"&settlemode="+settlemode;
		paramValues += "&shttermflag="+shttermflag+"&stepseqno="+stepseqno+"&transfsum="+transfsum;
		paramValues += "&famt1="+famt1+"&famt2="+famt2+"&famt3="+famt3+"&famt4="+famt4+"&famt5="+famt5+"&famt6="+famt6;
		paramValues += "&grzh="+grzh+"&accnum="+accnum+"&adjiaccnum="+adjiaccnum+"&adjoaccnum="+adjoaccnum+"&mergiaccnum="+mergiaccnum+"&mergoaccnum="+mergoaccnum+"&channelSeq="+channelSeq;
		String res = HttpUtil.send("appapi91317.json", "6545", params, paramValues);
		return res;
	}
	
	/**
	 * 提前还款入账
	 * @param apprnum
	 * @param repaytolamt
	 * @param stepseqno 入账4，撤销6
	 * @return
	 */
	@PostMapping("tqhkrz")

	public String tqhkrz(@RequestParam(value = "apprnum") String apprnum, @RequestParam(value = "repaytolamt") String repaytolamt, @RequestParam(value = "stepseqno") String stepseqno, @RequestParam(value = "channelSeq") String channelSeq) {
		String money = CryptoAes.encrypt(repaytolamt, encKey);
		String params = ",apprnum,repaytolamt,stepseqno,money,channelSeq";
		String paramValues = "&apprnum="+apprnum+"&repaytolamt="+repaytolamt+"&stepseqno="+stepseqno+"&money="+money+"&channelSeq="+channelSeq;
		String res = HttpUtil.send("appapi03603.json", "5826", params, paramValues);
		return res;
	}
}
