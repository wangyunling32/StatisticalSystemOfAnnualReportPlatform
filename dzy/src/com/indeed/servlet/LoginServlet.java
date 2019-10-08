package com.indeed.servlet;

import com.indeed.dao.UserDaoImpl;
import com.indeed.model.User;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends javax.servlet.http.HttpServlet {

    //HTML框架
    private String html_head = "<html>\n" +
            "<head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "    <link rel=\"stylesheet\" href=\"layui/css/layui.css\">\n" +
            "    <script type=\"javascript\" src=\"layui/layui.js\" ></script>\n" +
            "    <title>Title</title>\n" +
            "</head>\n" +
            "<body>\n";
    private String html_tail = "</body>\n" +
            "</html>";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("wyl")&&password.equals("123456"))
        {
        	response.sendRedirect("temp.jsp");
        }
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.load(username,password);
        if (user == null)
        {
        	/***user为null，输出错误信息 ***/
			response.setContentType("text/html");
			String docType =
				      "<!doctype html public \"-//w3c//dtd html 4.0 " +
				      "transitional//en\">\n";
			PrintWriter writer = response.getWriter();
			writer.println(docType);
			writer.println("<script>alert('用户不存在或密码错误!')</script>");
			HttpSession session = request.getSession();
			session.setAttribute("number", username);
			writer.println("<script>window.location.href='login.jsp'</script>");
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            request.getRequestDispatcher("fill_in.jsp").forward(request,response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String absolute = getServletConfig().getServletContext().getRealPath("/");
        File china_json = new File(absolute + "js\\china.js");

        request.setAttribute("err_msg","非法请求，请以正规方式登陆系统");
        request.getRequestDispatcher("error.jsp").forward(request,response);
    }
}
