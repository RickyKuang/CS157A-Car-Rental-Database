import java.sql.*;
public class App {
    public static void main(String[] args) throws Exception {
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
//           Statement stmt=con.createStatement();
//           System.out.println("INSERT INTO Reviews(reviewID, stars) VALUES (20, 4)");
//           ResultSet rs=stmt.executeQuery("INSERT INTO Reviews(reviewID, stars) VALUES (20, 4)");
//           while(rs.next())
//           {
//               System.out.println(rs.getString(1));
//           }
           Statement stmt = con.createStatement();
           System.out.println("INSERT INTO Reviews(reviewID, stars) VALUES (20, 4)");
           String sql = "INSERT INTO Reviews(reviewID, stars) VALUES (20, 4)";
           stmt.executeUpdate(sql);
           con.close();
       }
       catch (Exception e) {
            System.out.println(e);
       }
    }
}
