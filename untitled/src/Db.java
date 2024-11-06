import java.sql.Connection;
import java.sql.DriverManager;
import java.security.spec.ECField;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Db {
    String dbname;
    String user;
    String pass;
    String host;
    public Connection connect_to_db(String dbname,String user,String pass){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if(conn!=null){
                this.dbname=dbname;
                this.user=user;
                this.pass=pass;
                this.host="jdbc:postgresql://localhost:5432/"+this.dbname;
                System.out.println("Connected to database");
            }
            else{
                System.out.println("Failed to connect to database");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return conn;
    }
    public void insert(data Data){
        String Query = "INSERT INTO bank_account"+" (id, money,interest_rate,open_date,first_name,last_name,birth_date,age) "+"VALUES (?,?,?,?,?,?,?,?)";
        System.out.println(this.host+this.user+this.pass);
        try(Connection conn = DriverManager.getConnection(this.host,this.user,this.pass);
            PreparedStatement ps = conn.prepareStatement(Query)){
            ps.setString(1,Data.id);
            ps.setDouble(2,Data.money);
            ps.setDouble(3,Data.rate);
            ps.setDate(4,Data.date);
            ps.setString(5,Data.firstName);
            ps.setString(6,Data.lastName);
            ps.setDate(7,Data.birth_day);
            ps.setInt(8,Data.age);
            int rowInserted = ps.executeUpdate();
            if(rowInserted>0){
                System.out.println("Inserted rows into database");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Cant Save");
        }
    }
}
