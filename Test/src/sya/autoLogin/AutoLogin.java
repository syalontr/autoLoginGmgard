package sya.autoLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * @author majie
 * @version 1.0.0
 * @Description
 * @date 2017年7月10日 下午6:20:25
 * @Copyright 中国电信集团系统集成有限责任公司
 */
public class AutoLogin {

	private String loginURL = "http://gmgard.com/";
	private String PunchInURL="http://gmgard.com/Home/PunchIn";
	private String token=null;
	private String cookie="__cfduid=dd804f0f56f54765d028b70a1e827e4ec1499678315; .AspNetCore.Antiforgery.v2CUd5wyqKo=CfDJ8BF8V8mqOGlAr4Bd8nUmq7xOS2VNIqfk4BylVtmUbphHstWyhR_jmD08SZ5QNKhNnvwwugfXppEEv7luoqW-ODQlDqyv-VpD43SBX2b6yoCqL2mdY160Ia8_itbjtZM_X6fut_DKT3RKfvQjjLmSAtA; .AspNetCore.Session=CfDJ8BF8V8mqOGlAr4Bd8nUmq7zDLFzFS2O4f%2By5Zdlx1s3%2FGozlRQIFYcaYMcoGD8K4vF0fSwqfqIHlgzRI9MLAHrM%2BiwGbL0VgsfeCe0DDFjkaYd%2FmZ9GB8JUG8gL5sSb%2B7LXkWEKdm8%2BrC0fjBJN198KOTC14CbW2MjUR5laV9mW1; .AspNetCore.Identity.Application=CfDJ8BF8V8mqOGlAr4Bd8nUmq7zemp494p9uqqIhv7LU58djEcKqAGCKatkmCP06FeAIHESDoepOjlst7VsQOJmndEUB87F3M-u9gEn6WCD63MfS_orcuRGQ49uhpQCN-33owjje_Is93lyXprNFk3Bmo8qv2bxGxztXY0tDf5tMNNpoztGjEJ3lPAKn4sl92Km_umq1WuZSK1PdriCNeKCXZ7H-U6Etwu-n8WuvWNLXDtvrq1f_6h3wX7R91YXnWgZeFnw3HO-cF3aw4Te9gbIuF7ambxzHzF_5wCvYu2vmGhSg8jC0I2C2zGhE7u2YcUAVQlzCNEXMWj42__L9q9BdhiHMory1Rg5hADiyox4mfQj9L6Kmuub4WeXEx05ZrbM3OxaWvAZ59452XrD-O8cO5LVkeJN_UEuHRahwTdzQfZdQrdwvUMh27O-j2EFFH7UJjgXzXT5Ns7LV3JEtxbC2L2nKA9AcVP_fgFe2NBsK77zsAt46BEI1Xs934DaDzzKM2bWzY0cMTizJFT_055GKs5uRLQT8mFSVg5JTJ8ma7m7n7J7ELTjZao26Rcl1tRGj4w; _ga=GA1.2.2098638690.1499678317; _gid=GA1.2.1277629138.1499678317";
	
	/**
	 * 获取必要的登陆参数信息
	 * @throws ClientProtocolException 
	 * 
	 * @throws IOException
	 */
	private void fetchNecessaryParam() throws ClientProtocolException, IOException {

		System.out.println("获取登录表单参数。。。。。");
		Map<String,String> headers=new HashMap();
		headers.put("Cookie",cookie);
		String ret=HttpUtils.sendGet(loginURL, headers);

		Document doc = Jsoup.parse(ret);
		Element contentForm = doc.getElementById("punch-form"); 
		
		token = doc.select("input[name=__RequestVerificationToken]").get(0).val();

		System.out.println("获取成功。。。。。");
	}

	private void login() throws ClientProtocolException, IOException {
			fetchNecessaryParam();
		
		System.out.println("开始签到。。。。。");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("__RequestVerificationToken", token));
		nvps.add(new BasicNameValuePair("ismakeup", "false"));
		
		Map<String,String> headers=new HashMap();
		headers.put("Cookie", cookie);
		headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
		headers.put("X-Requested-With","XMLHttpRequest");
		headers.put("Origin","http://gmgard.com");
		
		String ret=HttpUtils.sendPost(PunchInURL, nvps, headers);
		System.out.println(ret);
		
	}

	public static void main(String[] args) {
		AutoLogin lession = new AutoLogin();
		try {
			lession.login();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}