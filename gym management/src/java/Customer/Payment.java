package Customer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Payment extends HttpServlet {

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
            String amount=(String)hs.getAttribute("amount");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>");
            out.println("<title>Payment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>"
                    + "<div class='row'>"
                    + "<div class='col-lg-12 col-md-12 col-sm-12' style='margin-top:20px;'>"
                    + "<img src='images/logo.png'>"
                    + "<p style='font-size:40px; margin-top:10px;'>Gold Gym</p>"
                    + "<form class='border border-danger p-4' action='Payment2' method='post'>"
                    + "<div class='form-group'>"
                    + "<label class='mr-3'>Payment Mode : </label>"
                    + "<div class='form-check form-check-inline'>"
                    + "<input type='radio' id='credit' name='ctype' value='credit'"
                    + " class='form-check-input' checked>" 
                    + "<label for='credit' class='form-check-label mr-2'>Credit Card</label>"
                    + "</div>"
                    + "<div class='form-check form-check-inline'>" 
                    + "<input type='radio' id='debit' name='ctype' value='debit' class='form-check-input'>"
                    + "<label for='debit' class='form-check-label'>Debit Card</label>"
                    + "</div>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='namecard'>Card Name</label>"
                    + "<input type='text' id='namecard' name='namecard' class='form-control' required>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label for='cardno'>Card Number</label>"
                    + "<input type='number' id='cardno' name='cardno' class='form-control' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='cvv'>CVV</label>" 
                    +" <input type='password' maxlength='3' id='cvv' name='cvv' class='form-control' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='exp'>Expiry</label>" 
                    + "<input type='month' id='exp' name='exp' class='form-control' required>" 
                    + "</div>" 
                    + "<div class='form-group'>" 
                    + "<label for='amount'>Amount</label>" 
                    + "<input type='text' id='amount' class='form-control' value='"+amount+"' readonly>"
                    + "</div>" 
                    + "<div class='form-group text-center'>" 
                    + "<button type='submit' name='pay' class='btn btn-primary btn-block'>"
                    + "PAY</button>" 
                    + "</div>"
                    + "</form>"
                    + "<a href='GymForm' class='btn btn-outline-warning btn-lg mt-3'>Back</a>"        
                    + "</div>"
                    + "</div>"
                    + " </div>");
             out.println("<script src='https://code.jquery.com/jquery-3.4.1.slim.min.js' integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n' crossorigin='anonymous'></script>"
                      + "<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js' integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo' crossorigin='anonymous'></script>"
                      + "<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js' integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6' crossorigin='anonymous'></script>");
            out.println("</body>");
            out.println("</html>"); 
        }
    }
}
