package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminContact extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs=request.getSession();
            String email1=(String)hs.getAttribute("email1");
            if(email1==null)
            {
                response.sendRedirect("Admin.html?msg=Please Login First"); 
            }
            DBConnection db=new DBConnection();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>");
            out.println("<title>Messages</title>");            
            out.println("</head>");
            out.println("<body>");
            String success=(String)hs.getAttribute("success");
            String error=(String)hs.getAttribute("error");
            out.println("<div class='container-fluid'>" 
                    + "<div class='row'>" 
                    + "<div class='col-lg-3 col-md-3 col-sm-12'></div>" 
                    + "<div class='col-lg-8 col-md-8 col-sm-12'>");
            if(success!=null)
            {
                out.println("<div class='alert alert-success alert-dismissible fade show' role='alert'>" 
                        + "<strong>Success Message : </strong>"+success+"" 
                        + "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" 
                        + "<span aria-hidden='true'>&times;</span>" 
                        + "</button></a>" 
                        + "</div>");
                hs.removeAttribute("success");
            }
            if(error!=null)
            {
                out.println("<div class='alert alert-success alert-dismissible fade show' role='alert'>" 
                        + "<strong>Error Message : </strong>"+error+"" 
                        + "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" 
                        + "<span aria-hidden='true'>&times;</span>" 
                        + "</button></a>" 
                        + "</div>");
                hs.removeAttribute("error");
            }        
            out.println("</div>" 
                    + "<div class='col-lg-1 col-md-1 col-sm-12'></div>" 
                    + "</div>");
            out.println("<div class='container'>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12' style='margin-top:180px;'>"
                    + "<img src='images/logo.png'>"
                    + "<p style='font-size:40px; margin-top:10px;'>Gold Gym</p>"
                    + "</div>"
                    + "</div>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12'>"
                    + "<table class='table table-bordered'>"
                    + "<tr><th>Name</th><th>Email</th><th>Address</th><th>Message</th>"
                    + "<th>Reply to Message</th></tr>");
            try
            {
                db.pstmt=db.con.prepareStatement("select * from contact where status='0'");
                db.rst=db.pstmt.executeQuery();
                while(db.rst.next())
                {
                    int id=db.rst.getInt(1);
                    out.println("<tr><td>"+db.rst.getString(2)+"</td><td>"+db.rst.getString(3)+"</td>"
                            + "<td>"+db.rst.getString(4)+"</td><td>"+db.rst.getString(5)+"</td>"
                            + "<td><form action='Reply'>"
                            + "<div class='form-group'>"
                            + "<textarea name='reply' class='form-control'></textarea>"
                            + "</div>"
                            + "<div class='form-group'>"
                            + "<button type='submit' name='id' value='"+id+"' class='btn btn-primary'>"
                            + "SEND</button>"
                            + "</div>"
                            + "</form></td></tr>");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            out.println("</table>"
                    + "<a href='Dashboard' class='btn btn-outline-warning btn-lg'>Back</a>"
                    + "</div>"
                    + "</div></div>");
            out.println("<script src='https://code.jquery.com/jquery-3.4.1.slim.min.js' integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n' crossorigin='anonymous'></script>"
                      + "<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js' integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo' crossorigin='anonymous'></script>"
                      + "<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js' integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6' crossorigin='anonymous'></script>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
