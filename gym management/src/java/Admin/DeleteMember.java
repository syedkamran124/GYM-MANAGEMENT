package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteMember extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String email=request.getParameter("email");
            try
            {
                DBConnection db=new DBConnection();
                db.pstmt=db.con.prepareStatement("delete from customer where email='"+email+"'");
                int i=db.pstmt.executeUpdate();
                if(i>0)
                {
                    db.pstmt=db.con.prepareStatement("delete from member where email='"+email+"'");
                    int i1=db.pstmt.executeUpdate();
                    if(i1>0)
                    {
                        response.sendRedirect("Member");
                        hs.setAttribute("success","Member And Membership Deleted Successfully");
                    }
                    else
                    {
                        response.sendRedirect("Member");
                        hs.setAttribute("error","Member Deleted Successfully");
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
