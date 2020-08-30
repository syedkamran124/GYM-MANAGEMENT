package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Membership2 extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {         
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String mob=request.getParameter("mob");
            String gender=request.getParameter("gender");
            String age=request.getParameter("age");
            String pack=request.getParameter("pack");
            DBConnection db=new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("select * from package where package=?");
                db.pstmt.setString(1,pack);
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    HttpSession hs=request.getSession();
                    int duration=db.rst.getInt(2);
                    String duration2=String.valueOf(duration);
                    String amount=db.rst.getString(3);                  
                    Date d=new Date();
                    SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
                    String join=sf.format(d);
                    Calendar c=Calendar.getInstance();
                    c.setTime(d);
                    c.add(Calendar.MONTH, +duration);
                    String expdate=sf.format(c.getTime());      
                    hs.setAttribute("pack",pack);
                    hs.setAttribute("duration",duration2);
                    hs.setAttribute("amount",amount);
                    hs.setAttribute("name",name);
                    hs.setAttribute("email",email);
                    hs.setAttribute("mob",mob);
                    hs.setAttribute("gender",gender);
                    hs.setAttribute("age",age);
                    hs.setAttribute("join",join);
                    hs.setAttribute("expire",expdate);
                    db.pstmt=db.con.prepareStatement("insert into member(name,email,mob,gen,age,pack,duration,"
                            + "amount,joining,expire) values(?,?,?,?,?,?,?,?,?,?)");
                    db.pstmt.setString(1,name);
                    db.pstmt.setString(2,email);
                    db.pstmt.setString(3,mob);
                    db.pstmt.setString(4,gender);
                    db.pstmt.setString(5,age);
                    db.pstmt.setString(6,pack);
                    db.pstmt.setInt(7,duration);
                    db.pstmt.setString(8,amount);
                    db.pstmt.setString(9,join);
                    db.pstmt.setString(10,expdate);
                    int i=db.pstmt.executeUpdate();
                    if(i>0)
                    {
                        response.sendRedirect("Payslip");
                    }
                    else
                    {
                        response.sendRedirect("Member");
                        hs.setAttribute("error","Something Went Wrong");
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
