package com.indeed.servlet;

import com.indeed.dao.DeanDao;
import com.indeed.dao.OrganizationDao;
import com.indeed.dao.PlatformDao;
import com.indeed.model.Dean;
import com.indeed.model.Organization;
import com.indeed.model.Platform;
import com.indeed.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "PlatformSubmitServlet")
public class PlatformSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从Session中获取填报用户
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        /***平台基本信息***/
        String[] platform_grade = request.getParameterValues("platform_grade"); //平台级别
        String platform_province = request.getParameter("province");            //所在省
        String platform_city = request.getParameter("city");                    //所在市
        String platform_district = request.getParameter("district");            //所在区
        String platform_form = request.getParameter("platform_form");           //平台组织形态
        String platform_subject = request.getParameter("subject3");             //所属主要学科
        String platform_faren_type = request.getParameter("faren_type");        //法人类型
        String platform_unite = request.getParameter("unite");                  //联合共建/独自建设
        String platform_jjjgj = request.getParameter("jjjgj");                  //京津冀共建
        String[] platform_gjdw = request.getParameterValues("gjdw");            //共建单位
        String  platform_industry=request.getParameter("section");
        
        
        int gradeValue = 0;
        System.out.println("平台基本信息：");
        for (String grade:platform_grade) {
            gradeValue = gradeValue|Integer.valueOf(grade);
            System.out.println(grade);
        }

        int formValue = 0;
        try {
            formValue += Integer.valueOf(platform_form);
            formValue += Integer.valueOf(platform_faren_type);
        }catch (NumberFormatException e){
            System.out.println("数值解析错误");
        }

        System.out.println(platform_province);
        System.out.println(platform_city);
        System.out.println(platform_district);
        System.out.println(platform_form);
        System.out.println(platform_faren_type);
        System.out.println(platform_unite);
        System.out.println(platform_jjjgj);
        for (String g:platform_gjdw){
            System.out.println(g);
        }
        System.out.println("");



        /**********************************/
        /**暂缺国民经济行业**/
        /**********************************/

        /***依托单位信息**/
        String dep_organization_name = request.getParameter("dep_organization_name");       //依托单位名称
        String dep_organization_number = request.getParameter("dep_organization_number");   //社会信用代码
        String dep_organization_faren = request.getParameter("dep_organization_faren");     //法人代表姓名
        String dep_organization_phone = request.getParameter("dep_organization_phone");     //办公电话
        String dep_organization_type = request.getParameter("dep_organization_type");       //依托单位类型

        System.out.println("依托单位信息：");
        System.out.println(dep_organization_name);
        System.out.println(dep_organization_number);
        System.out.println(dep_organization_faren);
        System.out.println(dep_organization_phone);
        System.out.println(dep_organization_type);
        System.out.println("");



        /***平台主任***/
        String dean_name = request.getParameter("dean_name");                               //平台主任姓名
        String dean_sex = request.getParameter("sex");                                      //性别
        String dean_birthday = request.getParameter("birthday");                            //出生年月
        String dean_job_title = request.getParameter("job_title");                          //职称
        String dean_major = request.getParameter("major");                                  //所学专业
        String dean_education = request.getParameter("education");                          //学历
        String dean_degree = request.getParameter("degree");                                //学位
        String dean_telephone = request.getParameter("dean_telephone");                     //办公电话
        String dean_mobilephone = request.getParameter("dean_mobilephone");                 //手机
        String dean_email = request.getParameter("dean_email");                             //Email

        System.out.println("平台主任信息：");
        System.out.println(dean_name);
        System.out.println(dean_sex);
        System.out.println(dean_birthday);
        System.out.println(dean_job_title);
        System.out.println(dean_major);
        System.out.println(dean_education);
        System.out.println(dean_degree);
        System.out.println(dean_telephone);
        System.out.println(dean_mobilephone);
        System.out.println(dean_email);
        System.out.println("");


        /***平台联系信息***/
        String platform_site_name = request.getParameter("platform_site_name");             //平台网站名称
        String platform_website = request.getParameter("platform_website");                 //平台网站地址
        String platform_addr = request.getParameter("platform_addr");                       //平台通讯地址
        String platform_postcode = request.getParameter("platform_postcode");               //邮编

        System.out.println("平台联系信息");
        System.out.println(platform_site_name);
        System.out.println(platform_website);
        System.out.println(platform_addr);
        System.out.println(platform_postcode);



        /***存入数据库***/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");          //日期格式解析
        //依托单位
        OrganizationDao organizationDao = new OrganizationDao();
        Organization organization = new Organization(dep_organization_name,dep_organization_number,dep_organization_faren,dep_organization_phone,dep_organization_type,user.getId());
        organization.setId(organizationDao.addOrganization(organization));
        //平台院长
        DeanDao deanDao = new DeanDao();
        Dean dean = null;
        try {
            Timestamp birthday = new Timestamp(sdf.parse(dean_birthday).getTime());
            dean = new Dean(dean_name,dean_sex,birthday,dean_job_title,dean_major,dean_education,dean_degree,dean_telephone,dean_mobilephone,dean_email,user.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dean.setId(deanDao.addDean(dean));          //设置id
        //平台信息
        PlatformDao platformDao = new PlatformDao();
        Platform platform = new Platform(user,organization,dean,gradeValue,platform_district,formValue,"united".equals(platform_unite),"yes".equals(platform_jjjgj),platform_industry,platform_subject,platform_gjdw,platform_site_name,platform_website,platform_addr,platform_postcode,0);
        platformDao.addPlatform(platform);
        PrintWriter pWriter=response.getWriter();
        //响应用户
        pWriter.write("<script language='javaScript'>"+"alert('Successful');"+"document.location.href='fill_in.jsp';"+"</script>");
        pWriter.close();
        //request.setAttribute("msg","{title:'提示信息',content:'提交成功',btnAlign:'c'}");
        //request.getRequestDispatcher("fill_in.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("err_msg","非法请求，请以正规方式提交表单");
        request.getRequestDispatcher("error.jsp").forward(request,response);
    }
}
