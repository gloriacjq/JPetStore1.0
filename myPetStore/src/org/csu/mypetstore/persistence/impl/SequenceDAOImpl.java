package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.SequenceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDAOImpl implements SequenceDAO {
    private static final String getSequenceSQL = "SELECT NAME, NEXTID FROM SEQUENCE WHERE NAME = ?";
    private static final String updateSequenceSQL = "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence seq = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getSequenceSQL);
            preparedStatement.setString(1, sequence.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                seq = new Sequence();
                seq.setName(resultSet.getString(1));
                seq.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return seq;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSequenceSQL);
            preparedStatement.setInt(1, sequence.getNextId());
            preparedStatement.setString(2, sequence.getName());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
