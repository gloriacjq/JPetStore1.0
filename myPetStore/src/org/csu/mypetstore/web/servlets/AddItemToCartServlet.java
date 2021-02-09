package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    private String workingItemId;
    private Cart cart;

    private CatalogService catalogService;
    private CartService cartService;
    private LogService logService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
            if(session.getAttribute("account") != null){
                cartService = new CartService();
                cartService.updateItem(cart, workingItemId, cart.getCartItem(workingItemId).getQuantity());
            }
        } else {
            catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
            if(session.getAttribute("account") != null){
                cartService = new CartService();
                cartService.addItem(cart, cart.getCartItem(item.getItemId()));
            }
        }

        if(session.getAttribute("account") != null){
            logService = new LogService();
            logService.insertLog(((Account)session.getAttribute("account")).getUsername(), "Add Item "+ workingItemId + " to cart");
        }

        session.setAttribute("cart", cart);
        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }
}
