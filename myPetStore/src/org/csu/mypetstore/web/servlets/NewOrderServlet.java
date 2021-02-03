package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class NewOrderServlet extends HttpServlet {
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";

    private Account account;
    private Order order;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        account = (Account) session.getAttribute("account");
        order = (Order) session.getAttribute("order");
        order.setUsername(account.getUsername());
        order.setOrderDate(new Date());
        if(request.getParameter("shipToFirstName") != null) {
            order.setShipToFirstName(request.getParameter("shipToFirstName"));
            order.setShipToLastName(request.getParameter("shipToLastName"));
            order.setShipAddress1(request.getParameter("shipAddress1"));
            order.setShipAddress2(request.getParameter("shipAddress2"));
            order.setShipCity(request.getParameter("shipCity"));
            order.setShipState(request.getParameter("shipState"));
            order.setShipZip(request.getParameter("shipZip"));
            order.setShipCountry(request.getParameter("shipCountry"));
        }else{
            order.setShipToFirstName(order.getBillToFirstName());
            order.setShipToLastName(order.getBillToLastName());
            order.setShipAddress1(order.getBillAddress1());
            order.setShipAddress2(order.getBillAddress2());
            order.setShipCity(order.getBillCity());
            order.setShipState(order.getBillState());
            order.setShipZip(order.getBillZip());
            order.setShipCountry(order.getBillCountry());
        }

        session.setAttribute("order", order);

        request.getRequestDispatcher(CONFIRM_ORDER).forward(request, response);
    }
}
