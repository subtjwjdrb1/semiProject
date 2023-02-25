package semi.adminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.MemberDao;
import semi.vo.MemberVo;

@WebServlet("/memberAdmin.do")
public class MemberAdminController extends HttpServlet{
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("utf-8");
            String cmd = req.getParameter("cmd");
            System.out.println(cmd);
            if(cmd.equals("list")) {
                list(req,resp);
            }else if(cmd.equals("delete")) {
                delete(req,resp);
            }
        }
        public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String sql=req.getParameter("sql");
            System.out.println(sql);
            int n = MemberDao.getInstance().delete(sql);
            System.out.println("n:"+n);
            if(n>0) {
                req.setAttribute("del", "삭제성공");
            }else {
                req.setAttribute("page", "/admin/member.jsp");
            }
            list(req,resp);
        }
        public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                ArrayList<MemberVo> list = null;
                if(text==null) {
                    getMax = MemberDao.getInstance().getMax();
                    list = MemberDao.getInstance().selectAll(startRow,endRow);
                }else {
                    System.out.println("select:"+req.getParameter("select"));
                    String select = req.getParameter("select");
                    list = MemberDao.getInstance().searchMember(select,text,startRow,endRow);
                    getMax=MemberDao.getInstance().getMax(select,text);
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
                req.setAttribute("page", "/admin/member.jsp");
                RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
                rd.forward(req, resp);
        }
    }
    

