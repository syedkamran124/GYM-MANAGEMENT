package Admin;

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

public class Membership extends HttpServlet {

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
            String id=request.getParameter("id");
            String email=request.getParameter("email");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>");
            out.println("<script  src='https://code.jquery.com/jquery-3.4.1.min.js' integrity='sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=' crossorigin='anonymous'></script>");
            out.println("<title>Membership</title>");            
            out.println("</head>");
            out.println("<body>");
            try
            {     
                db.pstmt=db.con.prepareStatement("select * from member where email='"+email+"'");
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    out.println("<div class='container-fluid'>"
                            + "<div class='row'>" 
                            + "<div class='col-lg-12 col-md-12 col-sm-12' style='margin-top:100px;'>" 
                            + "<h1 class='display-4 text-center'>Membership Details</h1>" 
                            + "</div>" 
                            + "</div>" 
                            + "<div class='row mt-3'>"
                            + "<div class='col-lg-3 col-md-3 col-sm-12'></div>"
                            + "<div class='col-lg-6 col-md-6 col-sm-12' style='margin-top:20px;'>"
                            + "<table class='table table-bordered shadow'>"
                            + "<tr class='text-center' style='font-variant:small-caps; font-size:25px;"
                            + " border:2px solid black; background-color:blue;'><th>*</th><th>Contents</th>"
                            + "<th>Details</th></tr>"
                            + "<tr class='text-center' style='font-size:20px; color:green;'><td>1)</td>"
                            + "<td style='font-variant:small-caps;'>Activated Pack</td>"
                            + "<td>"+db.rst.getString(7)+"</td></tr>"
                            + "<tr class='text-center' style='font-size:20px; color:green;'><td>2)</td>"
                            + "<td style='font-variant:small-caps;'>Duration</td>"
                            + "<td>"+db.rst.getString(8)+"</td></tr>"
                            + "<tr class='text-center' style='font-size:20px; color:green;'><td>3)</td>"
                            + "<td style='font-variant:small-caps;'>Amount You Paid</td>"
                            + "<td>"+db.rst.getString(9)+"</td></tr>"
                            + "<tr class='text-center' style='font-size:20px; color:green;'><td>4)</td>"
                            + "<td style='font-variant:small-caps;'>Joining Date</td>"
                            + "<td>"+db.rst.getString(10)+"</td></tr>"
                            + "<tr class='text-center' style='font-size:20px; color:green;'><td>5)</td>"
                            + "<td style='font-variant:small-caps;'>Pack Expire Date</td>"
                            + "<td>"+db.rst.getString(11)+"</td></tr>"
                            + "</table>"
                            + "<a href='Member' class='btn btn-outline-warning btn-lg'>Back</a>"        
                            + "</div>"
                            + "<div class='col-lg-3 col-md-3 col-sm-12'></div>"
                            + "</div>"
                            + "</div>");
                }
                else 
                {
                    out.println("<div class='container'>" 
                            + "<div class='row'>" 
                            + "<div class='col-lg-12 col-md-12 col-sm-12' style='margin-top:30px;'>" 
                            + "<h1 class='display-4 text-center'>Membership Form</h1>" 
                            + "</div>" 
                            + "</div>" 
                            + "<div class='row'>" 
                            + "<div class='col-lg-12 col-md-12 col-sm-12'>" 
                            + "</div>" 
                            + "</div>");
                try
                {
                    db.pstmt=db.con.prepareStatement("select * from customer where id='"+id+"'");
                    db.rst=db.pstmt.executeQuery();
                    if(db.rst.next())
                    {
                        String m=db.rst.getString(4);
                        String e=db.rst.getString(3);
                        String n=db.rst.getString(2);
                        String gen=db.rst.getString(5);
                        String d=db.rst.getString(6);
                        DateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
                        Date date1=df.parse(d);
                        Calendar  now=Calendar.getInstance();
                        Calendar dob=Calendar.getInstance();
                        dob.setTime(date1);
                        if(dob.after(now)) 
                        {
                            throw new IllegalArgumentException("Can't be born in the future");
                        }
                        int year1=now.get(Calendar.YEAR);
                        int year2=dob.get(Calendar.YEAR);
                        int age=year1-year2;
                        out.println("<div class='row'>"
                                + "<div class='col-lg-3 col-md-3 col-sm-12'></div>"
                                + "<div class='col-lg-6 col-md-6 col-sm-12'>"
                                + "<form action='Membership2' method='post' class='my-5 border p-4 rounded"
                                + " shadow'>" 
                                + "<div class='form-group'>"
                                + "<label for='name'>Name :</label>"
                                + "<input type='text' name='name' id='name' class='form-control'"
                                + " value='"+n+"' readonly>"
                                + "</div>"
                                + "<div class='form-group'>"
                                + "<label for='email'>E-mail :</label>"
                                + "<input type='email' name='email' id='email' class='form-control'"
                                + " value='"+e+"' readonly>"
                                + "</div>"
                                + "<div class='form-group'>"
                                + "<label for='mob'>Mobile :</label>"
                                + "<input type='text' name='mob' id='mob' class='form-control' value='"+m+"'"
                                + " readonly>"
                                + "</div>"
                                + "<div class='form-group'>"
                                + "<label for='gen'>Gender :</label>"
                                + "<input type='text' name='gender' id='gen' class='form-control'"
                                + " value='"+gen+"' readonly>"
                                + "</div>"
                                + "<div class='form-group'>"
                                + "<label for='age'>Age :</label>"
                                + "<input type='text' name='age' id='age' value='"+age+"' class='form-control'"
                                + " readonly>"
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
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                        out.println("</select>"
                                + "</div>"
                                + "<div class='text-center'><button type='submit' class='btn btn-primary"
                                + " btn-block'>SUBMIT</button>"
                                + "</div>" 
                                + "</form>"
                                + "<a href='Member' class='btn btn-outline-warning btn-lg'>Back</a>"
                                + "</div>"
                                + "<div class='col-lg-3 col-md-3 col-sm-12'></div>"
                                + "</div>");   
                    }
                    out.println("<script src='https://code.jquery.com/jquery-3.4.1.slim.min.js' integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n' crossorigin='anonymous'></script>"
                            + "<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js' integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo' crossorigin='anonymous'></script>"
                            + "<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js' integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6' crossorigin='anonymous'></script>");
                    out.println("</body>");
                    out.println("</html>");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
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
