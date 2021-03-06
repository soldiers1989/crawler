package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Test {
	public static void main(String[] args) throws Exception {
		// token----24.600a7da9301566e837a3a6a3b1c7f545.2592000.1518851491.282335-10711509
		String token = getAuth();
		System.out.println(token);
		// 通用识别urlconnection.setRequestProperty("templateSign", "ea3ae8a5c4145067eb987eb669c336b2");
//		String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate";
		String otherHost = "https://aip.baidubce.com/rest/2.0/solution/v1/iocr/recognise";
		// 本地图片路径
		String filePath = "E:\\Codeimg\\zhuhaiPayInfo.png";
		try {
			byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);
			String param = "image="+URLEncoder.encode(imgStr,"UTF-8")+"&templateSign=88718ba27c9519469d798b5f32221f7f";
			/**
			 * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取,30天
			 */
			String result = HttpUtil.post(otherHost, token, param);
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取权限token
	 */
	public static String getAuth() {
		// API Key
		String clientId = "85Yh1jbkPVjTVAa0SWcvGqlC";
		// Secret Key
		String clientSecret = "N7s4LxZiVWug6bt5NrMa6eNqOad54F3v";
		return getAuth(clientId, clientSecret);
	}

	public static String getAuth(String ak, String sk) {
		// 获取token地址
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		String getAccessTokenUrl = authHost
				// grant_type为固定参数
				+ "grant_type=client_credentials"
				// API Key
				+ "&client_id=" + ak
				// Secret Key
				+ "&client_secret=" + sk;
		try {
			URL realUrl = new URL(getAccessTokenUrl);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.err.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String result = "";
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			/**
			 * 返回结果示例
			 */
			System.err.println("result:" + result);
			
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse(result);
			String access_token = jsonObject.get("access_token").getAsString();
			return access_token;
		} catch (Exception e) {
			System.err.printf("获取token失败！");
			e.printStackTrace(System.err);
		}
		return null;
	}
}