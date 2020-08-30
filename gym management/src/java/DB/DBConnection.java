package DB;
import java.sql.*;
public class DBConnection {
    public Connection con;
    public Statement stmt;
    public PreparedStatement pstmt;
    public ResultSet rst;
    public DBConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gym_management","root","root");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
