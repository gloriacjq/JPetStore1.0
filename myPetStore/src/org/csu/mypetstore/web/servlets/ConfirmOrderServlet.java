package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.LogService;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ConfirmOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";

    private OrderService orderService;
    private CartService cartService;
    private LogService logService;
    private Order order;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        orderService = new OrderService();
        cartService = new CartService();
        logService = new LogService();
        order = (Order)session.getAttribute("order");
        orderService.insertOrder(order);
        cartService.clearCart((Cart)session.getAttribute("cart"));
        logService.insertLog(((Account)session.getAttribute("account")).getUsername(), "Submit order " + order.getOrderId());

        session.setAttribute("cart", null);
        String confirmMessage = "Your order has been submitted! Thanks!";
        request.setAttribute("confirmMessage", confirmMessage);
        request.getRequestDispatcher(VIEW_ORDER).forward(request, response);
    }
}
