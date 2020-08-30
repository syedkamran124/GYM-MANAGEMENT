
import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String mob=request.getParameter("mob");
            String gen=request.getParameter("gen");
            String pswd=request.getParameter("pswd");
            String cpswd=request.getParameter("cpswd");
            String date=request.getParameter("date");
            DBConnection db=new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("select email from customer where email=?");
                db.pstmt.setString(1,email);
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    response.sendRedirect("index.html?msg=Email Already Exist");
                }
                else
                {
                    if(pswd.equals(cpswd))
                    {
                        db.pstmt=db.con.prepareStatement("insert into customer(name,email,mob,gen,dob,pswd) "
                        + "values(?,?,?,?,?,?)");
                        db.pstmt.setString(1,name);
                        db.pstmt.setString(2,email);
                        db.pstmt.setString(3,mob);
                        db.pstmt.setString(4,gen);
                        db.pstmt.setString(5,date);
                        db.pstmt.setString(6,pswd);
                        int i=db.pstmt.executeUpdate();
                        if(i>0)
                        {
                            response.sendRedirect("index.html?msg=Registered Successfully");
                        }
                        else
                        {
                            response.sendRedirect("index.html?msg=Something Went Wrong");
                        }
                    }
                    else
                    {
                        response.sendRedirect("index.html?msg=Password Does Not Match");
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