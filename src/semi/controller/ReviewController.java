package semi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.dao.ReviewDao;
import semi.vo.RcommentVo;
import semi.vo.ReviewVo;

@WebServlet("/review.do")
public class ReviewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		System.out.println(cmd);
		if (cmd.equals("list")) {
			list(req, resp);
		} else if (cmd.equals("write")) {
			req.setAttribute("page", "/review/jReviewWrite.jsp");
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		} else if (cmd.equals("writeOk")) {
			writeOk(req, resp);
		} else if (cmd.equals("list")) {
			list(req, resp);
		} else if (cmd.equals("content")) {
			content(req, resp);
		}else if (cmd.equals("delete")) {
			delete(req,resp);
		}else if (cmd.equals("update")) {
			update(req,resp);
		}else if(cmd.equals("recommend")) {
			recommend(req,resp);
		}else if(cmd.equals("police")) {
			police(req,resp);
		}else if(cmd.equals("updateOk")) {
			updateOk(req,resp);
		}else if(cmd.equals("rcomment")) {
		    rcomment(req,resp);
		}
		
	}

	public void rcomment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String rcomment = req.getParameter("rcomment");
	    String id = req.getParameter("id");
	    int rno = Integer.parseInt(req.getParameter("rno"));
	    int rcno = Integer.parseInt(req.getParameter("rcno"));
	    int rcref = Integer.parseInt(req.getParameter("rcref"));
	    int rclev = Integer.parseInt(req.getParameter("rclev"));
	    int rcstep = Integer.parseInt(req.getParameter("rcstep"));
	    int rcreport = Integer.parseInt(req.getParameter("rcreport"));
	    System.out.println("rcomment : "+rcomment);
	    System.out.println("rcno : "+rcno);
	    System.out.println("rcref : "+rcref);
	    System.out.println("rclev : "+rclev);
	    System.out.println("rcstep : "+rcstep);
	    System.out.println("rcreport : "+rcreport);
	    int n = ReviewDao.getInstance().rcommentInsert(rcomment, id, rno, rcno, rcref, rclev, rcstep,rcreport);
	    content(req, resp);
	}
	public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String text = req.getParameter("text");
		String spageNum = req.getParameter("pageNum");
		System.out.println("spageNum:"+spageNum);
		int pageNum = 1;
		if(spageNum!=null) {
			if(Integer.parseInt(spageNum)<0) {
				spageNum="1";
			}
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow = (pageNum-1)*10+1;
		System.out.println("startRow:"+startRow);
		int endRow = startRow+9;
		System.out.println("endRow:"+ endRow);
		int getMax=0;
		System.out.println("text:"+text);
		ArrayList<ReviewVo> rlist = null;
		if(text==null) {
			getMax = ReviewDao.getInstance().getCount();
			rlist= ReviewDao.getInstance().reviewList2(null, null, startRow, endRow);
		}else {
			System.out.println("select:"+req.getParameter("select"));
			String select = req.getParameter("select");
			getMax=ReviewDao.getInstance().getCount(select,text);
			System.out.println("getMax:"+getMax);
			rlist = ReviewDao.getInstance().reviewList2(select, text, startRow, endRow);

			req.setAttribute("select",select);
			req.setAttribute("text", text);
		}
		int pageCount = (int)Math.ceil(getMax/10.0);
		int startPage = ((pageNum-1)/5*5)+1;
		int endPage = startPage + 4;
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		
		req.setAttribute("rlist", rlist);
		req.setAttribute("pageCount",pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("page", "/review/jReviewList.jsp");
		req.getRequestDispatcher("main.jsp").forward(req, resp);
		
		
		
	
	}

	public void writeOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		String uploadPath = "C:\\Users\\JHTA\\git\\semiproject\\semiProject\\WebContent\\image";

		


		

		MultipartRequest mr = new MultipartRequest(req, uploadPath, 1024 * 1024 * 5, "utf-8",
				new DefaultFileRenamePolicy());

		String rtitle = mr.getParameter("title");
		String rcontent = mr.getParameter("scontent");
		
		String orgfilename = mr.getOriginalFileName("file");
		String savefilename = mr.getFilesystemName("file");
		String id = (String) req.getSession().getAttribute("id");
		int company = Integer.parseInt(mr.getParameter("company"));
		ReviewVo vo = new ReviewVo(0, rtitle, rcontent, null, 0, 0, orgfilename, savefilename, id, company,0,0);
		ReviewDao dao = ReviewDao.getInstance();
		int n = dao.write(vo);
		if (n > 0) {
			resp.sendRedirect("/semiProject/review.do?cmd=list");
		} else {
			System.out.println("fail");
		}
	}

	public void content(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rno = Integer.parseInt(req.getParameter("rno"));
		ReviewDao dao = ReviewDao.getInstance();
		ReviewVo vo = dao.content(rno);
		int recommend=dao.getRecommend(rno);
		int police = dao.getPolice(rno);
		ArrayList<RcommentVo>rclist = dao.getInstance().rcomment(rno);
		req.setAttribute("rclist", rclist);
		req.setAttribute("recommend", recommend);
		req.setAttribute("police", police);
		req.setAttribute("vo", vo);
		req.setAttribute("page", "/review/jReviewContent.jsp");
		req.getSession().setAttribute("url", req.getRequestURL()  +"?" +req.getQueryString());
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int rno = Integer.parseInt(req.getParameter("rno"));
		String id= req.getParameter("id");
		ReviewDao dao = ReviewDao.getInstance();
		int n = dao.delete(rno, id);
		
		if(n>0) {
			resp.sendRedirect("/semiProject/review.do?cmd=list");
		}else {
			req.setAttribute("result", "cancel");
			
		}
	}
	public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rno = Integer.parseInt(req.getParameter("rno"));
		ReviewDao dao=ReviewDao.getInstance();
		ReviewVo vo=dao.update(rno);
		req.setAttribute("vo", vo);
		req.setAttribute("page", "/review/jReviewUpdate.jsp");
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	
		}
	
	public void recommend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rno = Integer.parseInt(req.getParameter("rno"));
		String id = req.getParameter("id");
		ReviewDao dao=ReviewDao.getInstance();
		int n=dao.oxrecommend(rno, id);
		if(n>0) {
			req.setAttribute("result", "동일게시물에는추천할수없습니다");
			req.setAttribute("result", "동일 게시물에는 추천할 수 없습니다.");
			content(req, resp);
		}else {
		int recommend = dao.recommend(rno, id);
		if(recommend>0) {
			req.setAttribute("result", "추천하였습니다.");
			content(req, resp);
		}else {

			content(req, resp);
		}
		}
		
	}
	
	public void police(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rno = Integer.parseInt(req.getParameter("rno"));
		String id = req.getParameter("id");
		ReviewDao dao=ReviewDao.getInstance();
		int n=dao.oxpolice(rno, id);
		
		if(n>0) {

			req.setAttribute("result", "이미 신고한 게시물입니다");
			content(req, resp);
		}else {

			int police = dao.police(rno, id);
			if(police>0) {
				req.setAttribute("result", "신고하였습니다.");
				content(req, resp);
			}else {
				content(req, resp);
			}
			}
		}
		

	
	public void updateOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	

		String uploadPath = "C:\\Users\\JHTA\\git\\semiproject\\semiProject\\WebContent\\image";


		MultipartRequest mr = new MultipartRequest(req, uploadPath, 1024 * 1024 * 5, "utf-8",
		new DefaultFileRenamePolicy());
		int rno=Integer.parseInt(mr.getParameter("rno"));
		System.out.println("rno:" + rno);
		String rtitle = mr.getParameter("title");
		System.out.println("title:" + rtitle);
		String rcontent = mr.getParameter("scontent");
		System.out.println("rcontent:" + rcontent);
		String orgfilename = mr.getOriginalFileName("up_files");
		String savefilename = mr.getFilesystemName("up_files");
		String id = (String) req.getSession().getAttribute("id");
		int company = Integer.parseInt(mr.getParameter("company"));
		
		ReviewVo vo = new ReviewVo(rno, rtitle, rcontent, null, 0, 0, orgfilename, savefilename, id, company,0,0);
		ReviewDao dao = ReviewDao.getInstance();
		int n = dao.update(vo);
		if (n > 0) {
			resp.sendRedirect("/semiProject/review.do?cmd=list");
		} else {
			System.out.println("fail");
		}
	}
	
}
