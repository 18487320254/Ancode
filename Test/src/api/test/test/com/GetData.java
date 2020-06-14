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

@WebServlet("/GetData")
public class GetData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetData() {
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
			if (type.equals("get_list_order")) {//获取账单列表
				sql="select * from order where uid=? order by id desc";
				json=D_Dao.SelecttoJson(sql, user.getId()+"");
				Base.put(json, response);
			} else if(type.equals("get_list_order_total")){//

			} else if(type.equals("get_list_order_total")){//

			} else if(type.equals("get_list_order_total")){//

			}
		} else {
			Base.put("n_login", response);
		}
	}

}
