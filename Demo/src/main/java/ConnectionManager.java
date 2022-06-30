import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    //this is a single
    public static ConnectionManager connectionManager;

    public static Connection connection;

    // private constructor
    private ConnectionManager(){


    }
//private get method to initialize the connectionmanager instance
    private ConnectionManager getConnectionManager(){

     if (connectionManager == null){
         connectionManager = new ConnectionManager();
         //connection = connect();
     }

     return connectionManager;
    }

    private Connection getConnection(){

        if (connection == null){

            connection = connect();
        }

        return connection;
    }

public static Connection connect(){
try {

    //this creates a properties list with no values
    Properties props = new Properties();

//here we are using the File Reader to read the contents of our jdbc.properties file
    FileReader fileReader = new FileReader("src/main/resources/jdbc.properties");


    //by loading the contents of the file into the properties list
    //we can now access the values at the keys that we have set on the properties list.
    props.load(fileReader);


    //the database URL is an address pointing to the database to be used
    //also known as the jdbc string. the format of this URL varies between
    //database verdors or DBMS
    //for Postgres the URL we are creating consists of
    // jdbc:postgresql://hostname//port/databaseName


    StringBuilder sb = new StringBuilder();
    sb.append("jdbc:postgresql://");
    sb.append(props.get("hostname"));
    sb.append(":");
    sb.append(props.get("port"));
    sb.append("/");
    sb.append(props.get("database"));


    //in order for us to use the string we have created we have to call the
    // .toString method on the sb
    String connectionURL = sb.toString();


    String user = String.valueOf(props.get("user"));
    String password = String.valueOf(props.get("password"));

    connection = DriverManager.getConnection(connectionURL,user,password);

    System.out.println(connectionURL.toString());
    System.out.println(connection.toString());






}catch (IOException | SQLException e){
    System.out.println(e.getMessage());
}

        return connection;
}










}
