package base.test.test.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Base {
	//获取当前系统时间
	public static String Time(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(System.currentTimeMillis());
		return time;
	}
	//生成6位验证码
	public static String Code(){
		int i=0;
		Random random=new Random();
		i=random.nextInt(899999)+100000;
		String s=Integer.toString(i);
		return s;
	}
	//servlet编码设置为UTF-8
	public static void Encoding(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	}
	//servlet获得string
	public static String get(HttpServletRequest request,String name) {
		return request.getParameter(name).trim().replace(",", "，").replace("#equal#", "=").replace("#apostrophe#", "'").replace("#percent#", "%");
	}
	
	//servlet获得int
	public static int getInt(HttpServletRequest request,String name) {
		return Integer.parseInt(request.getParameter(name).trim());
	}
	
	//servlet获得double
	public static double getDouble(HttpServletRequest request,String name) {
		return Double.valueOf(request.getParameter(name).trim());
	}
	
	//servlet向前端输出字符串
	public static void put(String text, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
		    out.print(text);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//servlet判断用户登录状态
	public static boolean isLogin(HttpServletRequest request,String AttributeName) {
		boolean b=false;
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute(AttributeName);
		if (user!=null) {
			b=true;
		}
		return b;
	}
}
