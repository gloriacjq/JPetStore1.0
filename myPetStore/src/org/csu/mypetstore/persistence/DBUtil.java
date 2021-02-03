package org.csu.mypetstore.persistence;

import java.net.ConnectException;
import java.sql.*;

public class DBUtil {
    private final static String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private final static String CONNECTION_STRING = "jdbc:mysql://localhost:3306/mypetstore?userUnicode=true&characterEncoding=utf8&serverTimezone=UTC&userSSL=false";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "gloria123";

    public static Connection getConnection() throws Exception {
        Connection connection = null;
        try {
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (Exception e){
            throw e;
        }
        return connection;
    }

    public static void closeConnection(Connection connection) throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public  static void closeStatement(Statement statement) throws Exception {
        if (statement != null) {
            statement.close();
        }
    }

    public  static void closePreparedStatement(PreparedStatement preparedStatement) throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public  static void closeResultSet(ResultSet resultSet) throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    /*test the db connection
	public static void main(String[] args) throws Exception {
		Connection conn = DBUtil.getConnection();
		System.out.println(conn);
	}*/
}
