package semi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.AdminDao;
import semi.dao.MyPageDao;
import semi.vo.MyPageVo;

@WebServlet("/mypage.do")
public class MyPageController extends HttpServlet{
@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	resp.setContentType("text/html; charset=utf-8");
	String cmd= req.getParameter("cmd");
	System.out.println("cmd:"+cmd);
	if(cmd.equals("scrap")) {
		scrap(req,resp);
		}else if(cmd.equals("list")){
			list(req,resp);
		}else if(cmd.equals("delete")) {
			delete(req,resp);
		}
	}
public void scrap(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String url=(String)req.getSession().getAttribute("url");
	System.out.println(url);
	req.getSession().removeAttribute("url");
	int rno=Integer.parseInt(req.getParameter("rno"));
	String id=req.getParameter("id");
	String title=req.getParameter("rtitle");
	
	MyPageVo vo = new MyPageVo(0, title, id, url, null,rno);
	int n=MyPageDao.getInstance().scrapAdd(vo);
	if(n>0) {
		resp.sendRedirect(url);
	}
	
	
	}
public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String id=(String)req.getSession().getAttribute("id");
	System.out.println(id);
	String text = req.getParameter("text");
    String spageNum = req.getParameter("pageNum");
    System.out.println("spageNum:"+spageNum);
    int pageNum=1;
    if(spageNum!=null) {
        if(Integer.parseInt(spageNum)<0) {
            spageNum="1";
        }
        pageNum=Integer.parseInt(spageNum);
    }
    System.out.println("pageNum:"+pageNum);
    int startRow = (pageNum-1)*10+1;
    System.out.println("startRow:"+startRow);
    int endRow = startRow+9;
    System.out.println("endRow:"+endRow);
    int getMax=0;
    System.out.println("text:"+text);
    ArrayList<MyPageVo>list = null;
    if(text==null) {
        getMax = MyPageDao.getInstance().getCount();
        list = MyPageDao.getInstance().reviewList(id,null,null,startRow, endRow);
    }else {
        System.out.println("select:"+req.getParameter("select"));
        String select = req.getParameter("select");
        getMax=MyPageDao.getInstance().getCount(select,text);
        list = MyPageDao.getInstance().reviewList(id,select, text, startRow, endRow);
        
        req.setAttribute("select", select);
        req.setAttribute("text", text);
    }
    System.out.println("getMax:"+getMax);
    int pageCount = (int)Math.ceil(getMax/10.0);
    System.out.println("pageCount:"+pageCount);
    int startPage = ((pageNum-1)/5*5)+1;
    System.out.println("startPage:"+startPage);
    int endPage = startPage+4;
    if(pageCount<endPage) {
        endPage=pageCount;
    }
    System.out.println("endPage:"+endPage);
    req.setAttribute("list", list);
    req.setAttribute("pageCount", pageCount);
    req.setAttribute("startPage", startPage);
    req.setAttribute("endPage", endPage);
    req.setAttribute("pageNum", pageNum);
   /* req.setAttribute("page", "/mypage/MyPageScrap.jsp");*/
    RequestDispatcher rd = req.getRequestDispatcher("/mypage/myPageScrap.jsp");
    rd.forward(req, resp);
	}

public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String sql=req.getParameter("sql");
    System.out.println(sql);
    int n = MyPageDao.getInstance().delete(sql);
    System.out.println("n:"+n);
    if(n>0) {
        req.setAttribute("del", "삭제되었습니다.");
    }else {
        req.setAttribute("page", "/mypage/myPageScrap.jsp");
        req.setAttribute("page1", "/review");
    }
    list(req,resp);
}
}