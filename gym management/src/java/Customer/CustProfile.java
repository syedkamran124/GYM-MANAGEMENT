package Customer;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustProfile extends HttpServlet {

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
            String success=(String)hs.getAttribute("success");
            String error=(String)hs.getAttribute("error");
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
            out.println("<title>Profile</title>");            
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
                    + "<div class='nav-link active'>My Profile</div>"
                    + "</li>");
            try
            {
                db.pstmt=db.con.prepareStatement("select * from member where email=?");
                db.pstmt.setString(1,email);
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    out.println("<li class='nav-item'>"
                    + "<a href='' data-toggle='modal' class='nav-link' data-target='#member'>Membership</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a href='DietPlan' class='nav-link'>Diet Plan</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a href='Exercise' class='nav-link'>Exercise</a>"
                    + "</li>"        
                    + "<div class='modal' tabindex='-1' role='dialog' id='member'>" 
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
                    + "<a href='GymForm' class='nav-link'>Buy Membership</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a href='' data-toggle='modal' class='nav-link' data-target='#diet'>Diet Plan</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a href='' data-toggle='modal' class='nav-link' data-target='#exercise'>Exercise</a>"
                    + "</li>"
                    + "<div class='modal' tabindex='-1' role='dialog' id='diet'>" 
                    + "<div class='modal-dialog'>" 
                    + "<div class='modal-content'>" 
                    + "<div class='modal-header'>" 
                    + "<div class='modal-title'>Diet Plan</div>"
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>&times;</button>" 
                    + "</div>" 
                    + "<div class='modal-body'>"
                    + "<p class='text-center'>You are not authorized to view this until and unless you will"
                    + " buy membership plans.</p>"
                    + "</div>" 
                    + "<div class='modal-footer'>" 
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>CLOSE</button>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>"
                    + "<div class='modal' tabindex='-1' role='dialog' id='exercise'>" 
                    + "<div class='modal-dialog'>" 
                    + "<div class='modal-content'>" 
                    + "<div class='modal-header'>" 
                    + "<div class='modal-title'>Exercise</div>"
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>&times;</button>" 
                    + "</div>" 
                    + "<div class='modal-body'>"
                    + "<p class='text-center'>You are not authorized to view this until and unless you will"
                    + " buy membership plans.</p>"
                    + "</div>" 
                    + "<div class='modal-footer'>" 
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>CLOSE</button>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>");
                }
            }
            catch(Exception e)
            {
             e.printStackTrace();   
            }
            out.println("<li class='nav-item'>"
                    + "<a href='Logout' class='nav-link'>Logout</a>"
                    + "</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "<div class='container-fluid'>"
                    + "<div class='row'>"
                    + "<div class='col-lg-6 col-md-6 col-sm-12'>"
                    + "<table class='table table-bordered shadow' style='margin-top:100px;'>"
                    + "<tr class='text-center' style='font-variant:small-caps; font-size:25px;"
                    + " border:2px solid black; background-color:blue;'><th>*</th><th>Contents</th>"
                    + "<th>Details</th></tr>"
                    + "<tr class='text-center' style='font-size:20px; color:green;'><td>1)</td>"
                    + "<td style='font-variant:small-caps;'>Name</td><td>"+name+"</td></tr>"
                    + "<tr class='text-center' style='font-size:20px; color:green;'><td>2)</td>"
                    + "<td style='font-variant:small-caps'>E-mail</td><td>"+email+"</td></tr>"
                    + "<tr class='text-center' style='font-size:20px; color:green;'><td>3)</td>"
                    + "<td style='font-variant:small-caps'>Mobile</td><td>"+mob+"</td></tr>"
                    + "<tr class='text-center' style='font-size:20px; color:green;'><td>4)</td>"
                    + "<td style='font-variant:small-caps'>Date Of Birth</td><td>"+dob+"</td></tr>"
                    + "<tr class='text-center' style='font-size:20px; color:green;'><td>5)</td>"
                    + "<td style='font-variant:small-caps'>Gender</td><td>"+gen+"</td></tr>"
                    + "</table>"
                    + "</div>"
                    + "<div class='col-lg-6 col-md-6 col-sm-12'>"
                    + "<div id='accordion' style='margin-top:100px;'>"
                    + "<div class='card'>"
                    + "<div class='card-header'>"
                    + "<button class='btn-block py-4' data-toggle='collapse' style='background-color:blue;"
                    + " font-size:25px; font-variant:small-caps;' data-target='#collapseone' aria-expanded='true'>"
                    + "Update Profile</button>"
                    + "</div>"
                    + "<div class='collapse' id='collapseone' data-parent='#accordion'>"
                    + "<div class='card-body'>"      
                    + "<form action='UpdateProfile' method='post'>"
                    + "<div class='form-group'>"
                    + "<label for='name'>Name</label>"
                    + "<input type='text' name='name' id='name' value="+name+" class='form-control' required>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='mob'>Mobile</label>"
                    + "<input type='number' name='mob' id='mob' value="+mob+" class='form-control' required>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='dob'>Date of Birth</label>"
                    + "<input type='date' name='dob' id='dob' value="+dob+" class='form-control' required>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='gender'>Gender</label>"
                    + "<input type='text' id='gender' name='gen1' value="+gen+" class='form-control' readonly>"
                    + "</div>"        
                    + "<div clas='form-group'>"
                    + "<label class='mr-5'>Select Gender</label>"
                    + "<div class='form-check form-check-inline'>"
                    + "<input type='radio' name='gen' id='male' value='Male' class='form-check-input ml-2'"
                    + ">"
                    + "<label for='male' class='form-check-label'>Male</label>"
                    + "</div>"
                    + "<div class='form-check form-check-inline ml-5'>"
                    + "<input type='radio' name='gen' id='female' value='Female'"
                    + " class='form-check-input ml-2'>"
                    + "<label for='female' class='form-check-label'>Female</label>"
                    + "</div>"
                    + "</div>"
                    + "<button type='submit' class='btn btn-outline-primary btn-block shadow' name='email'"
                    + " value='"+email+"'>UPDATE</button>"
                    +"</form>"
                    + "</div>"
                    + "</div>"
                    + "</div>"        
                    + "<div class='card'>"
                    + "<div class='card-header'>"
                    + "<button class='btn-block py-4' data-toggle='collapse' style='background-color:blue;"
                    + " font-size:25px; font-variant:small-caps;' data-target='#collapsethree' aria-expanded='true'>"
                    + "Change Password</button>"
                    + "</div>"
                    + "<div class='collapse' id='collapsethree' data-parent='#accordion'>"
                    + "<div class='card-body'>"
                    + "<form action='ChangePassword' method='post'>"
                    + "<div class='form-group'>"
                    + "<label for='opswd'>Old Password</label>"
                    + "<input type='password' name='opswd' id='opswd' class='form-control' required>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='npswd'>New Password</label>"
                    + "<input type='password' name='npswd' id='npswd' class='form-control' required>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='cpswd'>Confirm Password</label>"
                    + "<input type='password' name='cpswd' id='cpswd' class='form-control' required>"
                    + "</div>"
                    + "<small id='emailHelp' class='form-text text-muted text-center'>"
                    + "You will have to login again if you change your Password.</small><br>"
                    + "<button type='submit' class='btn btn-outline-primary btn-block shadow'"
                    + "name='email' value='"+email+"'>CHANGE</button>"
                    + "</form>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "</div>");
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