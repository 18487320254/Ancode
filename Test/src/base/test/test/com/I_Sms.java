package base.test.test.com;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSONObject;

//短信接口
public class I_Sms {
	//极速数据短信接口    
    //极速数据短信接口
    public static boolean sendms_jisu(String phone,String content) throws Exception {
    	boolean b=false;
        String APPKEY = "668ad0eacba00e6d";// 你的appkey
        String URL = "https://api.jisuapi.com/sms/send";
        //String mobile = phone;// 手机号
        //String content = "用户您好。【极速数据】";// utf-8
     
        String result = null;
        String url = URL + "?mobile=" + phone + "&content=" + URLEncoder.encode(content, "utf-8") + "&appkey="
                + APPKEY;
        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = JSONObject.parseObject(result);
            if (json.getIntValue("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
            	b=true;
                JSONObject resultarr = json.getJSONObject("result");
                String count = resultarr.getString("count");
                String accountid = resultarr.getString("accountid");
                System.out.println(count + " " + accountid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
