package Admin;
import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddMember extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String mob=request.getParameter("mob");
            String gen=request.getParameter("gen");
            String pswd=request.getParameter("pswd");
            String cpswd=request.getParameter("cpswd");
            String dob=request.getParameter("dob");
            DBConnection db=new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("select email from customer where email=?");
                db.pstmt.setString(1,email);
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    hs.setAttribute("error","Email Already Exist");
                    response.sendRedirect("Member");
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
                        db.pstmt.setString(5,dob);
                        db.pstmt.setString(6,pswd);
                        int i=db.pstmt.executeUpdate();
                        if(i>0)
                        {
                        hs.setAttribute("success","Member Added Successfully");
                        response.sendRedirect("Member");
                        }
                        else
                        {
                            hs.setAttribute("error","Something Went Wrong");
                            response.sendRedirect("Member");
                        }
                    }
                    else
                    {
                        hs.setAttribute("error","Password does not match");
                        response.sendRedirect("Member");
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