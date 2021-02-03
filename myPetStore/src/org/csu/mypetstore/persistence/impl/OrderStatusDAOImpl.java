package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.OrderStatusDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class OrderStatusDAOImpl implements OrderStatusDAO {
    public static final String createNewOrderSQL = "INSERT INTO ORDERSTATUS (ORDERID , TIMESTAMP, STATUS) VALUES (?,?,?)";
    public static final String orderPaidSQL = "UPDATE ORDERSTATUS SET STATUS = ? WHERE ORDERID = ?";
    private static final String insertOrderStatusSQL = "INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS) VALUES (?, ?, ?, ?)";

    @Override
    public void CreateNewOrder(Order order) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(createNewOrderSQL);
            preparedStatement.setInt(1,order.getOrderId());
            Date sqlDate = new Date(order.getOrderDate().getTime());
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setString(3,"Not Paid");

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OrderPaid(Order order) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(orderPaidSQL);
            preparedStatement.setInt(2,order.getOrderId());
            preparedStatement.setString(1,"Paid");

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrderStatus(Order order) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrderStatusSQL);
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getLineItems().size());
            preparedStatement.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
            preparedStatement.setString(4, order.getStatus());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
