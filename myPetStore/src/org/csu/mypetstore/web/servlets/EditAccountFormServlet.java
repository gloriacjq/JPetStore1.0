package org.csu.mypetstore.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditAccountFormServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT_FORM = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    private static final String SIGN_IN_FORM = "/WEB-INF/jsp/account/SignInForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("account") == null){
            request.getRequestDispatcher(SIGN_IN_FORM).forward(request, response);
        }
        request.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(request, response);
    }
}
