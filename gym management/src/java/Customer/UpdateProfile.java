package Customer;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProfile extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String name=request.getParameter("name");
            String mob=request.getParameter("mob");
            String dob=request.getParameter("dob");
            String gen=request.getParameter("gen");
            String gen1=request.getParameter("gen1");
            String email=request.getParameter("email");
            DBConnection db=new DBConnection();
            try
            {
                if(gen!=null)
                {
                    db.pstmt=db.con.prepareStatement("update customer set name=?, mob=?, dob=?, gen=? where "
                        + "email=?");
                    db.pstmt.setString(1,name);
                    db.pstmt.setString(2,mob);
                    db.pstmt.setString(3,dob);
                    db.pstmt.setString(4,gen);
                    db.pstmt.setString(5,email);
                    int i=db.pstmt.executeUpdate();
                    if(i>0)
                    {
                        hs.setAttribute("success","Updated Successfully");
                        response.sendRedirect("CustProfile");
                    }
                    else
                    {
                        hs.setAttribute("error","Something Went Wrong");
                        response.sendRedirect("CustProfile");
                    }
                }
                else
                {
                    db.pstmt=db.con.prepareStatement("update customer set name=?, mob=?, dob=?, gen=? where "
                        + "email=?");
                    db.pstmt.setString(1,name);
                    db.pstmt.setString(2,mob);
                    db.pstmt.setString(3,dob);
                    db.pstmt.setString(4,gen1);
                    db.pstmt.setString(5,email);
                    int i=db.pstmt.executeUpdate();
                    if(i>0)
                    {
                        hs.setAttribute("success","Updated Successfully");
                        response.sendRedirect("CustProfile");
                    }
                    else
                    {
                        hs.setAttribute("error","Something Went Wrong");
                        response.sendRedirect("CustProfile");
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
