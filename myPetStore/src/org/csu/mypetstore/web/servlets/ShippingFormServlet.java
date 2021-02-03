package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShippingFormServlet extends HttpServlet {
    private static final String SHIPPING_FORM = "/WEB-INF/jsp/order/ShippingForm.jsp";

    private Order order;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        order.setCardType(request.getParameter("cardType"));
        order.setCreditCard(request.getParameter("creditCard"));
        order.setExpiryDate(request.getParameter("expiryDate"));
        order.setBillToFirstName(request.getParameter("billToFirstName"));
        order.setBillToLastName(request.getParameter("billToLastName"));
        order.setBillAddress1(request.getParameter("billAddress1"));
        order.setBillAddress2(request.getParameter("billAddress2"));
        order.setBillCity(request.getParameter("billCity"));
        order.setBillZip(request.getParameter("billZip"));
        order.setBillState(request.getParameter("billState"));
        order.setBillCountry(request.getParameter("billCountry"));
        session.setAttribute("order", order);
        if(request.getParameter("shippingAddressRequired") != null) {
            request.getRequestDispatcher(SHIPPING_FORM).forward(request, response);
        } else {
            request.getRequestDispatcher("/newOrder").forward(request, response);
        }
    }
}
