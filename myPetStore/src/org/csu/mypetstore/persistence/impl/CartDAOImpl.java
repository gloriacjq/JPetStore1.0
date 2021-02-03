package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartDAOImpl implements CartDAO {
    private static final String getCartByUsernameSQL = "SELECT CARTID FROM CART WHERE USERID = ? ";
    private static final String insertCartSQL = "INSERT INTO CART (CARTID, USERID) VALUES (DEFAULT, ?)";

    @Override
    public Cart getCartByUsername(String username) {
        Cart cart = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCartByUsernameSQL);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cart = new Cart();
                cart.setCartId(resultSet.getInt(1));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public void insertCart(Cart cart) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertCartSQL);
            preparedStatement.setString(1, cart.getUsername());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
