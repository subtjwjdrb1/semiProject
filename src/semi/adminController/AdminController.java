package semi.adminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.MemberDao;
import semi.dao.SellDao;
import semi.vo.MemberVo;
import semi.vo.SellVo;
@WebServlet("/admin.do")
public class AdminController extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String cmd = req.getParameter("cmd");
        System.out.println(cmd);
        if(cmd.equals("board")) {
            board(req,resp);
        }else if(cmd.equals("report")) {
            report(req,resp);
        }else if(cmd.equals("mypage")) {
            mypage(req,resp);
        }
    }
    public void mypage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    public void board(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String text = req.getParameter("text");
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
        ArrayList<SellVo>list = SellDao.getInstance().list(startRow, endRow);
        if(text==null) {
            getMax = SellDao.getInstance().getCount();
            list = SellDao.getInstance().list(startRow, endRow);
        }else {
            System.out.println("select:"+req.getParameter("select"));
            String select = req.getParameter("select");
            getMax=SellDao.getInstance().getCount(select,text);
            list = SellDao.getInstance().searchlist(select,text,startRow,endRow);
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
        req.setAttribute("pageNum", pageNum);*/
        req.setAttribute("page", "/admin/board.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void report(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("page", "/admin/report.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
}
