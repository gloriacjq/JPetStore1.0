package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private static final String SIGN_IN_FORM = "/WEB-INF/jsp/account/SignInForm.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    private AccountService accountService;
    private CartService cartService;
    private LogService logService;
    private Account account;
    private String username;
    private String password;
    private Cart cart;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        //1 获得用户输入的验证码
        String verifyCode = request.getParameter("verifyCode");
        //2 获得服务器session 存放数据 ,如果没有返回null
        String sessionCacheData = (String) request.getSession().getAttribute("sessionCacheData");
        // *将服务器缓存session数据移除
        request.getSession().removeAttribute("sessionCacheData");
        // ** 判断服务器是否存在
        if(sessionCacheData == null){
            request.setAttribute("msg", "请不要重复提交");
            request.getRequestDispatcher(SIGN_IN_FORM).forward(request, response);
            return;
        }
        //3 比较
        if(verifyCode == ""){
            request.setAttribute("username", username);
            request.setAttribute("msg", "Please enter the verification code");
            request.getRequestDispatcher(SIGN_IN_FORM).forward(request, response);
            return;
        }else if(! sessionCacheData.equalsIgnoreCase(verifyCode)){
            //用户输入错误
            // * 存放request作用域
            request.setAttribute("username", username);
            request.setAttribute("msg", "Wrong verification code, please try again");
            // * 请求转发
            request.getRequestDispatcher(SIGN_IN_FORM).forward(request, response);
            return;
        }



        //用户登录
        accountService = new AccountService();
        account = accountService.getAccount(username, password);

        HttpSession session = request.getSession();

        if(account == null){
            String signInFailedMessage = "Invalid username or password. Please try again";
            request.setAttribute("signInFailedMessage", signInFailedMessage);
            request.setAttribute("username", username);
            request.getRequestDispatcher(SIGN_IN_FORM).forward(request, response);
        }else{
            cartService = new CartService();
            if(session.getAttribute("cart") == null){
                cart = cartService.getCart(username);
            }else {
                cart = (Cart)session.getAttribute("cart");
                cart = cartService.mergeCart(cartService.getCart(username), cart);
            }
            logService = new LogService();
            logService.insertLog(username, "Log in");

            String signInMessage = "Welcome, " + username;
            session.setAttribute("signInMessage", signInMessage);
            session.setAttribute("account", account);
            session.setAttribute("cart", cart);
            //if(account.isBannerOption()) session.setAttribute("bannerName", account.getBannerName());
            //request.getRequestDispatcher(MAIN).forward(request, response);
            response.sendRedirect("/main");
        }

    }
}
