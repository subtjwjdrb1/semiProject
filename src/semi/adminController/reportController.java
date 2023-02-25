package semi.adminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.ReportDao;
import semi.vo.BuyVo;
import semi.vo.FqboardVo;
import semi.vo.ReviewVo;
import semi.vo.SellVo;
@WebServlet("/report.do")
public class reportController extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");
        if(cmd.equals("fqboardReport")) {
            fqboardReport(req,resp);
        }else if(cmd.equals("fqboardReportDetail")) {
            fqboardReportDetail(req,resp);
        }else if(cmd.equals("fqboardReportDel")) {
            fqboardReportDel(req,resp);
        }else if(cmd.equals("buyReport")) {
            buyReport(req,resp);
        }else if(cmd.equals("buyReportDel")) {
            buyReportDel(req,resp);
        }else if(cmd.equals("buyReportDetail")) {
            buyReportDetail(req,resp);
        }else if(cmd.equals("sellReport")) {
            sellReport(req,resp);
        }else if(cmd.equals("sellReportDel")) {
            sellReportDel(req,resp);
        }else if(cmd.equals("sellReportDetail")) {
            sellReportDetail(req,resp);
        }else if(cmd.equals("reviewReport")) {
            reviewReport(req,resp);
        }else if(cmd.equals("reviewReportDel")) {
            reviewReportDel(req,resp);
        }else if(cmd.equals("reviewReportDetail")) {
            reviewReportDetail(req,resp);
        }
    }
    public void fqboardReportDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fqno=Integer.parseInt(req.getParameter("fqno"));
        FqboardVo vo=ReportDao.getInstance().fqboardDetail(fqno);
            req.setAttribute("vo", vo);
            req.setAttribute("page", "/admin/fqboardReportDetail.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
            rd.forward(req, resp);
    }
    public void fqboardReportDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql=req.getParameter("sql");
        System.out.println(sql);
        int n = ReportDao.getInstance().buyDelete(sql);
        System.out.println("n:"+n);
       fqboardReport(req, resp);
    }
    public void fqboardReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ArrayList<FqboardVo>list = ReportDao.getInstance().fqboardReport(startRow, endRow);
            getMax=ReportDao.getInstance().fqboardGetMax();
            list = ReportDao.getInstance().fqboardReport(startRow, endRow);
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
        req.setAttribute("page", "/admin/fqboardReport.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void sellReportDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sno=Integer.parseInt(req.getParameter("sno"));
        SellVo vo=ReportDao.getInstance().sellDetail(sno);
            req.setAttribute("vo", vo);
            req.setAttribute("page", "/admin/sellReportDetail.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
            rd.forward(req, resp);
    }
    public void sellReportDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql=req.getParameter("sql");
        System.out.println(sql);
        int n = ReportDao.getInstance().sellDelete(sql);
        System.out.println("n:"+n);
        sellReport(req, resp);
    }
    public void sellReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ArrayList<SellVo>list = ReportDao.getInstance().SellReport(startRow, endRow);
            getMax=ReportDao.getInstance().sellGetMax();
            list = ReportDao.getInstance().sellReport(startRow, endRow);
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
        req.setAttribute("page", "/admin/sellReport.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void reviewReportDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rno=Integer.parseInt(req.getParameter("rno"));
        ReviewVo vo=ReportDao.getInstance().reviewDetail(rno);
            req.setAttribute("vo", vo);
            req.setAttribute("page", "/admin/reviewReportDetail.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
            rd.forward(req, resp);
    }
    public void reviewReportDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql=req.getParameter("sql");
        System.out.println(sql);
        int n = ReportDao.getInstance().reportDelete(sql);
        System.out.println("n:"+n);
        reviewReport(req, resp);
    }
    public void reviewReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ArrayList<ReviewVo>list = ReportDao.getInstance().ReviewReport(startRow, endRow);
        getMax=ReportDao.getInstance().reviewGetMax();
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
        req.setAttribute("page", "/admin/reviewReport.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void buyReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ArrayList<BuyVo>list = ReportDao.getInstance().buyReport(startRow, endRow);
        getMax=ReportDao.getInstance().buyGetMax();
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
        req.setAttribute("page", "/admin/buyReport.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
        rd.forward(req, resp);
    }
    public void buyReportDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bno=Integer.parseInt(req.getParameter("bno"));
        BuyVo vo=ReportDao.getInstance().buyDetail(bno);
            req.setAttribute("vo", vo);
            req.setAttribute("page", "/admin/buyReportDetail.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
            rd.forward(req, resp);
    }
    public void buyReportDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql=req.getParameter("sql");
        System.out.println(sql);
        int n = ReportDao.getInstance().buyDelete(sql);
        System.out.println("n:"+n);
        buyReport(req, resp);
    }
}

