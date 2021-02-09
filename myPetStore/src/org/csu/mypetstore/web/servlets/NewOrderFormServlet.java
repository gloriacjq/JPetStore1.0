package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet {
    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String SIGN_IN_FORM = "/WEB-INF/jsp/account/SignInForm.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private Account account;
    private Order order;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        account = (Account)session.getAttribute("account");
        cart = (Cart)session.getAttribute("cart");

        if (account == null){
            session.setAttribute("message", "You must sign in before attempting to check out.  Please sign in and try checking out again.");
            request.getRequestDispatcher(SIGN_IN_FORM).forward(request, response);
        }else if(cart != null){
            order = new Order();
            order.orderInit(account, cart);
            session.setAttribute("order", order);
            request.getRequestDispatcher(NEW_ORDER_FORM).forward(request, response);
        }else{
            session.setAttribute("message", "Order fails to be created because the cart is not found.");
            Account account = (Account)session.getAttribute("account");
            request.getRequestDispatcher(ERROR).forward(request, response);
        }
    }
}
