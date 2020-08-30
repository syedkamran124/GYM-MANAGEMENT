import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLogin extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email=request.getParameter("email");
            String pswd=request.getParameter("pswd");
            DBConnection db=new DBConnection();
            HttpSession hs=request.getSession();
            try
            {
                db.pstmt=db.con.prepareStatement("select pswd from admin where email=?");
                db.pstmt.setString(1,email);
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    if(db.rst.getString(1).equals(pswd))
                    {
                        hs.setAttribute("email1",email);
                        response.sendRedirect("Dashboard");
                    }
                    else
                    {
                        response.sendRedirect("Admin.html?msg=Incorrect Password");
                    }
                }
                else
                {
                    response.sendRedirect("Admin.html?msg=Invalid Email");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
