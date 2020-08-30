package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditTrainer extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {          
            HttpSession hs=request.getSession();
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String mob=request.getParameter("mob");
            String gen=request.getParameter("gen");
            String adhar=request.getParameter("adhar");
            String dob=request.getParameter("dob");
            String exp=request.getParameter("exp");
            String spec=request.getParameter("spec");
            DBConnection db=new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("update trainer set name=?, aadhar=?, mob=?, gen=?, dob=?,"
                        + " experience=?, speciality=? where email=?");
                db.pstmt.setString(1,name);
                db.pstmt.setString(2,adhar);
                db.pstmt.setString(3,mob);
                db.pstmt.setString(4,gen);
                db.pstmt.setString(5,dob);
                db.pstmt.setString(6,exp);
                db.pstmt.setString(7,spec);
                db.pstmt.setString(8,email);
                int i=db.pstmt.executeUpdate();
                if(i>0)
                {
                    response.sendRedirect("Trainer");
                    hs.setAttribute("success","Trainer Updated Successfully");
                }
                else
                {
                    response.sendRedirect("Trainer");
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
