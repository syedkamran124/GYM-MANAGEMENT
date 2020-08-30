package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Reply extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String id=request.getParameter("id");
            String reply=request.getParameter("reply");
            DBConnection db=new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("update contact set reply=?, status='1' where id=?");
                db.pstmt.setString(1,reply);
                db.pstmt.setString(2,id);
                int i=db.pstmt.executeUpdate();
                if(i>0)
                {
                    response.sendRedirect("AdminContact");
                    hs.setAttribute("success","Reply Sent Successfully");
                }
                else
                {
                    response.sendRedirect("AdminContact");
                    hs.setAttribute("error","Something Went Wrong");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
