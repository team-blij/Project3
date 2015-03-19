/**
 * Created by Elize on 17-3-2015.
 */

import java.sql.*;

public class Database {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void connectToDatabase() throws SQLException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            connection = DriverManager.getConnection(
                    //The adres of the server
                    "jdbc:sqlserver://145.24.222.198:80",
                    //username
                    "0891435",
                    //password
                    "B14sK8"
                    );
            System.out.println("U heeft verbinding");
        }catch(SQLException ex){
            System.out.println("Er kon geen verbinding worden gemaakt.");
        }catch(ClassNotFoundException ex){
                    System.out.println("Class not found...");
                    System.exit(0);
                }

    }//end of connectToDatabase()


    private void createTable() throws SQLException{
        statement = connection.createStatement();

            String sql = "CREATE TABLE Tweets"
                    +   "message    MESSAGE_TEXT(500)   NOT NULL "
                    +   "user_id    VARCHAR(255)        PRIMARY KEY"
                    +   "username   VARCHAR(255)"
                    ;

        statement.executeUpdate(sql);

    }//end of createTable()

    private void getTable() throws SQLException{
        statement = connection.createStatement();
            String sql = "SELECT FROM Tweets * "
                    ;
        statement.executeUpdate(sql);




    }//end of getTable()

    public void insertIntoTable(String tableName, String message, Long user_id, String username) throws SQLException{
        statement = connection.createStatement();
            String sql =    "INSERT INTO "
                        +   tableName
                        +   "VALUES ("
                        +   message
                        +   ", "
                        +   user_id
                        +   ", "
                        +   username
                        +   ")"
                    ;
        statement.executeUpdate(sql);
    }// end of insertIntoTable()


}// end of class Database
