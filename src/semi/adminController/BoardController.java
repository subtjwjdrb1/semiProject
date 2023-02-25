package semi.adminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.AdminDao;
import semi.dao.BuyDao;
import semi.dao.FqboardDao;
import semi.dao.NoticesDao;
import semi.dao.ReportDao;
import semi.dao.ReviewDao;
import semi.dao.SellDao;
import semi.vo.BuyVo;
import semi.vo.FqboardVo;
import semi.vo.NoticesVo;
import semi.vo.ReviewVo;
import semi.vo.SellVo;
@WebServlet("/boardlist.do")
public class BoardController extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String cmd= req.getParameter("cmd");
        System.out.println("cmd:"+cmd);
        if(cmd.equals("sell")) {
            sell(req,resp);
        }else if(cmd.equals("selldelete")) {
            selldelete(req,resp);
        }else if(cmd.equals("sellDetail")) {
            sellDetail(req,resp);
        }else if(cmd.equals("review")) {
            review(req,resp);
        }else if(cmd.equals("reviewDetail")){
            reviewDetail(req,resp);
        }else if(cmd.equals("reviewdelete")){
            reviewdelete(req,resp);
        }else if(cmd.equals("buy")) {
            buy(req,resp);
        }else if(cmd.equals("buyDetail")) {
            buyDetail(req,resp);
        }else if(cmd.equals("buydelete")) {
            buydelete(req,resp);
        }else if(cmd.equals("notices")) {
            notices(req,resp);
        }else if(cmd.equals("noticesInsert")) {
            noticesInsert(req,resp);
        }else if(cmd.equals("noticesOk")) {
            noticesOk(req,resp);
        }else if(cmd.equals("noticesUpdate")) {
            noticesUpdate(req,resp);
        }else if(cmd.equals("selldelete")) {
            selldelete(req,resp);
        }else if(cmd.equals("fqboard")) {
            fqboard(req,resp);
        }else if(cmd.equals("fqboardDetail")) {
            fqboardDetail(req,resp);
        }else if(cmd.equals("fqboarddelete")) {
            fqboarddelete(req,resp);
        }
    }
    public void fqboarddelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sql=req.getParameter("sql");
        System.out.println(sql);
        int n = AdminDao.getInstance().adminDelete(sql);
        System.out.println("n:"+n);
        if(n>0) {
            req.setAttribute("del", "명령성공");
        }else {
            req.setAttribute("page", "/admin/board.jsp");
            req.setAttribute("page1", "/fqboard");
        }
        fqboard(req,resp);
    }
    public void fqboardDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fqno=Integer.parseInt(req.getParameter("fqno"));
        FqboardVo vo=ReportDao.getInstance().fqboardDetail(fqno);
        req.setAttribute("vo", vo);
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "fqboard");
        req.setAttribute("page2", "detail");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void fqboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ArrayList<FqboardVo>list = null;
        if(text==null) {
            getMax = FqboardDao.getInstance().getCount();
            list = FqboardDao.getInstance().list(startRow, endRow);
        }else {
            System.out.println("select:"+req.getParameter("select"));
            String select = req.getParameter("select");
            getMax=FqboardDao.getInstance().getCount(select,text);
            list = FqboardDao.getInstance().search(select, text, startRow, endRow);
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
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "fqboard");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void reviewdelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sql=req.getParameter("sql");
        System.out.println(sql);
        int n = AdminDao.getInstance().adminDelete(sql);
        System.out.println("n:"+n);
        if(n>0) {
            req.setAttribute("del", "명령성공");
        }else {
            req.setAttribute("page", "/admin/board.jsp");
            req.setAttribute("page1", "/review");
        }
        review(req,resp);
    }
    public void reviewDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rno=Integer.parseInt(req.getParameter("rno"));
        ReviewVo vo=ReportDao.getInstance().reviewDetail(rno);
        req.setAttribute("vo", vo);
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "review");
        req.setAttribute("page2", "detail");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void review(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ArrayList<ReviewVo>list = null;
        if(text==null) {
            getMax = ReviewDao.getInstance().getCount();
            list = ReviewDao.getInstance().reviewList(null,null,startRow, endRow);
        }else {
            System.out.println("select:"+req.getParameter("select"));
            String select = req.getParameter("select");
            getMax=ReviewDao.getInstance().getCount(select,text);
            list = ReviewDao.getInstance().reviewList(select, text, startRow, endRow);
            
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
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "review");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void buydelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql=req.getParameter("sql");
        System.out.println(sql);
        int n = AdminDao.getInstance().adminDelete(sql);
        System.out.println("n:"+n);
        if(n>0) {
            req.setAttribute("del", "명령성공");
        }else {
            req.setAttribute("page", "/admin/board.jsp");
            req.setAttribute("page1", "/buy");
        }
        buy(req,resp);
    }
    public void buyDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bno=Integer.parseInt(req.getParameter("bno"));
        BuyVo vo=ReportDao.getInstance().buyDetail(bno);
        req.setAttribute("vo", vo);
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "buy");
        req.setAttribute("page2", "detail");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void sellDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sno=Integer.parseInt(req.getParameter("sno"));
        SellVo vo=ReportDao.getInstance().sellDetail(sno);
        req.setAttribute("vo", vo);
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "sell");
        req.setAttribute("page2", "detail");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void selldelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql=req.getParameter("sql");
        System.out.println(sql);
        int n = AdminDao.getInstance().adminDelete(sql);
        System.out.println("n:"+n);
        if(n>0) {
            req.setAttribute("del", "명령성공");
        }else {
            req.setAttribute("page", "/admin/board.jsp");
            req.setAttribute("page1", "sell");
        }
        sell(req,resp);
    }
    public void noticesUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num=Integer.parseInt(req.getParameter("num"));
        NoticesVo vo=NoticesDao.getInstance().detail(num);
        req.setAttribute("vo", vo);
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "noticesUpdate");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void noticesInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "noticesInsert");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
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
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "notices");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void noticesOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String title = req.getParameter("title");
       String content = req.getParameter("scontent");
       String chk[] = req.getParameterValues("chk");
       System.out.println("title:"+title);
       System.out.println("content:"+content);
       int n=0;
       for(int i=0; i<chk.length;i++) {
           n+=AdminDao.getInstance().notices(chk[i], title, content);
           System.out.println("n:"+n);
       }
        
        notices(req,resp);
    }
    public void buy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ArrayList<BuyVo>list = null;
        if(text==null) {
            getMax = BuyDao.getInstance().getCount();
            list = BuyDao.getInstance().list(startRow, endRow);
        }else {
            System.out.println("select:"+req.getParameter("select"));
            String select = req.getParameter("select");
            getMax=BuyDao.getInstance().getCount(select,text);
            list = BuyDao.getInstance().search(select, text, startRow, endRow);
            
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
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "buy");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void sell(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ArrayList<SellVo>list = null;
        if(text==null) {
            getMax = SellDao.getInstance().getCount();
            list = SellDao.getInstance().list(startRow, endRow);
        }else {
            System.out.println("select:"+req.getParameter("select"));
            String select = req.getParameter("select");
            getMax=SellDao.getInstance().getCount(select,text);
            list = SellDao.getInstance().searchlist(select,text,startRow,endRow,null);
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
        req.setAttribute("page", "/admin/board.jsp");
        req.setAttribute("page1", "sell");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    
}
