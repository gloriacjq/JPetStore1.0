package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.LogDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

public class LogDAOImpl implements LogDAO {
    String insertLogSQL = "INSERT INTO LOG (LOGDATE, USERNAME, LOGTEXT) VALUES (?, ?, ?)";

    @Override
    public void insertLog(Date date, String username, String log) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertLogSQL);
            preparedStatement.setTimestamp(1, new Timestamp(date.getTime()));
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, log);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
