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

import semi.dao.ScommentDao;
import semi.vo.ScommentVo;

@WebServlet("/scomment.do")
public class ScommentController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		//System.out.println("cmd:" + cmd);
		String id=(String)req.getSession().getAttribute("id");
		if(cmd.equals("insert") && id!=null ) {
			insert(req,resp);
		}else if(cmd.equals("list")) {
			list(req,resp);
		}
	}
	
	private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		String sccontent = req.getParameter("sccontent");
		//System.out.println("mmmmmmmmmmmmmm:"+sccontent);
		String id=(String)req.getSession().getAttribute("id");
		int sno=Integer.parseInt(req.getParameter("sno"));
		String sellcno=req.getParameter("scno");
		//System.out.println("sno:"+sno+" scno:"+sellcno);
		int scno=0;
		int ref=0;
		int lev=0;
		int step=0;
		if(sellcno!=null && !(sellcno.equals(""))) {//답글인경우
			scno=Integer.parseInt(sellcno);
			ref=Integer.parseInt(req.getParameter("scref"));
			lev=Integer.parseInt(req.getParameter("sclev"));
			step=Integer.parseInt(req.getParameter("scstep"));
			//System.out.println("ref:"+ref+" lev:"+lev+" step:"+step);
		}
		ScommentDao dao=ScommentDao.getInstance();
		ScommentVo vo=new ScommentVo(scno, sccontent, ref, lev, step, 0, null, sno, id);
		int n=dao.insert(vo);
		//System.out.println("n:"+n);
		JSONObject json=new JSONObject();
		if(n>0) {
			json.put("result", "success");
		}else {
			json.put("result", "fail");
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		pw.close();
	}	
	
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		int sno=Integer.parseInt(req.getParameter("sno"));
		ScommentDao dao=ScommentDao.getInstance();		
		ArrayList<ScommentVo> list=dao.list(sno);
		
		//json배열로 응답하기
		JSONArray arr=new JSONArray();
		for(int i=0;i<list.size();i++){
			ScommentVo vo=list.get(i);
			JSONObject obj=new JSONObject();
			//System.out.println("id:"+vo.getId());
			obj.put("id",vo.getId());
			obj.put("comments",vo.getSccontent());
			obj.put("ref", vo.getScref());
			obj.put("lev", vo.getSclev());
			obj.put("step", vo.getScstep());
			obj.put("scno", vo.getScno());
			arr.put(obj); //json객체를 배열에 담기	
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.println(arr);
		pw.close();	
	}
}
