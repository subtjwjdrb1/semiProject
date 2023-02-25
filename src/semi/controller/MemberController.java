package semi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.MemberDao;
import semi.vo.MemberVo;

@WebServlet("/member.do")
public class MemberController  extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String cmd = req.getParameter("cmd");
       // System.out.println(cmd);
        if(cmd.equals("join")) {
            req.setAttribute("page", "/member/join.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("main.jsp");
            rd.forward(req, resp);
        }else if(cmd.equals("login")) {
            req.setAttribute("page", "/member/login.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("main.jsp");
            rd.forward(req, resp);
        }else if(cmd.equals("memberInsert")) {
            memberInsert(req,resp);
        }else if(cmd.equals("checkId")) {
            checkId(req,resp);
        }else if(cmd.equals("loginOk")) {
            loginOk(req,resp);
        }else if(cmd.equals("update")) {
        	update(req,resp);
        }else if(cmd.equals("mypage")) {
        	mypage(req,resp);
        }
    }
    public void mypage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String id =(String)req.getSession().getAttribute("id");
    	System.out.println("id:?"+id);
    	ArrayList<MemberVo> list = null;
    	list = MemberDao.getInstance().updateMember(id);
    	req.setAttribute("list", list);
     	req.setAttribute("page", "/mypage/myPageUpdate.jsp");
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
    
    
    
    
    public void loginOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String pwd=req.getParameter("pwd");
        int n = MemberDao.getInstance().loginOk(id, pwd);
        if(n>0) {
            req.getSession().setAttribute("id",id);
            String addr = req.getParameter("addr");
            System.out.println("id:"+id);
            if(id.equals("admin")) {
                resp.sendRedirect(req.getContextPath()+"/admin.jsp");
            }else if(addr.equals("")) {
                resp.sendRedirect(req.getContextPath()+"/start.jsp");
            }else {
                resp.sendRedirect(addr);
            }
        }else {
            req.setAttribute("page", "/member/login.jsp");

            req.setAttribute("result", "fail");
            RequestDispatcher rd = req.getRequestDispatcher("main.jsp");

            rd.forward(req, resp);
        }
    }
    public void checkId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/xml;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        int n = MemberDao.getInstance().checkId(id);
        pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        if(n>0) {
            pw.println("<result>success</result>");
        }else {
            pw.println("<result>fail</result>");
        }
        pw.close();
    }
    public void memberInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id"); 
        String pwd = req.getParameter("pwd"); 
        String nickname = req.getParameter("nickname"); 
        String name = req.getParameter("name"); 
        String phone = req.getParameter("phone"); 
        String email = req.getParameter("email"); 
        MemberVo vo = new MemberVo(id, pwd, nickname, name, phone, email, null);
        int n = MemberDao.getInstance().insert(vo);
        if(n>0) {
            req.setAttribute("page", "/member/login.jsp");
            req.setAttribute("msg", "success");
            RequestDispatcher rd = req.getRequestDispatcher("main.jsp");
            rd.forward(req, resp);
        }else {
            req.setAttribute("page", "/member/join.jsp");
            req.setAttribute("msg", "fail");
            RequestDispatcher rd = req.getRequestDispatcher("main.jsp");
            rd.forward(req, resp);
        }
    }
    public void join(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
    }
    
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
    	String pwd=req.getParameter("pwd");
        String nickname = req.getParameter("nickname"); 
        String name = req.getParameter("name"); 
        String phone = req.getParameter("phone"); 
        String email = req.getParameter("email"); 
        MemberVo vo = new MemberVo(id, pwd, nickname, name, phone, email, null);
        int n = MemberDao.getInstance().update(vo);
        if(n>0) {
            req.setAttribute("result", "success");
            RequestDispatcher rd = req.getRequestDispatcher("/mypage/myPageUpdate.jsp");
            rd.forward(req, resp);
        }else {
            req.setAttribute("result", "fail");
            RequestDispatcher rd = req.getRequestDispatcher("/mypage/myPageUpdate.jsp");
            rd.forward(req, resp);
        }
    }
    
    
    
}
