package semi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.BuyDao;
import semi.dao.FqboardDao;
import semi.dao.MemberDao;
import semi.dao.ReviewDao;
import semi.dao.SellDao;
import semi.vo.BuyVo;
import semi.vo.FqboardVo;
import semi.vo.MemberVo;
import semi.vo.SellVo;

@WebServlet("/buy.do")
public class BuyController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		//System.out.println("buycmd:"+cmd);
		//String context=req.getContextPath();
		String id=(String)req.getSession().getAttribute("id");
		if(cmd.equals("buyList")) {
			list(req,resp);
		}else if(cmd.equals("insert") && id!=null) {
			req.setAttribute("page", "/buy/insert.jsp");
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		}else if(cmd.equals("insertOk")) {
			insertOk(req,resp);
		}else if(cmd.equals("bdetail")) {
			detail(req,resp);
		}else if(cmd.equals("delete")) {
			delete(req,resp);
		}else if(cmd.equals("search")) {
			search(req,resp);
		}else if(cmd.equals("update")) {
			update(req,resp);
		}else if(cmd.equals("police")) {
			police(req,resp);
		}else if(cmd.equals("updateOk")) {
			updateOk(req,resp);
		}
	}
	
		private void updateOk(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException{
			int bno=Integer.parseInt(req.getParameter("bno"));
			String btitle=req.getParameter("btitle");
			String bcontent=req.getParameter("scontent");
			int success=Integer.parseInt(req.getParameter("bsuccess"));
			
			BuyDao dao=BuyDao.getInstance();
			BuyVo vo=new BuyVo(bno, btitle, bcontent, null, 0, 0, success, 0, null);
			int n=dao.updateOk(vo);
			
			if(n>0) {
				resp.sendRedirect(req.getContextPath()+"/buy.do?cmd=buyList");
			}else {
				req.setAttribute("result", "fail");
				resp.sendRedirect(req.getContextPath()+"/buy.do?cmd=buyList");
			}
			
		}
		
	private void police(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int bno = Integer.parseInt(req.getParameter("bno"));
		String id = req.getParameter("id");
		//System.out.println("sno:"+sno+"id:"+id);
		BuyDao dao=BuyDao.getInstance();
		int n=dao.oxpolice(bno, id);
		if(n>0) {
			req.setAttribute("result", "�̹� �Ű��� �Խù��Դϴ�");
			detail(req, resp);
		}else {
			int police = dao.police(bno, id);
			if(police>0) {
				req.setAttribute("result", "�Ű��Ͽ����ϴ�.");
				dao.updateReport(bno);
				detail(req, resp);
			}else {
				detail(req, resp);
			}
		}	
		
	}
	
	
	private void update(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int bno = Integer.parseInt(req.getParameter("bno"));
		BuyDao dao=BuyDao.getInstance();
		BuyVo vo=dao.update(bno);
		req.setAttribute("vo", vo);
		req.setAttribute("page", "/buy/update.jsp");
		req.getRequestDispatcher("main.jsp").forward(req, resp);
		
	}
	
	private void search(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		String text = req.getParameter("text");
        String spageNum = req.getParameter("pageNum");
        //System.out.println("spageNum:"+spageNum);
        int pageNum=1;
        if(spageNum!=null) {
            if(Integer.parseInt(spageNum)<0) {
                spageNum="1";
            }
            pageNum=Integer.parseInt(spageNum);
        }
        //System.out.println("pageNum:"+pageNum);
        int startRow = (pageNum-1)*10+1;
        //System.out.println("startRow:"+startRow);
        int endRow = startRow+9;
        //System.out.println("endRow:"+endRow);
        int getCount=0;
        //System.out.println("text:"+text);
        ArrayList<BuyVo> list = null;
        if(text==null) {
            getCount = BuyDao.getInstance().getCount();
            list = BuyDao.getInstance().list(startRow,endRow);
        }else {
            //System.out.println("select:"+req.getParameter("select"));
            String select = req.getParameter("select");
            list = BuyDao.getInstance().search(select,text,startRow,endRow);
            getCount=BuyDao.getInstance().getCount(select,text);
            req.setAttribute("select", select);
            req.setAttribute("text", text);
        }
        //System.out.println("getMax:"+getCount);
        int pageCount = (int)Math.ceil(getCount/10.0);
        //System.out.println("pageCount:"+pageCount);
        int startPage = ((pageNum-1)/5*5)+1;
        //System.out.println("startPage:"+startPage);
        int endPage = startPage+4;
        if(pageCount<endPage) {
            endPage=pageCount;
        }
        //System.out.println("endPage:"+endPage);
        req.setAttribute("list", list);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("page", "/buy/buylist.jsp");
		req.getRequestDispatcher("/main.jsp").forward(req, resp);
	}
		

	
	private void delete(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int bno = Integer.parseInt(req.getParameter("bno"));
		String id= req.getParameter("id");
		BuyDao dao = BuyDao.getInstance();
		int n = dao.delete(bno, id);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/buy.do?cmd=buyList");
		}else {
			req.setAttribute("result", "cancel");
		}
	}
	
	
	private void list(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		
		BuyDao dao=BuyDao.getInstance();
	
			String spageNum=req.getParameter("pageNum");
			int pageNum=1;
			if(spageNum!=null) {
				pageNum=Integer.parseInt(spageNum);
			}
			int startRow=(pageNum-1)*10+1;
			int endRow=startRow+9;
			

			ArrayList<BuyVo> list=dao.list(startRow, endRow);
			int pageCount=(int)Math.ceil(dao.getCount()/10.0);
			
			int startPage = ((pageNum-1)/5)*5 + 1 ; 
			int endPage = startPage + 4 ; 
			if(pageCount<endPage) {
				endPage=pageCount;
			}
				
			req.setAttribute("list", list);
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("page", "/buy/buylist.jsp");
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		
	}
	private void insertOk(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int success=Integer.parseInt(req.getParameter("success"));
		String btitle=req.getParameter("btitle");
		String bcontent= req.getParameter("scontent");
		String id=(String)req.getSession().getAttribute("id");
		
		BuyVo vo=new BuyVo(0, btitle, bcontent, null, 0, 0, success, 0, id);
		BuyDao dao=BuyDao.getInstance();
		int n=dao.insert(vo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/buy.do?cmd=buyList");
		}else {
			
		}
	}
	private void detail(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int bno=Integer.parseInt(req.getParameter("bno"));
		
		BuyDao dao=BuyDao.getInstance();
		BuyVo vo=dao.detail(bno);
		int police = dao.getPolice(bno);
		dao.updateHit(vo);
		req.setAttribute("vo", vo);
		req.setAttribute("police", police);
		req.setAttribute("page", "buy/bdetail.jsp");
		req.getRequestDispatcher("/main.jsp").forward(req, resp);
		
		
	}
}
