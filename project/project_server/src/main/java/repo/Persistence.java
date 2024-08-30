package repo;

import java.sql.*;

public class Persistence {

    private static Persistence instance = new Persistence();

    private Persistence(){
        try{
            Class.forName(DBConnectionInfo.DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Persistence getInstance() {
        return instance;
    }

    public Connection createConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DBConnectionInfo.URL.getValue(),
                    DBConnectionInfo.USER.getValue(),DBConnectionInfo.PASSWORD.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static Connection getConnection(){
        return instance.createConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean executeQuery(String query){
        boolean result = false;
        Connection connection = null;
        Statement statement = null;

        try{
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }

        return result;
    }

    public ResultSet getDataTable(String query){
        Statement statement;
        ResultSet resultSet = null;
        Connection connection = null;
        try{
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(connection);
        }

        return resultSet;
    }
}
