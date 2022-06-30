import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Connection connections = ConnectionManager.connect();
        try {
            //database is not empty.
            System.out.println(connections.getClientInfo().isEmpty());
            //returns the schema from DBeaver (in our case public)
            System.out.println(connections.getSchema());


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
