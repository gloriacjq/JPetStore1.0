package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewAccountServlet extends HttpServlet {
    private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    private Account account;
    private AccountService accountService;
    private Cart cart;
    private CartService cartService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(!request.getParameter("password").equals(request.getParameter("repeatedPassword"))) {
            String pwdMessage = "password validation failed, please enter again";
            session.setAttribute("pwdMessage",pwdMessage);
            request.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(request, response);
        }else{
            session.setAttribute("pwdMessage", null);
            account = new Account();
            account.setUsername(request.getParameter("username"));
            account.setPassword(request.getParameter("password"));
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
            accountService.insertAccount(account);
            cartService = new CartService();
            cartService.createCart(account.getUsername());
            cartService.mergeCart((Cart)session.getAttribute("cart"),cartService.getCart(account.getUsername()));

            session.setAttribute("account", account);
            session.setAttribute("cart", cartService.getCart(account.getUsername()));
            session.setAttribute("signInMessage", " Welcome, "+ account.getUsername());
            request.getRequestDispatcher(MAIN).forward(request, response);
        }
    }
}
