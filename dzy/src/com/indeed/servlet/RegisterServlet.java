package com.indeed.servlet;
import com.indeed.dao.UserDaoImpl;
import com.indeed.model.User;

import sun.font.Script;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HTML框架
        String html_head = "<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <link rel=\"stylesheet\" href=\"layui/css/layui.css\">\n" +
                "    <script type=\"javascript\" src=\"layui/layui.js\" ></script>\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n";
        String html_tail = "</body>\n" +
                "</html>";

        //用户注册信息
        String username = request.getParameter("username").trim();
        String password = request.getParameter("pass1").trim();
        String platformName = request.getParameter("platform_name").trim();
        String platformNo = request.getParameter("platform_number").trim();
        String approvalDate = request.getParameter("approve_date").trim();
        String approvalNum = request.getParameter("approve_number").trim();
        String field = request.getParameter("technosphere").trim();
        //检验用户名是否已存在
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> users = userDao.loadUser(username);
        try {
            if (users.size() != 0){
                request.setAttribute("err_msg","用户名已存在！<a href='javascript:history.go(-1);'>返回</a>");
                request.getRequestDispatcher("error.jsp").forward(request,response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(users.size() == 0) {
            //添加用户
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(approvalDate);
                Date date2=new Date(date.getTime());
                User user = new User(username, password, platformName, platformNo, date2, approvalNum, field);
                userDao.add(user);
                PrintWriter pWriter=response.getWriter();
                //响应用户
                pWriter.write("<script language='javaScript'>"+"alert('Successful');"+"document.location.href='login.jsp';"+"</script>");
                pWriter.close();
                //request.getRequestDispatcher("msg.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("exception", e);
                request.setAttribute("err_msg", "出错！请联系管理员（15613155379）错误信息为：");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
