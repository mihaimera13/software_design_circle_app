package repo;

import java.sql.*;

public class Persistence {

    protected Connection connection;

    public Persistence(){
        try{
            Class.forName(DBConnectionInfo.DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        connection = null;
        try{
            connection = DriverManager.getConnection(DBConnectionInfo.URL.getValue(),
                    DBConnectionInfo.USER.getValue(),DBConnectionInfo.PASSWORD.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean executeQuery(String query){
        boolean result = false;

        Statement statement = null;

        try{
            this.connect();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }

        return result;
    }

    public ResultSet getDataTable(String query){
        Statement statement;
        ResultSet resultSet = null;

        try{
            this.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }

        return resultSet;
    }
}
