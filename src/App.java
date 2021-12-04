import java.sql.*;
public class App {
    public static void main(String[] args){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_rating", "root", "password");
           Statement stmt = con.createStatement();
           System.out.println("Connected");
           String sql = "INSERT INTO Reviews(reviewID, stars) VALUES (20, 4)";
           stmt.executeUpdate(sql);
           con.close();
       }
       catch (Exception e) {
            System.out.println(e);
       }
    }
}
