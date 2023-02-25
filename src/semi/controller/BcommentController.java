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

import semi.dao.BcommentDao;
import semi.dao.ScommentDao;
import semi.vo.BcommentVo;
import semi.vo.ScommentVo;

@WebServlet("/bcomment.do")
public class BcommentController extends HttpServlet {
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
		String bccontent = req.getParameter("bccontent");
		
		String id=(String)req.getSession().getAttribute("id");
		int bno=Integer.parseInt(req.getParameter("bno"));
		String buycno=req.getParameter("bcno");
		
		int bcno=0;
		int ref=0;
		int lev=0;
		int step=0;
		if(buycno!=null && !(buycno.equals(""))) {//답글인경우
			bcno=Integer.parseInt(buycno);
			ref=Integer.parseInt(req.getParameter("bcref"));
			lev=Integer.parseInt(req.getParameter("bclev"));
			step=Integer.parseInt(req.getParameter("bcstep"));
			//System.out.println("ref:"+ref+" lev:"+lev+" step:"+step);
		}
		BcommentDao dao=BcommentDao.getInstance();
		BcommentVo vo=new BcommentVo(bcno, bccontent, ref, lev, step, 0, null, id, bno);
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
		int bno=Integer.parseInt(req.getParameter("bno"));
		BcommentDao dao=BcommentDao.getInstance();		
		ArrayList<BcommentVo> list=dao.list(bno);
		
		//json배열로 응답하기
		JSONArray arr=new JSONArray();
		for(int i=0;i<list.size();i++){
			BcommentVo vo=list.get(i);
			JSONObject obj=new JSONObject();
			//System.out.println("id:"+vo.getId());
			obj.put("id",vo.getId());
			obj.put("comments",vo.getBccontent());
			obj.put("ref", vo.getBcref());
			obj.put("lev", vo.getBclev());
			obj.put("step", vo.getBcstep());
			obj.put("scno", vo.getBcno());
			arr.put(obj); //json객체를 배열에 담기	
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.println(arr);
		pw.close();	
	}
}
