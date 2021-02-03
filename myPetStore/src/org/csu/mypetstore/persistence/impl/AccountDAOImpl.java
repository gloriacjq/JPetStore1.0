package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.persistence.AccountDAO;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOImpl implements AccountDAO {
    private static String getAccountByUsernameSQL = "SELECT " +
            "SIGNON.USERNAME," +
            "ACCOUNT.EMAIL," +
            "ACCOUNT.FIRSTNAME," +
            "ACCOUNT.LASTNAME," +
            "ACCOUNT.STATUS," +
            "ACCOUNT.ADDR1 AS address1," +
            "ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY," +
            "ACCOUNT.STATE," +
            "ACCOUNT.ZIP," +
            "ACCOUNT.COUNTRY," +
            "ACCOUNT.PHONE," +
            "PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption," +
            "PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID " +
            "AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static String getAccountByUsernameAndPasswordSQL = "SELECT " +
            "SIGNON.USERNAME," +
            "ACCOUNT.EMAIL," +
            "ACCOUNT.FIRSTNAME," +
            "ACCOUNT.LASTNAME," +
            "ACCOUNT.STATUS," +
            "ACCOUNT.ADDR1 AS address1," +
            "ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY," +
            "ACCOUNT.STATE," +
            "ACCOUNT.ZIP," +
            "ACCOUNT.COUNTRY," +
            "ACCOUNT.PHONE," +
            "PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption," +
            "PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? " +
            "AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID " +
            "AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static String insertAccountSQL = "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String insertProfileSQL = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID, MYLISTOPT, BANNEROPT) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static String insertSignonSQL = "INSERT INTO SIGNON (PASSWORD, USERNAME) " +
            "VALUES (?, ?)";
    private static String updateAccountSQL = "UPDATE ACCOUNT SET " +
            "EMAIL = ?," +
            "FIRSTNAME = ?," +
            "LASTNAME = ?," +
            "STATUS = ?," +
            "ADDR1 = ?," +
            "ADDR2 = ?," +
            "CITY = ?," +
            "STATE = ?," +
            "ZIP = ?," +
            "COUNTRY = ?," +
            "PHONE = ? " +
            "WHERE USERID = ?";
    private static String updateProfileSQL = "UPDATE PROFILE SET " +
            "LANGPREF = ?," +
            "FAVCATEGORY = ?," +
            "MYLISTOPT = ?," +
            "BANNEROPT = ? " +
            "WHERE USERID = ?";
    private static String updateSignonSQL = "UPDATE SIGNON SET "+
            "PASSWORD = ? " +
            "WHERE USERNAME = ?";


    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameSQL);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(String username, String password) {
        Account account = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameAndPasswordSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void insertAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertAccountSQL);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertProfileSQL);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.setInt(4, account.isListOption()? 1 : 0);
            preparedStatement.setInt(5, account.isBannerOption()? 1 : 0);
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSignonSQL);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateAccountSQL);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateProfileSQL);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setInt(3, account.isListOption()? 1 : 0);
            preparedStatement.setInt(4, account.isBannerOption()? 1 : 0);
            preparedStatement.setString(5, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSignonSQL);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
