package Customer;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GymForm extends HttpServlet {

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
            out.println("<title>Buy Membership</title>");            
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
                    + "<div class='nav-link active'>Buy Membership</div>"
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
                    + "<div class='col-lg-3 col-md-3 col-sm-12'></div>"
                    + "<div class='col-lg-6 col-md-6 col-sm-12'>"
                    + "<h1 class='text-center' style='font-variant:small-caps;'>Gym Form</h1>"
                    + "<form action='Bill' method='post'>");
                    DateFormat df=new SimpleDateFormat("yyyy-mm-dd");
                    Date date1 = (Date) df.parse(dob);
                    Calendar now=Calendar.getInstance();
                    Calendar d=Calendar.getInstance();
                    d.setTime(date1);
                    if(d.after(now))
                    {
                    throw new IllegalArgumentException("Can not born in the future");
                    }
                    int year1=now.get(Calendar.YEAR);
                    int year2=d.get(Calendar.YEAR);
                    int age=year1-year2;
            out.println("<div class='form-group'>"
                    + "<label for='name'>Name :</label>"
                    + "<input type='text' name='name' id='name' class='form-control' value='"+name+"' readonly>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='email'>E-mail :</label>"
                    + "<input type='email' name='email' id='email' class='form-control' value='"+email+"' readonly>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='mob'>Mobile :</label>"
                    + "<input type='text' name='mob' id='mob' class='form-control' value='"+mob+"' readonly>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='gen'>Gender :</label>"
                    + "<input type='text' name='gender' id='gen' class='form-control'"
                    + " value='"+gen+"' readonly>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='age'>Age :</label>"
                    + "<input type='text' name='age' id='age' value='"+age+"' class='form-control' readonly>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='package'>Select Package :</label>"
                    + "<select class='form-control' name='pack' id='package'>");
                    try
                    {
                        db.pstmt=db.con.prepareStatement("select * from package");
                        db.rst=db.pstmt.executeQuery();
                        while(db.rst.next())
                        {
                            out.println("<option value='"+db.rst.getString(1)+"'>(Package : "
                                    + ""+db.rst.getString(1)+") (Duration : "+db.rst.getString(2)+" Months)"
                                    + " (Price : "+db.rst.getString(3)+")</option>");
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }       
            out.println("</select>"
                    + "</div>"
                    + "<div class='form-group text-center'>"
                    + "<button type='submit' class='btn btn-primary btn-block shadow'>SUBMIT</button>"
                    + "</div>"
                    + "</form>"
                    + "</div>"
                    + "<div class='col-lg-3 col-md-3 col-sm-12'></div>"
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