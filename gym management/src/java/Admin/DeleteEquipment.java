package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteEquipment extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String id=request.getParameter("id");
            DBConnection db= new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("delete from equipment where id='"+id+"'");
                int i=db.pstmt.executeUpdate();
                if(i>0)
                {
                    response.sendRedirect("Equipment");
                    hs.setAttribute("success","Equipment Deleted Successfully");
                }
                else
                {  
                    response.sendRedirect("Equipment");
                    hs.setAttribute("error","Problem In Deletion");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
