package Customer;

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

public class Bill extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String email=(String)hs.getAttribute("email");
            if(email==null)
            {
                response.sendRedirect("index.html?msg=Please Login First");
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>");
            out.println("<title>Bill</title>");            
            out.println("</head>");
            out.println("<body>");
            String name=request.getParameter("name");
            String mob=request.getParameter("mob");
            String gender=request.getParameter("gender");
            String age=request.getParameter("age");
            String pack=request.getParameter("pack");;
            Date d=new Date();
            try
            {
                DBConnection db=new DBConnection();
                db.pstmt=db.con.prepareStatement("select * from package where package=?");
                db.pstmt.setString(1,pack);
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    int duration=db.rst.getInt(2);
                    String duration2=String.valueOf(duration);
                    String amount=db.rst.getString(3);
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
                    out.println("<div class='container'>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12' style='margin-top:20px;'>"
                    + "<img src='images/logo.png'>"
                    + "<p style='font-size:40px; margin-top:10px;'>Gold Gym</p>"
                    + "</div>"
                    + "</div>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12' style='margin-top:20px;'>"
                    + "<table class='table table-bordered'>"
                    + "<tr><th colspan=4>Member Details</th></tr>"
                    + "<tr><th>Name</th><td>"+name+"</td><th>Email</th><td>"+email+"</td></tr>"
                    + "<tr><th>Mobile</th><td>"+mob+"</td><th>Gender</th><td>"+gender+"</td></tr>"
                    + "<tr><th>Age</th><td>"+age+"</td><th>Join Date</th><td>"+join+"</td></tr>"
                    + "<tr><th colspan=4>Pack Details</th></tr>"
                    + "<tr><th>Amount</th><td>"+amount+"</td><th>Pack</th><td>"+pack+"</td></tr>"
                    + "<tr><th>Duration</th><td>"+duration+"</td><th>Pack Expire On</th><td>"+expdate+"</td></tr>");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            out.println("<tr><td colspan='4' class='text-center'><a href='Payment'"
                    + " class='btn btn-primary btn-block'>Proceed To Payment</a></td></tr>"
                    + "</table>"
                    + "<a href='GymForm' class='btn btn-outline-warning btn-lg'>Back</a>"
                    + "</div>"
                    + "</div>"
                    + "</div>");
            out.println("<script src='https://code.jquery.com/jquery-3.4.1.slim.min.js' integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n' crossorigin='anonymous'></script>"
                      + "<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js' integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo' crossorigin='anonymous'></script>"
                      + "<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js' integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6' crossorigin='anonymous'></script>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
