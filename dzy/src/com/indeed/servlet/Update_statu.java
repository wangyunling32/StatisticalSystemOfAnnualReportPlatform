package com.indeed.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.indeed.dao.PlatformDao;
import com.indeed.dao.UserDaoImpl;
import com.indeed.model.Platform;
import com.indeed.model.User;

/**
 * Servlet implementation class Update_statu
 */
@WebServlet("/Update_statu")
public class Update_statu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_statu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
        PrintWriter pWriter=response.getWriter();
        //响应用户
        pWriter.write("<script language='javaScript'>"+"alert('Update Status Successful');"+"document.location.href='admin.jsp';"+"</script>");
        
        String status = request.getParameter("status");
        PrintWriter pw = response.getWriter();
        pw.write("<script language='javascript'>alert('hehe')");
        System.out.println(status);
        String pid=request.getParameter("pid");
        int id=Integer.valueOf(pid);
        int x;
        if(status.equals("通过"))
        {
        	x=1;
        }
        else {
			x=2;
		}
        PlatformDao platformDao=new PlatformDao();
         platformDao.update_status(id,x);
        try {
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        request.setAttribute("msg","{title:'提示信息',content:'审核成功！',btnAlign:'c'}");
        request.setAttribute("err_msg","审核成功！点击<a href='admin.jsp'>返回</a>审核页");
	}

}
