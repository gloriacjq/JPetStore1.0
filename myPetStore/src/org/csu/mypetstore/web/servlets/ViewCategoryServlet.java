package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Category;
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

public class ViewCategoryServlet extends HttpServlet {
    private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";

    private String categoryId;
    private LogService logService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryId = request.getParameter("categoryId");
        CatalogService service = new CatalogService();
        Category category = service.getCategory(categoryId);
        List<Product> productList = service.getProductListByCategory(categoryId);

        HttpSession session = request.getSession();
        if(session.getAttribute("account") != null){
            logService = new LogService();
            logService.insertLog(((Account)session.getAttribute("account")).getUsername(), "View Category " + categoryId);
        }
        session.setAttribute("category" , category);
        session.setAttribute("productList" , productList);

        request.getRequestDispatcher(VIEW_CATEGORY).forward(request,response);
    }
}
