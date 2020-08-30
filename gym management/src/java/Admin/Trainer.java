package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Trainer extends HttpServlet {

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
            out.println("<link href='https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css'>");
            out.println("<link href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css'>");
            out.println("<link href='https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css'>");
            out.println("<title>Trainer</title>");            
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
                    + "</div>" 
                    + "<div class='row mt-2'>" 
                    + "<div class='col-lg-3 col-md-3 col-sm-12'><h2>Trainer Page</h2></div>" 
                    + "<div class='col-lg-8 col-md-8 col-sm-12'><marquee>");
            if(email1!=null)
            {
                out.println(email1);
            }
            out.println("</marquee></div>" 
                    + "<div class='col-lg-1 col-md-1 col-sm-12'>" 
                    + "<a href='Logout1' class='btn btn-outline-warning btn-sm'>LOGOUT</a>" 
                    + "</div>" 
                    + "</div>" 
                    + "<div class='row mt-2'>" 
                    + "<div class='col-lg-3 col-md-3 col-sm-12'>" 
                    + "<ul class='nav nav-pills flex-column'>" 
                    + "<li class=nav-item'>" 
                    + "<a class='nav-link' href='Dashboard'>Home</a>" 
                    + "</li>" 
                    + "<li class='nav-item'>" 
                    + "<a class='nav-link' href='Member'>Members</a>" 
                    + "</li>" 
                    + "<li class='nav-item'>" 
                    + "<div class='nav-link active'>Trainers</div>" 
                    + "</li>" 
                    + "<li class='nav-item'>" 
                    + "<a class='nav-link' href='Equipment'>Equipments</a>" 
                    + "</li>" 
                    + "</ul>"
                    + "</div>" 
                    + "<div class='col-lg-8 col-md-8 col-sm-12'>"
                    + "<table class='table table-borderless'>"
                    + "<tr><th colspan='8' class='text-center'><h2>Trainer List</h2></th>"
                    + "<th class='text-center' colspan='2'>"
                    + "<button class='btn btn-outline-primary' data-target='#addtrainer' data-toggle='modal'>"
                    + "Add Trainer</button></th></tr>"
                    + "</table>"
                    + "<table class='table table-striped table-bordered' style='width:100%' id='trainer'>"
                    + "<thead>"
                    + "<tr class='bg-dark text-white'><th>Name</th><th>Gender</th><th>DOB</th><th>Aadhar</th>"
                    + "<th>Mobile</th><th>Email</th><th>Exp</th><th>Speciality</th><th>Action</th></tr>"
                    + "</thead><tbody>");
            try
            {
                db.pstmt=db.con.prepareStatement("select * from trainer");
                db.rst=db.pstmt.executeQuery();
                while(db.rst.next())
                {
                    String id=db.rst.getString(1);
                    String name=db.rst.getString(2);
                    String adhar=db.rst.getString(3);
                    String email=db.rst.getString(4);
                    String mob=db.rst.getString(5);
                    String gen=db.rst.getString(6);
                    String dob=db.rst.getString(7);
                    String exp=db.rst.getString(8);
                    String spec=db.rst.getString(9);
                    out.println("<tr><td>"+name+"</td><td>"+gen+"</td><td>"+dob+"</td><td>"+adhar+"</td>"
                            + "<td>"+mob+"</td><td>"+email+"</td><td>"+exp+"</td><td>"+spec+"</td>"
                            + "<td><div class='btn-group'>" 
                            + "<button type='button' class='btn btn-dark btn-sm'>Action</button>" 
                            + "<button type='button' class='btn btn-dark dropdown-toggle "
                            + "btn-sm dropdown-toggle-split' data-toggle='dropdown'>" 
                            + "<span class='sr-only'>Toggle Dropdown</span>" 
                            + "</button>" 
                            + "<div class='dropdown-menu'>" 
                            + "<a href='#' class='dropdown-item text-primary edittrainer'>Update</a>" 
                            + "<div class='dropdown-divider'></div>" 
                            + "<a href='DeleteTrainer?id="+id+"' class='dropdown-item text-danger'>Delete</a>" 
                            + "</div>" 
                            + "</div></td></tr>");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            out.println("</tbody></table>"
                    + "</div>" 
                    + "<div class='col-lg-1 col-md-1 col-sm-12'></div>" 
                    + "</div>" 
                    + "</div>" 
                    + "<div class='modal' tabindex='-1' role='dialog' id='addtrainer'>" 
                    + "<div class='modal-dialog' role='document'>" 
                    + "<div class='modal-content'>" 
                    + "<div class='modal-header'>" 
                    + "<h5 class='modal-title'>Add Trainer</h5>" 
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>&times;</button>"     
                    + "</div>" 
                    + "<div class='modal-body'>" 
                    + "<form action='AddTrainer' method='post'>"
                    + "<div class='form-group'>"
                    + "<label for='name'>Name</label>"
                    + "<input type='text' name='name' id='name' class='form-control' required>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='email'>Email</label>"
                    + "<input type='email' name='email' id='email' class='form-control' required>" 
                    + "</div>"
                    + "<div class='form-group'>" 
                    + "<label for='mob'>Mobile</label>" 
                    + "<input type='number' name='mob' id='mob' class='form-control' required>" 
                    + "</div>"
                    + "<div clas='form-group'>"
                    + "<label class='mr-5'>Gender</label>"
                    + "<div class='form-check form-check-inline'>"
                    + "<input type='radio' name='gen' id='male' value='Male' class='form-check-input ml-2' checked>"
                    + "<label for='male' class='form-check-label'>Male</label>"
                    + "</div>"
                    + "<div class='form-check form-check-inline ml-5'>"
                    + "<input type='radio' name='gen' id='female' value='Female' class='form-check-input ml-2'>"
                    + "<label for='female' class='form-check-label'>Female</label>"
                    + "</div>"
                    + "</div>"
                    + "<div class='form-group'>" 
                    + "<label for='date'>Date of Birth</label>" 
                    + "<input type='date' name='dob' id='date' class='form-control' required>" 
                    + "</div>"
                    + "<div class='form-group'>" 
                    + "<label for='adhar'>Aadhar Number</label>" 
                    + "<input type='number' class='form-control' name='adhar' id='adhar' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='exp'>Experience (In Years)</label>" 
                    + "<input type='number' class='form-control' name='exp' id='exp' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='spec'>Select Speciality</label>" 
                    + "<select name='spec' id='spec' class='form-control'>"
                    + "<option value='Strength'>Strength</option>"
                    + "<option value='Yoga'>Yoga</option>"
                    + "<option value='Arobics'>Arobics</option>"
                    + "<option value='Cardio'>Cardio</option></select>" 
                    + "</div>"
                    + "<div class='form-group text-center'>" 
                    + "<button type='submit' class='btn btn-outline-primary btn-block'>SUBMIT</button>" 
                    + "</div>" 
                    + "</form>" 
                    + "</div>" 
                    + "<div class='modal-footer'>"
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>CLOSE</button>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>" 
                    + "</div>"
                    + "<div class='modal' tabindex='-1' role='dialog' id='edittrainer'>" 
                    + "<div class='modal-dialog' role='document'>" 
                    + "<div class='modal-content'>" 
                    + "<div class='modal-header'>" 
                    + "<h5 class='modal-title'>Edit Trainer</h5>" 
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>&times;</button>"     
                    + "</div>"
                    + "<div class='modal-body'>" 
                    + "<form action='EditTrainer' method='post'>" 
                    + "<div class='form-group'>" 
                    + "<label for='name1'>Name</label>" 
                    + "<input type='text' class='form-control' name='name' id='name1' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='email1'>Email</label>" 
                    + "<input type='email' class='form-control' name='email' id='email1' readonly>" 
                    + "</div>"
                    + "<div class='form-group'>" 
                    + "<label for='mob1'>Mobile</label>" 
                    + "<input type='number' class='form-control' name='mob' id='mob1' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='gender'>Gender</label>" 
                    + "<select name='gen' id='gender' class='form-control'>"
                    + "<option value='Male'>Male</option>"
                    + "<option value='Female'>Female</option>"
                    + "</select>" 
                    + "</div>"
                    + "<div class='form-group'>" 
                    + "<label for='date1'>Date of Birth</label>" 
                    + "<input type='date' name='dob' id='date1' class='form-control' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='adhar1'>Aadhar No.</label>" 
                    + "<input type='number' class='form-control' name='adhar' id='adhar1' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='exp1'>Experience (In Years)</label>" 
                    + "<input type='number' class='form-control' name='exp' id='exp1' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='spec1'>Select Speciality</label>" 
                    + "<select name='spec' id='spec1' class='form-control'>"
                    + "<option value='Strength'>Strength</option>"
                    + "<option value='Yoga'>Yoga</option>"
                    + "<option value='Arobics'>Arobics</option>"
                    + "<option value='Cardio'>Cardio</option>"
                    + "</select>" 
                    + "</div>"
                    + "<div class='form-group text-center'>" 
                    + "<button type='submit' class='btn btn-outline-primary btn-block'>UPDATE</button>" 
                    + "</div>" 
                    + "</form>" 
                    + "</div>" 
                    + "<div class='modal-footer'>"
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>CLOSE</button>" 
                    + "</div>"
                    + "</div>" 
                    + "</div>" 
                    + "</div>");
            out.println("<script  src='https://code.jquery.com/jquery-3.4.1.min.js'></script>"
                    + "<script src='https://code.jquery.com/jquery-3.4.1.slim.min.js' integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n' crossorigin='anonymous'></script>"              
                    + "<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js' integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo' crossorigin='anonymous'></script>"
                    + "<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js' integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6' crossorigin='anonymous'></script>"
                    + "<script>"
                    + "$(document).ready(function(){"
                    + "$('.edittrainer').click(function(){"
                    +"$('#edittrainer').modal('show');"
                    + "$tr=$(this).closest('tr');"
                    + "var data=$tr.children('td').map(function(){"
                    + "return $(this).text();"
                    + "}).get();"
                    + "$('#name1').val(data[0]);"
                    + "$('#gender').val(data[1]);"
                    + "$('#date1').val(data[2]);"
                    + "$('#adhar1').val(data[3]);"
                    + "$('#mob1').val(data[4]);"
                    + "$('#email1').val(data[5]);"
                    + "$('#exp1').val(data[6]);"
                    + "$('#spec1').val(data[7]);"
                    + "});"
                    + "});"
                    + "</script>" 
                    + "<script src='https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js'></script>"
                    + "<script src='https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js'></script>"
                    + "<script>"
                    + "$(document).ready(function(){"
                    + "$('#trainer').DataTable();"
                    + "});"
                    + "</script>"
                    + "</body>" 
                    + "</html>");
        }
    }
}
