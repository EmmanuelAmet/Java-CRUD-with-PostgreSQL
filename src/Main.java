import java.sql.Connection;

public class Main {
    public static void main(String[] args)
    {
        String table_name = "<table_name>";
        DbFunctions dbFunctions = new DbFunctions();
        Connection conn = dbFunctions.connectToDb("<db_name>", "<db_user_name>", "<password>");

        //Create Table
        dbFunctions.createTable(conn, table_name);

        //Insert Data
        dbFunctions.insertData(conn, table_name, "Grace", "New York - US");

        //Read Data
        dbFunctions.readData(conn, table_name);

        //Update Data
        dbFunctions.updateName(conn, table_name, "Manuel", "Manuel Amet");
        dbFunctions.readData(conn, table_name);

        //Search Data - By Name & Id
        dbFunctions.searchByName(conn, table_name, "Grace");

        dbFunctions.searchById(conn, table_name, 3);

        //Delete Data - By Name & Id
        dbFunctions.deleteByName(conn, table_name, "Grace");
        dbFunctions.readData(conn, table_name);

        dbFunctions.deleteById(conn, table_name, 1);
        dbFunctions.readData(conn, table_name);

        //Delete Table
        dbFunctions.deleteTable(conn, table_name);
    }
}