package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewProductServlet extends HttpServlet {
    private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";

    private String productId;
    private LogService logService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId = request.getParameter("productId");
        CatalogService service = new CatalogService();
        Product product = service.getProduct(productId);
        List<Item> itemList = service.getItemListByProduct(productId);

        HttpSession session = request.getSession();
        if(session.getAttribute("account") != null){
            logService = new LogService();
            logService.insertLog(((Account)session.getAttribute("account")).getUsername(), "View product " + product.getCategoryId() + "  " + productId);
        }
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);

        request.getRequestDispatcher(VIEW_PRODUCT).forward(request,response);
    }
}
