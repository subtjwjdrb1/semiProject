package semi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.FqboardDao;
import semi.dao.SellDao;
import semi.vo.FqboardVo;
import semi.vo.SellVo;

@WebServlet("/fq.do")
public class FqController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		System.out.println("fqcmd:"+cmd);
		//String context=req.getContextPath();
		String id=(String)req.getSession().getAttribute("id");
		if(cmd.equals("fqList")) {
			list(req,resp);
		}else if(cmd.equals("insert") && id!=null) {
			req.setAttribute("page", "/fq/insert.jsp");
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		}else if(cmd.equals("insertOk")) {
			insertOk(req,resp);
		}else if(cmd.equals("fqdetail")) {
			detail(req,resp);
		}else if(cmd.equals("search")) {
			search(req,resp);
		}else if(cmd.equals("update")) {
			update(req,resp);
		}else if(cmd.equals("police")) {
			police(req,resp);
		}else if(cmd.equals("recommend")) {
			recommend(req,resp);
		}else if(cmd.equals("delete")) {
			delete(req,resp);
		}else if(cmd.equals("updateOk")) {
			updateOk(req,resp);
		}
	}
	
	private void updateOk(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int fqno=Integer.parseInt(req.getParameter("fqno"));
		int fqtype=Integer.parseInt(req.getParameter("fqtype"));
		String fqtitle=req.getParameter("fqtitle");
		String fqcontent=req.getParameter("fqcontent");
		
		FqboardDao dao=FqboardDao.getInstance();
		FqboardVo vo=new FqboardVo(fqno, fqtype, fqtitle, fqcontent, null, 0, 0, 0, null, 0);
		int n=dao.updateOk(vo);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/fq.do?cmd=fqList");
		}else {
			req.setAttribute("result", "fail");
			resp.sendRedirect(req.getContextPath()+"/fq.do?cmd=fqList");
		}
		
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int fqno = Integer.parseInt(req.getParameter("fqno"));
		String id= req.getParameter("id");
		FqboardDao dao = FqboardDao.getInstance();
		int n = dao.delete(fqno, id);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/fq.do?cmd=fqList");
		}else {
			req.setAttribute("result", "cancel");
		}
	}
	
	public void recommend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int fqno = Integer.parseInt(req.getParameter("fqno"));
		String id = req.getParameter("id");
		FqboardDao dao=FqboardDao.getInstance();
		int n=dao.oxrecommend(fqno, id);
		
		if(n>0) {
			req.setAttribute("result", "동일 게시물에는 추천할 수 없습니다.");
			detail(req, resp);
		}else {
		int recommend = dao.recommend(fqno, id);
			if(recommend>0) {
				req.setAttribute("result", "추천하였습니다.");
				detail(req, resp);
			}else {
				detail(req, resp);
			}
		}
		
	}
	
	private void police(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int fqno = Integer.parseInt(req.getParameter("fqno"));
		String id = req.getParameter("id");
		//System.out.println("sno:"+sno+"id:"+id);
		FqboardDao dao=FqboardDao.getInstance();
		int n=dao.oxpolice(fqno, id);
		if(n>0) {
			req.setAttribute("result", "�̹� �Ű��� �Խù��Դϴ�");
			detail(req, resp);
		}else {
			int police = dao.police(fqno, id);
			if(police>0) {
				req.setAttribute("result", "�Ű��Ͽ����ϴ�.");
				dao.updateReport(fqno);
				detail(req, resp);
			}else {
				detail(req, resp);
			}
		}	
		
	}
	
	private void update(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int fqno = Integer.parseInt(req.getParameter("fqno"));
		System.out.println("fqno:"+fqno);
		FqboardDao dao=FqboardDao.getInstance();
		FqboardVo vo=dao.update(fqno);
		req.setAttribute("vo", vo);
		req.setAttribute("page", "/fq/update.jsp");
		req.getRequestDispatcher("main.jsp").forward(req, resp);
		
	}
	
	private void search(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		String text = req.getParameter("text");
        String spageNum = req.getParameter("pageNum");
        int pageNum=1;
        if(spageNum!=null) {
            if(Integer.parseInt(spageNum)<0) {
                spageNum="1";
            }
            pageNum=Integer.parseInt(spageNum);
        }
        int startRow = (pageNum-1)*10+1;
        int endRow = startRow+9;
        int getCount=0;
        ArrayList<FqboardVo> list = null;
        if(text==null) {
            getCount = FqboardDao.getInstance().getCount();
            list = FqboardDao.getInstance().list(startRow,endRow);
        }else {
            String select = req.getParameter("select");
            list = FqboardDao.getInstance().search(select,text,startRow,endRow);
            getCount=FqboardDao.getInstance().getCount(select,text);
            req.setAttribute("select", select);
            req.setAttribute("text", text);
        }
        int pageCount = (int)Math.ceil(getCount/10.0);
        int startPage = ((pageNum-1)/5*5)+1;
        int endPage = startPage+4;
        if(pageCount<endPage) {
            endPage=pageCount;
        }
        req.setAttribute("list", list);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("pageNum", pageNum);
        req.setAttribute("page", "/fq/fqList.jsp");
		req.getRequestDispatcher("/main.jsp").forward(req, resp);
	}
	
	private void detail(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int fqno=Integer.parseInt(req.getParameter("fqno"));
		FqboardDao dao=FqboardDao.getInstance();
		FqboardVo vo=dao.detail(fqno);
		int police = dao.getPolice(fqno);
		int recommend = dao.getRecommend(fqno);
		
		dao.updateHit(vo);
		req.setAttribute("vo", vo);
		req.setAttribute("police", police);
		req.setAttribute("recommend", recommend);
		req.setAttribute("page", "fq/fqdetail.jsp");
		req.getRequestDispatcher("/main.jsp").forward(req, resp);
		
		
	}
	
	private void insertOk(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int fqtype=Integer.parseInt(req.getParameter("fqtype"));
		String fqtitle=req.getParameter("fqtitle");
		String fqcontent= req.getParameter("scontent");
		String id=(String)req.getSession().getAttribute("id");
		FqboardVo vo=new FqboardVo(0, fqtype, fqtitle, fqcontent, null, 0, 0, 0, id, 0);
		FqboardDao dao=FqboardDao.getInstance();
		int n=dao.insert(vo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/fq.do?cmd=fqList");
		}else {
			
		}
	}
	
	private void list(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		
		FqboardDao dao=FqboardDao.getInstance();
	
			String spageNum=req.getParameter("pageNum");
			int pageNum=1;
			if(spageNum!=null) {
				pageNum=Integer.parseInt(spageNum);
			}
			int startRow=(pageNum-1)*10+1;
			int endRow=startRow+9;
			

			ArrayList<FqboardVo> list=dao.list(startRow, endRow);
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
			req.setAttribute("page", "/fq/fqList.jsp");
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		
	}
}
