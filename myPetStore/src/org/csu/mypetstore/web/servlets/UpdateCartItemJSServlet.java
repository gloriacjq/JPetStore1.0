package org.csu.mypetstore.web.servlets;

import com.alibaba.fastjson.JSONObject;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@WebServlet(name = "UpdateCartItemJSServlet")
public class UpdateCartItemJSServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private Cart cart;
    private CartService cartService;
    private LogService logService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从对话中，获取购物车
        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        cartService = new CartService();
        logService = new LogService();
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();
           // if(itemId = )
            try {
                int quantity = Integer.parseInt(request.getParameter(itemId));
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                    if(session.getAttribute("account") != null){
                        cartService.deleteItem(cart, itemId);
                    }
                }else{
                    if(session.getAttribute("account") != null){
                        cartService.updateItem(cart, itemId, quantity);
                    }
                }
            } catch (Exception e) {
                session.setAttribute("message", "The Quantities of Item must be Integer!");
                request.getRequestDispatcher(ERROR).forward(request, response);
            }
        }
        if(session.getAttribute("account") != null){
            logService.insertLog(((Account)session.getAttribute("account")).getUsername(), "Update cart");
        }
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("quantity",quantity);
//        jsonObject.put("totalCost",cart.getCartItem(itemId).getTotal();
        jsonObject.put("subTotal",cart.getSubTotal());
        System.out.println(jsonObject);
        response.getWriter().print(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
