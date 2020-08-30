package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditEquipment extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String company=request.getParameter("company");
            String qty=request.getParameter("qty");
            String price=request.getParameter("price");
            String exercise=request.getParameter("exercise");
            DBConnection db=new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("update equipment set name=?, exercise=?, quantity=?, "
                        + "price=?, company=? where id=?");
                db.pstmt.setString(1,name);
                db.pstmt.setString(2,exercise);
                db.pstmt.setString(3,qty);
                db.pstmt.setString(4,price);
                db.pstmt.setString(5,company);
                db.pstmt.setString(6,id);
                int i=db.pstmt.executeUpdate();
                if(i>0)
                {
                    response.sendRedirect("Equipment");
                    hs.setAttribute("success","Equipment Updated Successfully");
                }
                else
                {
                    response.sendRedirect("Equipment");
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
