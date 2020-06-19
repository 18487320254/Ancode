package api.test.test.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import base.test.test.com.Base;
import base.test.test.com.I_Sms;
import base.test.test.com.User;
import database.test.test.com.D_Base;
import database.test.test.com.D_Dao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Base.Encoding(request, response);
		HttpSession session=request.getSession();
		String type=Base.get(request, "type");
		if (type.equals("getcode")) {//获取验证码
			String phone=Base.get(request, "phone");
			String code=Base.Code();
			String cent="亲爱的朋友您好，您本次手机验证码为"+code+"，桉光科技祝您生活愉快！【桉光科技】";
			System.out.println(cent);
			try {
				if (I_Sms.sendms_jisu(phone,cent)) {
					session.setAttribute("User_phone", phone);
					session.setAttribute("User_code", code);
					Base.put("yes", response);
				} else {
					Base.put("n_do", response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Base.put("n_do", response);
			}
		} else if(type.equals("login")){//登录
			String code=Base.get(request, "code");
			String code1=(String) session.getAttribute("User_code");
			String phone=(String) session.getAttribute("User_phone");
			session.removeAttribute("User_code");
			session.removeAttribute("User_phone");
			String sql="";
			String par="";
			if (code.equals(code1)) {
				sql="select * from user where phone=?";
				if (D_Dao.NoExist(sql, phone)) {//新用户
					sql="insert into user(phone,time) values(?,?)";
					par=phone+","+Base.Time();
					if (D_Dao.Up(sql, par)) {
						Connection conn=D_Base.Opendb();
						sql="select * from user where phone=?";
						ResultSet rs=D_Dao.Select(conn, sql, phone);
						User user=D_Dao.getBean_User(rs);
						if (user!=null) {
							session.setAttribute("User_Now", user);
							Base.put("yes", response);
						} else {
							Base.put("n_do", response);
						}
					} else {
						Base.put("n_do", response);
					}
				} else {//老用户 
					Connection conn=D_Base.Opendb();
					ResultSet rs=D_Dao.Select(conn, sql, phone);
					User user=D_Dao.getBean_User(rs);
					if (user!=null) {
						session.setAttribute("User_Now", user);
						Base.put("yes", response);
					} else {
						Base.put("n_do", response);
					}
				}
			} else {
				Base.put("n_code", response);
			}
		} else if(type.equals("logout")){//注销
			session.invalidate();
			Base.put("yes", response);
		}
	}

}
