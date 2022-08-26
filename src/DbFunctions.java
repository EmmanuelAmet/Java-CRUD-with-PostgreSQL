import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions {
    public Connection connectToDb(String dbName, String username, String password){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:7000/" + dbName, username, password);
            if(conn != null){
                System.out.println("DB is up and running.");
            }else{
                System.out.println("Failed to connect to DB.");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public void createTable( Connection conn, String table_name){
        Statement statement;
        try {
            String query = "CREATE TABLE " + table_name + "(empid BIGSERIAL NOT NULL PRIMARY KEY, name VARCHAR(200) NOT NULL, address VARCHAR(200));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void insertData(Connection conn, String table_name, String name, String address){
        Statement statement;
        try {
            String query = String.format("INSERT INTO %s(name, address) VALUES('%s', '%s');", table_name, name, address);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data inserted");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readData(Connection conn, String table_name){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("SELECT * FROM %s", table_name);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("empid") + " ");
                System.out.print(resultSet.getString("name") + " ");
                System.out.println(resultSet.getString("address"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateName(Connection conn, String table_name, String old_name, String new_name){
        Statement statement;
        try {
            String query = String.format("UPDATE %s SET name = '%s' WHERE name = '%s';", table_name, new_name, old_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Updated");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchByName(Connection conn, String table_name, String name){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("SELECT * FROM %s WHERE name = '%s';", table_name, name);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("empid") + " ");
                System.out.print(resultSet.getString("name") + " ");
                System.out.println(resultSet.getString("address") + " ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchById(Connection conn, String table_name, int id){
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("SELECT * FROM %s WHERE empid = %s;", table_name, id);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("empid") + " ");
                System.out.print(resultSet.getString("name") + " ");
                System.out.println(resultSet.getString("address") + " ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteByName(Connection conn, String tablle_name, String name){
        Statement statement;
        try {
            String query = String.format("DELETE FROM %s WHERE name = '%s';", tablle_name, name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data deleted");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteById(Connection conn, String tablle_name, int empid){
        Statement statement;
        try {
            String query = String.format("DELETE FROM %s WHERE empid = %s;", tablle_name, empid);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data deleted");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteTable(Connection conn, String table_name){
        Statement statement;
        try {
            String query = String.format("DROP TABLE %s;", table_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table deleted");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
