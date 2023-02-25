package semi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.FqboardDao;
import semi.dao.NoticesDao;
import semi.dao.ReviewDao;
import semi.vo.FqboardVo;
import semi.vo.NoticesVo;
import semi.vo.ReviewVo;

@WebServlet("/start.do")
public class StartController extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");
        if(cmd.equals("start")) {
            start(req,resp); 
        }
    }
    public void start(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<NoticesVo>noticesList = NoticesDao.getInstance().noticesStart();
        ArrayList<ReviewVo> reviewMain = ReviewDao.getInstance().listmain();
        req.setAttribute("noticesList", noticesList);
        req.setAttribute("reviewMain", reviewMain);
        ArrayList<FqboardVo> fqmain = FqboardDao.getInstance().fqmain();
		req.setAttribute("fqmain", fqmain);
        RequestDispatcher rd = req.getRequestDispatcher("main.jsp");
        rd.forward(req, resp);
    }
}
