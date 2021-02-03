package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.persistence.CategoryDAO;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final String getCategoryListSQL = "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY";
    private static final String getCategorySQL = "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY WHERE CATID = ?";

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<Category>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCategoryListSQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
                categoryList.add(category);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return getCategoryList();
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try {
            Connection connection =DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCategorySQL);
            preparedStatement.setString(1,categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
}
