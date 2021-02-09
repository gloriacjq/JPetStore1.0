package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT_FORM = "/WEB-INF/jsp/account/EditAccountForm.jsp";

    private Account account;
    private AccountService accountService;
    private LogService logService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(!request.getParameter("password").equals(request.getParameter("repeatedPassword"))) {
            String pwdMessage = "password validation failed, please enter again";
            session.setAttribute("pwdMessage", pwdMessage);
            request.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(request, response);
        }else{
            session.setAttribute("pwdMessage", null);

            account = (Account)session.getAttribute("account");

            account.setUsername(request.getParameter("username"));
            account.setPassword(request.getParameter("password") != null ? request.getParameter("password") : account.getPassword());
            account.setFirstName(request.getParameter("firstName"));
            account.setLastName(request.getParameter("lastName"));
            account.setEmail(request.getParameter("email"));
            account.setPhone(request.getParameter("phone"));
            account.setAddress1(request.getParameter("address1"));
            account.setAddress2(request.getParameter("address2"));
            account.setCity(request.getParameter("city"));
            account.setState(request.getParameter("state"));
            account.setZip(request.getParameter("zip"));
            account.setCountry(request.getParameter("country"));
            account.setLanguagePreference(request.getParameter("languagePreference"));
            account.setFavouriteCategoryId(request.getParameter("favouriteCategoryId"));
            account.setListOption(request.getParameter("listOption") != null);
            account.setBannerOption(request.getParameter("bannerOption") != null);

            accountService = new AccountService();
            accountService.updateAccount(account);
            logService = new LogService();
            logService.insertLog(account.getUsername(), "Edit account information");

            session.setAttribute("account", account);
            session.setAttribute("signInMessage", "welcome, "+ account.getUsername());
            request.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(request, response);
        }
    }
}
