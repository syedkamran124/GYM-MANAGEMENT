package Customer;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Exercise extends HttpServlet {

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
           DBConnection db=new DBConnection();
           try
           {
               db.pstmt=db.con.prepareStatement("select * from customer where email=?");
               db.pstmt.setString(1,email);
               db.rst=db.pstmt.executeQuery();
               if(db.rst.next())
               {
             String name=db.rst.getString(2);
             String mob=db.rst.getString(4);
             String gen=db.rst.getString(5);
             String dob=db.rst.getString(6);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>");
            out.println("<script  src='https://code.jquery.com/jquery-3.4.1.min.js' integrity='sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=' crossorigin='anonymous'></script>");
            out.println("<title>Exercise</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=container>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12'>"
                    + "<h1 class='display-4 text-center'>Welcome "+name+"</h1>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "<div class='container-fluid'>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12'>"
                    + "<ul class='nav nav-tabs'>"
                    + "<li class='nav-item'>"
                    + "<a href='CustProfile' class='nav-link'>My Profile</a>"
                    + "</li>");
            try
            {
                db.pstmt=db.con.prepareStatement("select * from member where email=?");
                db.pstmt.setString(1,email);
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    out.println("<li class='nav-item'>"
                    + "<a href='' data-toggle='modal' class='nav-link' data-target='#show'>Membership</a>"
                    + "</li>"
                    + "<div class='modal' tabindex='-1' role='dialog' id='show'>" 
                    + "<div class='modal-dialog'>" 
                    + "<div class='modal-content'>" 
                    + "<div class='modal-header'>" 
                    + "<div class='modal-title'>Membership</div>"
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>&times;</button>" 
                    + "</div>" 
                    + "<div class='modal-body'>"
                    + "<table class='table table-bordered'>" 
                    + "<tr><th>Activated Pack</th><td>"+db.rst.getString(7)+"</td></tr>"
                    + "<tr><th>Duration</th><td>"+db.rst.getString(8)+"</td></tr>"
                    + "<tr><th>Amount</th><td>"+db.rst.getString(9)+"</td></tr>"
                    + "<tr><th>Joining Date</th><td>"+db.rst.getString(10)+"</td></tr>"
                    + "<tr><th>Pack Expire Date</th><td>"+db.rst.getString(11)+"</td></tr>" 
                    + "</table>" 
                    + "</div>" 
                    + "<div class='modal-footer'>" 
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>CLOSE</button>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>");
                }
                else
                {
                    out.println("<li class='nav-item'>"
                    + "<a href='GymForm' class='nav-link'>Gym Form</a>"
                    + "</li>");
                }
            }
            catch(Exception e)
            {
             e.printStackTrace();   
            }
            out.println("<li class='nav-item'>"
                    + "<a href='DietPlan' class='nav-link'>Diet Plan</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<div class='nav-link active'>Exercise</div>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a href='Logout' class='nav-link'>Logout</a>"
                    + "</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</div>");
            out.println("<div class='container'>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12' style='margin-top:20px;'>"
                    + "<img src='images/logo.png'>"
                    + "<p style='font-size:40px; margin-top:10px;'>Gold Gym</p>"
                    + "</div>"
                    + "</div>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12' style='margin-top:20px;'>"
                    + "<img src='images/exercise.jpg' alt='exercise' class='img img-thumbnail'>"
                    + "</div>"
                    + "</div>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12' style='margin-top:15px;'>"
                    + "<button onclick='window.print()' class='btn btn-outline-primary btn-lg'>PRINT</button>"
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
           catch(Exception e)
           {
               e.printStackTrace();
           }
        }
    }
}