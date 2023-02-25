package semi.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import semi.dao.SellDao;

import semi.vo.SellVo;

@WebServlet("/sell.do")
public class SellController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		//System.out.println("cmd:"+cmd);
		String context=req.getContextPath();
		String id=(String)req.getSession().getAttribute("id");
		if(cmd.equals("sellList")) {
			list(req,resp);
        }else if(cmd.equals("insert") && id!=null) {
        	req.setAttribute("page", "/sell/insert.jsp");
        	req.getRequestDispatcher("/main.jsp").forward(req, resp);
        }else if(cmd.equals("insertOk")) {
        	insertOk(req,resp);
        }else if(cmd.equals("sdetail")) {
        	detail(req,resp);
        }else if(cmd.equals("delete")){
        	delete(req,resp);
        }else if(cmd.equals("update")) {
        	update(req,resp);
        }else if(cmd.equals("police")) {
			police(req,resp);
		}else if(cmd.equals("updateOk")) {
			updateOk(req,resp);
		}
	}
	
	private void police(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int sno = Integer.parseInt(req.getParameter("sno"));
		String id = req.getParameter("id");
		//System.out.println("sno:"+sno+"id:"+id);
		SellDao dao=SellDao.getInstance();
		int n=dao.oxpolice(sno, id);
		if(n>0) {
			req.setAttribute("result", "신고성공");
			detail(req, resp);
		}else {
			int police = dao.police(sno, id);
			if(police>0) {
				req.setAttribute("result", "신고실패");
				dao.updateReport(sno);
				detail(req, resp);
			}else {
				detail(req, resp);
			}
		}	
	}
	private void updateOk(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int sno=Integer.parseInt(req.getParameter("sno"));
		int os=Integer.parseInt(req.getParameter("os"));
		int telecom=Integer.parseInt(req.getParameter("telecom"));
		int company=Integer.parseInt(req.getParameter("company"));
		String loc=req.getParameter("loc");
		int price=Integer.parseInt(req.getParameter("price"));
		String stitle=req.getParameter("stitle");
		String scontent=req.getParameter("scontent");
		int success=Integer.parseInt(req.getParameter("success"));
		SellDao dao=SellDao.getInstance();
		SellVo vo=new SellVo(sno, os, telecom, company, loc, price, stitle, scontent, null, 0, 0, success, 0, null);
		int n=dao.updateOk(vo);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/sell.do?cmd=sellList");
		}else {
			req.setAttribute("result", "fail");
			resp.sendRedirect(req.getContextPath()+"/sell.do?cmd=sellList");
		}
		
	}
	
	private void update(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int sno = Integer.parseInt(req.getParameter("sno"));
		SellDao dao=SellDao.getInstance();
		SellVo vo=dao.update(sno);
		req.setAttribute("vo", vo);
		req.setAttribute("page", "/sell/update.jsp");
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int sno = Integer.parseInt(req.getParameter("sno"));
		String id= req.getParameter("id");
		SellDao dao = SellDao.getInstance();
		int n = dao.delete(sno, id);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/sell.do?cmd=sellList");
		}else {
			req.setAttribute("result", "cancel");
		}

	}
	
	private void detail(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int sno=Integer.parseInt(req.getParameter("sno"));
		SellDao dao=SellDao.getInstance();
		SellVo vo=dao.detail(sno);
		int police = dao.getPolice(sno);
		dao.updateHit(vo);
		req.setAttribute("vo", vo);
		req.setAttribute("police", police);
		req.setAttribute("page", "sell/sdetail.jsp");
		req.getRequestDispatcher("/main.jsp").forward(req, resp);
		
	}
	private void insertOk(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		String title = req.getParameter("title");
		String id=(String)req.getSession().getAttribute("id");
		int os = Integer.parseInt(req.getParameter("os"));
		int telecom = Integer.parseInt(req.getParameter("telecom"));
		int company = Integer.parseInt(req.getParameter("company"));
		String loc = req.getParameter("loc");
		int price = Integer.parseInt(req.getParameter("price"));
		String stitle = req.getParameter("stitle");
		String scontent = req.getParameter("scontent");
		int success=Integer.parseInt(req.getParameter("success"));
		System.out.println("stitle"+title);
		
		SellVo vo=new SellVo(0, os, telecom, company, loc, price, stitle, scontent, null, 0, 0, success,0,id);
		SellDao dao=SellDao.getInstance();
		int n=dao.insert(vo);
		//System.out.println("n:"+ n);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/sell.do?cmd=sellList");
		}else {
			req.setAttribute("result", "fail");
		}
	}
	
	private void list(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		String[] os1=req.getParameterValues("os");
		//System.out.println("os:" + os1);
		req.removeAttribute("os1");
		req.removeAttribute("os2");
		if(os1!=null) {
			for(String os:os1) {
				if(os.equals("1")) {
					req.setAttribute("os1",Integer.parseInt(os));
				}else if(os.equals("2")) {
					req.setAttribute("os2",Integer.parseInt(os));
				}
				//System.out.println("os:" + os);
			}
		}
		
		String[] telecom1=req.getParameterValues("telecom");
		req.removeAttribute("telecom1");
		req.removeAttribute("telecom2");
		req.removeAttribute("telecom3");
		req.removeAttribute("telecom4");
		if(telecom1!=null) {
			for(String telecom:telecom1) {
				if(telecom.equals("1")) {
					req.setAttribute("telecom1", Integer.parseInt(telecom));
				}else if(telecom.equals("2")) {
					req.setAttribute("telecom2", Integer.parseInt(telecom));
				}else if(telecom.equals("3")) {
					req.setAttribute("telecom3", Integer.parseInt(telecom));
				}else if(telecom.equals("4")) {
					req.setAttribute("telecom4", Integer.parseInt(telecom));
				}
			}
		}
		
		String[] company1=req.getParameterValues("company");
		req.removeAttribute("company1");
		req.removeAttribute("company2");
		req.removeAttribute("company3");
		req.removeAttribute("company4");
		if(company1!=null) {
			for(String company:company1) {
				if(company.equals("1")) {
					req.setAttribute("company1", Integer.parseInt(company));
				}else if(company.equals("2")) {
					req.setAttribute("company2", Integer.parseInt(company));
				}else if(company.equals("3")) {
					req.setAttribute("company3", Integer.parseInt(company));
				}else if(company.equals("4")) {
					req.setAttribute("company4", Integer.parseInt(company));
				}
			}
		}
		
		
		SellDao dao=SellDao.getInstance();
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		
		req.setAttribute("page", "/sell/sellList.jsp");	
		String sql=req.getParameter("sql");
		//System.out.println("sql:"+sql);
		String type=req.getParameter("type");
		if(type!=null && type.equals("form")){
			//System.out.println("controller sql:"+sql);
			String select=req.getParameter("select");	
			String text=req.getParameter("text");
			String chk=req.getParameter("sql");
			int pageCount1=0;
			//System.out.println("chk:" + chk);
			if(chk!=null && chk.equals("")) {
				pageCount1=(int)Math.ceil(dao.getCount()/10.0);
			}else {
				pageCount1=(int)Math.ceil(dao.getCount(chk)/10.0);
			}
			
			
			int startRow=(pageNum-1)*10+1;
			int endRow=startRow+9;
			int startPage = ((pageNum-1)/5)*5 + 1 ; 
			int endPage = startPage + 4;
			if(endPage>pageCount1) {
				endPage=pageCount1;
			}
			req.setAttribute("pageCount", pageCount1);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageNum", pageNum);
	    	//System.out.println("pageCount1:"+pageCount1);
			//System.out.println("startPage"+startPage);
			//System.out.println("endPage"+endPage);
			
			ArrayList<SellVo> sList=dao.searchlist(select, text, startRow, endRow, chk);
			req.setAttribute("slist", sList);
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		}else if(sql==null) {
			//System.out.println("startRow:"+startRow+"endRow:"+endRow);
			//System.out.println("sql:>>>>" + sql);
			
			int pageCount1=(int)Math.ceil(dao.getCount()/10.0);
			int startRow=(pageNum-1)*10+1;
			int endRow=startRow+9;
			int startPage = ((pageNum-1)/5)*5 + 1 ; 
			int endPage = startPage + 4;
			if(endPage>pageCount1) {
				endPage=pageCount1;
			}
			req.setAttribute("pageCount", pageCount1);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageNum", pageNum);
			ArrayList<SellVo> slist=dao.list(startRow,endRow);
			req.setAttribute("slist", slist);
			req.setAttribute("page", "/sell/sellList.jsp");
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		}else {	
			String select=req.getParameter("select");	
			String text=req.getParameter("text");
			String chk=req.getParameter("sql");
			//System.out.println("select:"+select +"text:"+text + "chk:" +chk);
			int pageCount1=(int)Math.ceil(dao.getCount(chk)/10.0);
			int startRow=(pageNum-1)*10+1;
			int endRow=startRow+9;
			int startPage = ((pageNum-1)/5)*5 + 1 ; 
			int endPage = startPage + 4;
			if(endPage>pageCount1) {
				endPage=pageCount1;
			}
			req.setAttribute("pageCount", pageCount1);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageNum", pageNum);
			
			ArrayList<SellVo> sList=dao.searchlist(select, text, startRow, endRow, chk);
			req.setAttribute("slist", sList);
			
			JSONArray arr=new JSONArray();
			for(int i=0;i<sList.size();i++) {
				SellVo vo=sList.get(i);
				JSONObject obj=new JSONObject();
				obj.put("success", vo.getSuccess());
				obj.put("title", vo.getStitle());
				obj.put("price", vo.getPrice());
				obj.put("id", vo.getId());
				obj.put("sno", vo.getSno());
				arr.put(obj);
			}

			JSONObject result=new JSONObject();
			result.put("list", arr);
			result.put("pageCount",pageCount1 );
			result.put("startPage", startPage );
			result.put("endPage", endPage );
			result.put("pageNum", pageNum);
			
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter pw=resp.getWriter();
			pw.println(result);
			pw.close();
		}
	}
	
}
