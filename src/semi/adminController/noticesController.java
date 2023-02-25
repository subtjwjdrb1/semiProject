package semi.adminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.adminController.BoardController;
import semi.dao.NoticesDao;
import semi.vo.NoticesVo;
@WebServlet("/notices.do")
public class noticesController extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String cmd=req.getParameter("cmd");
        if(cmd.equals("notices")) {
            notices(req,resp);
        }else if(cmd.equals("detail")) {
            detail(req,resp);
        }else if(cmd.equals("noticesdelete")) {
            noticesdelete(req,resp);
        }else if(cmd.equals("update")) {
            update(req,resp);
        }
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num=Integer.parseInt(req.getParameter("num"));
        String title = req.getParameter("title");
        String content = req.getParameter("scontent");
        NoticesVo vo = new NoticesVo(num,title,content,0,null);
        int n = NoticesDao.getInstance().update(vo);
        req.setAttribute("vo", vo);
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "noticesOk");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void noticesdelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql=req.getParameter("sql");
        System.out.println(sql);
        int n = NoticesDao.getInstance().delete(sql);
        System.out.println("n:"+n);
        BoardController bc = new BoardController();
        bc.notices(req,resp);
    }
    public void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num=Integer.parseInt(req.getParameter("num"));
        String admin = req.getParameter("admin");
        System.out.println("admin:"+admin);
        NoticesVo vo=NoticesDao.getInstance().detail(num);
        NoticesDao.getInstance().updateHit(vo);
        if(admin==null) {
            req.setAttribute("vo", vo);
            req.setAttribute("page", "/notices/noticesOk.jsp");
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("vo", vo);
            req.setAttribute("page", "/admin/board.jsp");
            req.setAttribute("page1", "noticesOk");
            RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
            rd.forward(req, resp);
        }
        
    }
    public void notices(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ArrayList<NoticesVo> list = null;
        if(text==null) {
            getMax = NoticesDao.getInstance().getMax();
            list = NoticesDao.getInstance().noticesList(null, startRow, endRow);
        }else {
            list = NoticesDao.getInstance().noticesList(text,startRow,endRow);
            getMax=NoticesDao.getInstance().getMax(text);
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
        req.setAttribute("page", "/notices/notices.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("main.jsp");
        rd.forward(req, resp);
    }
}
