package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddTrainer extends HttpServlet {

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
                db.pstmt=db.con.prepareStatement("select email from trainer where email=?");
                db.pstmt.setString(1,email);
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    hs.setAttribute("error","Email Already Exist");
                    response.sendRedirect("Trainer");
                }
                else
                {
                    db.pstmt=db.con.prepareStatement("insert into trainer(name,aadhar,email,mob,gen,dob,"
                        + "experience,speciality) values(?,?,?,?,?,?,?,?)");
                    db.pstmt.setString(1,name);
                    db.pstmt.setString(2,adhar);
                    db.pstmt.setString(3,email);
                    db.pstmt.setString(4,mob);
                    db.pstmt.setString(5,gen);
                    db.pstmt.setString(6,dob);
                    db.pstmt.setString(7,exp);
                    db.pstmt.setString(8,spec);
                    int i=db.pstmt.executeUpdate();
                    if(i>0)
                    {
                        hs.setAttribute("success","Trainer Added Successfully");
                        response.sendRedirect("Trainer");
                    }
                    else
                    {
                        hs.setAttribute("error","Something Went Wrong");
                        response.sendRedirect("Trainer");
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
