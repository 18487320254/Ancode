package api.test.test.com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import base.test.test.com.Base;
import base.test.test.com.User;
import database.test.test.com.D_Dao;

@WebServlet("/SetData")
public class SetData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SetData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Base.Encoding(request, response);
		HttpSession session=request.getSession();
		String type=Base.get(request, "type");
		String sql="";
		String par="";
		String json=null;
		User user=(User) session.getAttribute("User_Now");
		if (Base.isLogin(request, "User_Now")) {
			if (type.equals("add_order")) {//新增账单
				String nowtype=Base.get(request, "nowtype");
				String remark=Base.get(request, "remarks");
				Double total=Base.getDouble(request, "total");
				String time=Base.Time();//2020-02-12 12:12:12
				String year=time.split("-")[0];
				String month=time.split("-")[1];
				sql="insert into order(uid,type,remarks,total,time,year,month) values(?,?,?,?,?,?,?)";
				par=user.getId()+","+nowtype+","+remark+","+total+","+time+","+year+","+month;
				if (D_Dao.Up(sql, par)) {
					Base.put("yes", response);
				} else {
					Base.put("n_do", response);
				}
			} else if(type.equals("get_list_order_total")){//

			}
		} else {
			Base.put("n_login", response);
		}
	}

}
