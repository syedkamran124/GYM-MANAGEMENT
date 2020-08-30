package Admin;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Equipment extends HttpServlet {

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
            out.println("<title>Equipment</title>");            
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
                    + "<div class='col-lg-3 col-md-3 col-sm-12'><h2>Equipment Page</h2></div>"
                    + "<div class='col-lg-8 col-md-8 col-sm-12'><marquee>");
            if(email1!=null)
            {
                out.println(email1);
            }
            out.println("</marquee></div>" 
                    +"<div class='col-lg-1 col-md-1 col-sm-12'>" 
                    + "<a href='Logout1' class='btn btn-outline-warning btn-sm'>LOGOUT</a>"
                    + "</div>" 
                    + "</div>" 
                    + "<div class='row mt-2'>" 
                    + "<div class='col-lg-3 col-md-3 col-sm-12'>" 
                    + "<ul class='nav nav-pills flex-column'>" 
                    + "<li class='nav-item'>" 
                    + "<a class='nav-link' href='Dashboard'>Home</a>" 
                    + "</li>" 
                    + "<li class='nav-item'>" 
                    + "<a class='nav-link' href='Member'>Members</a>" 
                    + "</li>" 
                    + "<li class='nav-item'>" 
                    + "<a class='nav-link' href='Trainer'>Trainers</a>" 
                    + "</li>" 
                    + "<li class='nav-item'>" 
                    + "<div class='nav-link active'>Equipments</div>" 
                    + "</li>" 
                    + "</ul>" 
                    + "</div>" 
                    + "<div class='col-lg-8 col-md-8 col-sm-12'>"
                    + "<table class='table table-borderless'>"
                    + "<tr><th colspan='6' class='text-center'><h2>Equipment List</h2></th>"
                    + "<th class='text-center' colspan='2'>"
                    + "<button class='btn btn-outline-primary' data-target='#addequipment'"
                    + " data-toggle='modal'>Add Equipment</button></th></tr>"
                    + "</table>"
                    + "<table class='table table-striped table-bordered' style='width:100%' id='equipment'>"
                    + "<thead>"
                    + "<tr class='bg-dark text-white'><th>Id</th><th>Name</th><th>Company</th>"
                    + "<th>Quantity</th><th>Price</th><th>Exercise</th><th>Action</th></tr>"
                    + "</thead><tbody>");
            try
            {       
                db.pstmt=db.con.prepareStatement("select * from equipment");
                db.rst=db.pstmt.executeQuery();
                while(db.rst.next())
                {
                    String id=db.rst.getString(1);
                    String name=db.rst.getString(2);
                    String exercise=db.rst.getString(3);
                    String qty=db.rst.getString(4);
                    String price=db.rst.getString(5);
                    String company=db.rst.getString(6);
                    out.println("<tr><td>"+id+"</td><td>"+name+"</td><td>"+company+"</td><td>"+qty+"</td>"
                            + "<td>"+price+"</td><td>"+exercise+"</td><td>" 
                            + "<a href='#' class='btn btn-outline-primary btn-sm editequipment'>Update</a>"
                            + "<a href='DeleteEquipment?id="+id+"' class='btn btn-outline-danger btn-sm'>"
                            + "Delete</a></td></tr>");
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
                    + "<div class='modal' tabindex='-1' role='dialog' id='addequipment'>" 
                    + "<div class='modal-dialog' role='document'>" 
                    + "<div class='modal-content'>" 
                    + "<div class='modal-header'>" 
                    + "<h5 class='modal-title'>Add Equipment</h5>" 
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>&times;</button>"     
                    + "</div>" 
                    + "<div class='modal-body'>" 
                    + "<form action='AddEquipment' method='post'>" 
                    + "<div class='form-group'>" 
                    + "<label for='name'>Name</label>" 
                    + "<input type='text' class='form-control' name='name' id='name' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='company'>Brand Company</label>" 
                    + "<select name='company' id='company' class='form-control'>"
                    + "<option value='Sunsai'>Sunsai</option>"
                    + "<option value='Nortus'>Nortus Gym</option>"
                    + "<option value='Syndicate'>Syndicate</option>"
                    + "<option value='Fitness'>Fitness</option>"
                    + "<option value='Steel Flex'>Steel Flex</option>"
                    + "<option value='Life Fitness'>Life Fitness</option>"
                    + "<option value='Precor'>Precor</option>"
                    + "<option value='Body-Solid'>Body-Solid</option>"
                    + "<option value='Paramount'>Paramount</option>"
                    + "<option value='Star Trac'>Star Trac</option>"
                    + "</select>"  
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='qty'>Quantity</label>" 
                    + "<input type='number' class='form-control' name='qty' id='qty' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='price'>Price</label>" 
                    + "<input type='number' class='form-control' name='price' id='price' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='exercise'>Used In Excercise</label>" 
                    + "<select name='exercise' id='exercise' class='form-control'>"
                    + "<option value='Chest'>Chest</option>"
                    + "<option value='Back'>Back</option>"
                    + "<option value='Shoulder'>Shoulder</option>"
                    + "<option value='Biceps'>Biceps</option>"
                    + "<option value='Triceps'>Triceps</option>"
                    + "<option value='Lower'>Lower</option>"
                    + "</select>" 
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
                    + "<div class='modal' tabindex='-1' role='dialog' id='editequipment'>" 
                    + "<div class='modal-dialog' role='document'>" 
                    + "<div class='modal-content'>" 
                    + "<div class='modal-header'>" 
                    + "<h5 class='modal-title'>Edit Equipment</h5>" 
                    + "<button class='btn btn-light' class='close' data-dismiss='modal'>&times;</button>"     
                    + "</div>"
                    + "<div class='modal-body'>" 
                    + "<form action='EditEquipment' method='post'>"
                    + "<input type='hidden' name='id' id='id'>" 
                    + "<div class='form-group'>" 
                    + "<label for='name1'>Name</label>" 
                    + "<input type='text' class='form-control' name='name' id='name1' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='company1'>Brand Company</label>" 
                    + "<select name='company' id='company1' class='form-control'>"
                    + "<option value='Sunsai'>Sunsai</option>"
                    + "<option value='Nortus'>Nortus Gym</option>"
                    + "<option value='Syndicate'>Syndicate</option>"
                    + "<option value='Fitness'>Fitness</option>"
                    + "<option value='Steel Flex'>Steel Flex</option>"
                    + "<option value='Life Fitness'>Life Fitness</option>"
                    + "<option value='Precor'>Precor</option>"
                    + "<option value='Body-Solid'>Body-Solid</option>"
                    + "<option value='Paramount'>Paramount</option>"
                    + "<option value='Star Trac'>Star Trac</option>"
                    + "</select>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='qty1'>Quantity</label>" 
                    + "<input type='number' class='form-control' name='qty' id='qty1' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='price1'>Price</label>" 
                    + "<input type='number' class='form-control' name='price' id='price1' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='exercise1'>Used In Excercise</label>" 
                    + "<select name='exercise' id='exercise1' class='form-control'>"
                    + "<option value='Chest'>Chest</option>"
                    + "<option value='Back'>Back</option>"
                    + "<option value='Shoulder'>Shoulder</option>"
                    + "<option value='Biceps'>Biceps</option>"
                    + "<option value='Triceps'>Triceps</option>"
                    + "<option value='Lower'>Lower</option>"
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
                    + "$('.editequipment').click(function(){"
                    +"$('#editequipment').modal('show');"
                    + "$tr=$(this).closest('tr');"
                    + "var data=$tr.children('td').map(function(){"
                    + "return $(this).text();"
                    + "}).get();"
                    + "$('#id').val(data[0]);"
                    + "$('#name1').val(data[1]);"
                    + "$('#company1').val(data[2]);"
                    + "$('#qty1').val(data[3]);"
                    + "$('#price1').val(data[4]);"
                    + "$('#exercise1').val(data[5]);"
                    + "});"
                    + "});"
                    + "</script>"
                    + "<script src='https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js'></script>"
                    + "<script src='https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js'></script>"
                    + "<script>"
                    + "$(document).ready(function(){"
                    + "$('#equipment').DataTable();"
                    + "});"
                    + "</script>"
                    + "</body>" 
                    + "</html>");   
        }
    }
}
