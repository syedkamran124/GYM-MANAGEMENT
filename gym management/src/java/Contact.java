import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Contact extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String addr=request.getParameter("addr");
            String msg=request.getParameter("msg");
            DBConnection db = new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("insert into contact(name,email,address,msg)"
                   + " values(?,?,?,?)");
                db.pstmt.setString(1,name);
                db.pstmt.setString(2,email);
                db.pstmt.setString(3,addr);
                db.pstmt.setString(4,msg);
                int i=db.pstmt.executeUpdate();
                if(i>0)
                {
                    response.sendRedirect("index.html?msg=Contact Message sent Successfully");                        
                }
                else
                {
                    response.sendRedirect("index.html?msg=Something Went Wrong");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
