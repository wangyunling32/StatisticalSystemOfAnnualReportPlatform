package com.indeed.model;

//平台填报信息
public class Platform {

    public static int NATIONAL = 1;             //国家级
    public static int PROVINCIAL = 2;           //省级
    public static int RELATIVE_INDEPENDENT = 1;     //内设机构相对独立
    public static int ENTERPRISE = 2;           //独立企业法人
    public static int COUSE = 3;                //独立事业法人
    public static int ASSOCIATION = 4;          //独立社团法人
    private int id;

    private User user;                  //填报用户

    private Organization organization;  //依托单位

    private Dean dean;                  //平台主任(院长)

    private int grade;                  //平台级别

    private String district;            //所在市县

    private int form;                   //平台组织形态

    private boolean unite;              //联合/独自

    private boolean jjjgj;              //是否京津冀共建

    private String industry;            //主要国民经济行业

    private String subject;             //所属主要学科

    private String[] gjdw;              //共建单位

    private String sitename;            //平台网站名称

    private String website;             //平台网站地址

    private String addr;                //平台通讯地址

    private String postcode;            //邮编
    
   private int status;                   //审核状态

    public Platform() {
    }

    public Platform(User user, Organization organization, Dean dean, int grade, String district, int form, boolean unite, boolean jjjgj, String industry, String subject, String[] gjdw, String sitename, String website, String addr, String postcode,int status) {
        this.user = user;
        this.organization = organization;
        this.dean = dean;
        this.grade = grade;
        this.district = district;
        this.form = form;
        this.unite = unite;
        this.jjjgj = jjjgj;
        this.industry = industry;
        this.subject = subject;
        this.gjdw = gjdw;
        this.sitename = sitename;
        this.website = website;
        this.addr = addr;
        this.postcode = postcode;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Dean getDean() {
        return dean;
    }

    public void setDean(Dean dean) {
        this.dean = dean;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public boolean isUnite() {
        return unite;
    }

    public void setUnite(boolean unite) {
        this.unite = unite;
    }

    public boolean isJjjgj() {
        return jjjgj;
    }

    public void setJjjgj(boolean jjjgj) {
        this.jjjgj = jjjgj;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getGjdw() {
        return gjdw;
    }

    public void setGjdw(String[] gjdw) {
        this.gjdw = gjdw;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    /*
     * 
     * status表示审核状态
     * status=0表示未审核
     * status=1表示审核成功
     * status=2表示为未通过审核
     * status默认是0
     */
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
    
}
