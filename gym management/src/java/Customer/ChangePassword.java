package Customer;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String opswd=request.getParameter("opswd");
            String npswd=request.getParameter("npswd");
            String cpswd=request.getParameter("cpswd");
            String email=request.getParameter("email");
            DBConnection db=new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("select pswd from customer where email=?");
                db.pstmt.setString(1,email);
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    if(db.rst.getString(1).equals(opswd))
                    {
                        if(npswd.equals(cpswd))
                        {
                            db.pstmt=db.con.prepareStatement("update customer set pswd=? where email=?");
                            db.pstmt.setString(1,npswd);
                            db.pstmt.setString(2,email);
                            int i=db.pstmt.executeUpdate();
                            if(i>0)
                            {
                                response.sendRedirect("Logout");
                            }
                            else
                            {
                                hs.setAttribute("error","Something Went Wrong");
                                response.sendRedirect("CustProfile");
                            }
                        }
                        else
                        {
                            hs.setAttribute("error","New Password And Confirm Password Does Not Match");
                            response.sendRedirect("CustProfile");
                        }
                    }
                    else
                    {
                        hs.setAttribute("error","Old Password Does Not Match");
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
