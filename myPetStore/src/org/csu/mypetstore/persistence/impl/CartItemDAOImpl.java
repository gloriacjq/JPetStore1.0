package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.persistence.CartItemDAO;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {
    private static final String getCartItemsByCartIdSQL = "SELECT ITEMID,QUANTITY FROM CARTITEM WHERE CARTID = ?";
    private static final String insertCartItemSQL = "INSERT INTO CARTITEM (CARTID, ITEMID, QUANTITY) VALUES (?, ?, ?)";
    private static final String deleteCartItemSQL = "DELETE FROM CARTITEM WHERE CARTID = ? AND ITEMID = ?";
    private static final String updateCartItemQuantitySQL = "UPDATE CARTITEM SET QUANTITY = ? WHERE CARTID = ? AND ITEMID = ?";
    private static final String clearCartItemsByCartIdSQL = "DELETE FROM CARTITEM WHERE CARTID = ?";

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {
        List<CartItem> cartItemList = new ArrayList<CartItem>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCartItemsByCartIdSQL);
            preparedStatement.setInt(1, cartId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CartItem cartItem = new CartItem();

                cartItem.setItemId(resultSet.getString(1));
                cartItem.setQuantity(resultSet.getInt(2));

                cartItemList.add(cartItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cartItemList;
    }

    @Override
    public void insertCartItem(CartItem cartItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertCartItemSQL);
            preparedStatement.setInt(1, cartItem.getCartId());
            preparedStatement.setString(2, cartItem.getItemId());
            preparedStatement.setInt(3, cartItem.getQuantity());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCartItem(int cartId, String itemId) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteCartItemSQL);
            preparedStatement.setInt(1, cartId);
            preparedStatement.setString(2, itemId);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateCartItemQuantity(int cartId, String itemId, int quantity){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateCartItemQuantitySQL);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, cartId);
            preparedStatement.setString(3, itemId);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void clearCartItemsByCartId(Cart cart) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(clearCartItemsByCartIdSQL);
            preparedStatement.setInt(1, cart.getCartId());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
