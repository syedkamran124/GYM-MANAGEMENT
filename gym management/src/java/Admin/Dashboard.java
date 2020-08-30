package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Dashboard extends HttpServlet {

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
            out.println("<script  src='https://code.jquery.com/jquery-3.4.1.min.js' integrity='sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=' crossorigin='anonymous'></script>");
            out.println("<title>Dashboard</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container-fluid'>" 
                    + "<div class='row mt-2'>" 
                    + "<div class='col-lg-3 col-md-3 col-sm-12'><h2>Welcome Admin</h2></div>" 
                    + "<div class='col-lg-7 col-md-7 col-sm-12'><marquee>");
            if(email1!=null)
            {
                out.println(email1);
            }
            out.println("</marquee></div>" 
                    + "<div class='col-lg-1 col-md-1 col-sm-12'>");
            try
            {
                db.pstmt=db.con.prepareStatement("select count(id) from contact where status='0'");
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    int msg=db.rst.getInt(1);
                    out.println("<a href='AdminContact' class='btn btn-outline-success btn-sm'>Notification"
                            + "<span class='badge badge-primary ml-1'>"+msg+"</span></a>");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            out.println("</div><div class='col-lg-1 col-md-1 col-sm-12'>"
                    + "<a href='Logout1' class='btn btn-outline-warning btn-sm'>LOGOUT</a>" 
                    + "</div>" 
                    + "</div>" 
                    + "<div class='row mt-2'>" 
                    + "<div class='col-lg-3 col-md-3 col-sm-12'>" 
                    + "<ul class='nav nav-pills flex-column'>" 
                    + "<li class='nav-item'>" 
                    + "<div class='nav-link active'>Home</div>" 
                    + "</li>" 
                    + "<li class='nav-item'>" 
                    + "<a class='nav-link' href='Member'>Members</a>" 
                    + "</li>" 
                    + "<li class='nav-item'>" 
                    + "<a class='nav-link' href='Trainer'>Trainers</a>" 
                    + "</li>" 
                    + "<li class='nav-item'>" 
                    + "<a class='nav-link' href='Equipment'>Equipments</a>" 
                    + "</li>" 
                    + "</ul>" 
                    + "</div>" 
                    + "<div class='col-lg-3 col-md-3 col-sm-12'>" 
                    + "<div class='card' style='width:18rem;'>"
                    + "<img src='images/member.jpg' class='card-img-top' alt='...'>"
                    + "<div class='card-body'>" 
                    + "<h5 class='card-title'>Member</h5>" 
                    + "<p class='card-text'>Total No of Gym Members<br>");
            try
            {
                db.pstmt=db.con.prepareStatement("select count(email) from customer");
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    int mno=db.rst.getInt(1);
                    out.println("<h1>"+mno+"</h1>");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            out.println("</p>"  
                    + "<a href='Member' class='btn btn-primary'>Go Details</a>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>" 
                    + "<div class='col-lg-3 col-md-3 col-sm-12'>" 
                    + "<div class='card' style='width:18rem;'>" 
                    + "<img src='images/trainer.jpg' class='card-img-top' alt='...'>"
                    + "<div class='card-body'>" 
                    + "<h5 class='card-title'>Trainer</h5>" 
                    + "<p class='card-text'>Total No of Gym Trainer<br>");
            try
            {
                db.pstmt=db.con.prepareStatement("select count(email) from trainer");
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    int tno=db.rst.getInt(1);
                    out.println("<h1>"+tno+"</h1>");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            out.println("</p>"  
                    + "<a href='Trainer' class='btn btn-primary'>Go Details</a>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>" 
                    + "<div class='col-lg-3 col-md-3 col-sm-12'>" 
                    + "<div class='card' style='width:18rem;'>" 
                    + "<img src='images/equipment_1.jpg' class='card-img-top' alt='...'>" 
                    + "<div class='card-body'>" 
                    + "<h5 class='card-title'>Equipments</h5>" 
                    + "<p class='card-text'>Total No of Gym Equipments<br>");
            try
            {
                db.pstmt=db.con.prepareStatement("select count(id) from equipment");
                db.rst=db.pstmt.executeQuery();
                if(db.rst.next())
                {
                    int eno=db.rst.getInt(1);
                    out.println("<h1>"+eno+"</h1>");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            out.println("</p>" 
                    + "<a href='Equipment' class='btn btn-primary'>Go Details</a>" 
                    + "</div>" 
                    + "</div>" 
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
