package Customer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Bill2 extends HttpServlet {

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
            String pack=(String)hs.getAttribute("pack");
            String duration=(String)hs.getAttribute("duration");
            String amount=(String)hs.getAttribute("amount");
            String name=(String)hs.getAttribute("name");
            String mob=(String)hs.getAttribute("mob");
            String gender=(String)hs.getAttribute("gender");
            String age=(String)hs.getAttribute("age");
            String join=(String)hs.getAttribute("join");
            String expire=(String)hs.getAttribute("expire");
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
                    + "<tr><th>Duration</th><td>"+duration+"</td><th>Pack Expire On</th><td>"+expire+"</td></tr>"
                    + "<tr style='font-size:25px;; font-variant:small-caps;'><td colspan='2'>Payment Status"
                    + "</td><td colspan='2' style='color:blue'>Paid</td></tr>"
                    + "</table>"
                    + "<a href='CustProfile' class='btn btn-outline-success btn-lg'>Back</a>"
                    + "<button onclick='window.print()' class='btn btn-outline-primary btn-lg ml-5'>PRINT"
                    + "</button>"
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
