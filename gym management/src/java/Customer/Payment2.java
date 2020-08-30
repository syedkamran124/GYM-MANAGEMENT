package Customer;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Payment2 extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String pack=(String)hs.getAttribute("pack");
            String duration=(String)hs.getAttribute("duration");
            String amount=(String)hs.getAttribute("amount");
            String name=(String)hs.getAttribute("name");
            String email=(String)hs.getAttribute("email");
            String mob=(String)hs.getAttribute("mob");
            String gender=(String)hs.getAttribute("gender");
            String age=(String)hs.getAttribute("age");
            String join=(String)hs.getAttribute("join");
            String expire=(String)hs.getAttribute("expire");
            String ctype=request.getParameter("ctype");
            String namecard=request.getParameter("namecard");
            String cardno=request.getParameter("cardno");
            String cvv=request.getParameter("cvv");
            String exp=request.getParameter("exp");
            SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
            Date d=new Date();
            String date=sf.format(d);
            DBConnection db = new DBConnection();
            try
            {
                db.pstmt=db.con.prepareStatement("insert into transaction(email_uname,cardtype,namecard,"
                + "cardno,expiry,cvv,amount,status,date) values(?,?,?,?,?,?,?,?,?)");
                db.pstmt.setString(1,email);
                db.pstmt.setString(2,ctype);
                db.pstmt.setString(3,namecard);
                db.pstmt.setString(4,cardno);
                db.pstmt.setString(5,exp);
                db.pstmt.setString(6,cvv);
                db.pstmt.setString(7,amount);
                db.pstmt.setString(8,"paid");
                db.pstmt.setString(9,date);
                db.pstmt.executeUpdate();
                db.pstmt=db.con.prepareStatement("insert into member(name,email,mob,gen,age,pack,duration,"
                            + "amount,joining,expire) values(?,?,?,?,?,?,?,?,?,?)");
                db.pstmt.setString(1,name);
                db.pstmt.setString(2,email);
                db.pstmt.setString(3,mob);
                db.pstmt.setString(4,gender);
                db.pstmt.setString(5,age);
                db.pstmt.setString(6,pack);
                db.pstmt.setString(7,duration);
                db.pstmt.setString(8,amount);
                db.pstmt.setString(9,join);
                db.pstmt.setString(10,expire);
                int i=db.pstmt.executeUpdate();
                if(i>0)
                {
                    response.sendRedirect("Bill2");
                }    
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}