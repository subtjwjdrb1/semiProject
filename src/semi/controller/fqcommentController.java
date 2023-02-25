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

import semi.dao.FqcommentDao;
import semi.vo.FqcommentVo;

@WebServlet("/fqcomment.do")
public class fqcommentController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		//System.out.println("cmdcmdcmdcmd:" + cmd);
		String id=(String)req.getSession().getAttribute("id");
		if(cmd.equals("insert") && id!=null ) {
			insert(req,resp);
		}else if(cmd.equals("list")) {
			list(req,resp);
		}
	
	}
	
	private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		String fqcontent = req.getParameter("fqcontent");
		
		String id=(String)req.getSession().getAttribute("id");
		int fqno=Integer.parseInt(req.getParameter("fqno"));
		String fqboardcno=req.getParameter("fqcno");
		
		int fqcno=0;
		int ref=0;
		int lev=0;
		int step=0;
		if(fqboardcno!=null && !(fqboardcno.equals(""))) {
			//System.out.println("왔다");
			fqcno=Integer.parseInt(fqboardcno);
			//System.out.println("fqcno>>>>>>>.<<<<<<"+fqcno);
			ref=Integer.parseInt(req.getParameter("fqcref"));
			lev=Integer.parseInt(req.getParameter("fqclev"));
			step=Integer.parseInt(req.getParameter("fqcstep"));
			//System.out.println("ref:"+ref+" lev:"+lev+" step:"+step);
		}
		FqcommentDao dao=FqcommentDao.getInstance();
		FqcommentVo vo=new FqcommentVo(fqcno, fqcontent, ref, lev, step, 0, null, fqno, id);
		int n=dao.insert(vo);
		System.out.println("n:"+n);
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
		int fqno=Integer.parseInt(req.getParameter("fqno"));
		FqcommentDao dao=FqcommentDao.getInstance();		
		ArrayList<FqcommentVo> list=dao.list(fqno);
		
		JSONArray arr=new JSONArray();
		for(int i=0;i<list.size();i++){
			FqcommentVo vo=list.get(i);
			JSONObject obj=new JSONObject();
			//System.out.println("id:"+vo.getId());
			obj.put("id",vo.getId());
			obj.put("comments",vo.getFqcontent());
			obj.put("ref", vo.getFqcref());
			obj.put("lev", vo.getFqclev());
			obj.put("step", vo.getFqcstep());
			obj.put("fqcno", vo.getFqno());
			arr.put(obj); 	
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.println(arr);
		pw.close();	
	}
}
