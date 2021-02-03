package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.persistence.ItemDAO;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {
    private static final String getItemListByProductSQL = "SELECT " +
            "I.ITEMID,LISTPRICE,UNITCOST," +
            "SUPPLIER AS supplierId," +
            "I.PRODUCTID AS \"product.productId\"," +
            "NAME AS \"product.name\"," +
            "DESCN AS \"product.description\"," +
            "CATEGORY AS \"product.categoryId\"," +
            "STATUS," +
            "ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 " +
            "FROM ITEM I, PRODUCT P " +
            "WHERE P.PRODUCTID = I.PRODUCTID " +
            "AND I.PRODUCTID = ?";
    private static final String getItemSQL = "SELECT " +
            "I.ITEMID,LISTPRICE,UNITCOST," +
            "SUPPLIER AS supplierId," +
            "I.PRODUCTID AS \"product.productId\"," +
            "NAME AS \"product.name\"," +
            "DESCN AS \"product.description\"," +
            "CATEGORY AS \"product.categoryId\"," +
            "STATUS," +
            "ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5, " +
            "QTY AS quantity " +
            "FROM ITEM I, INVENTORY V, PRODUCT P " +
            "WHERE P.PRODUCTID = I.PRODUCTID " +
            "AND I.ITEMID = V.ITEMID AND I.ITEMID = ?";
    private static final String getInventoryQuantitySQL = "SELECT QTY AS quantity FROM INVENTORY WHERE ITEMID = ?";
    private static final String updateInventoryQuantitySQL = "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";

    @Override
    public void updateInventoryQuantity(Map<String, Object> param){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateInventoryQuantitySQL);
            String itemId = param.keySet().iterator().next();
            Integer increment = (Integer) param.get(itemId);
            preparedStatement.setInt(1, increment.intValue());
            preparedStatement.setString(2, itemId);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int result = -1;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getInventoryQuantitySQL);
            preparedStatement.setString(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<Item>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getItemListByProductSQL);
            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                itemList.add(item);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getItemSQL);
            preparedStatement.setString(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                item.setQuantity(resultSet.getInt(15));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }
}
