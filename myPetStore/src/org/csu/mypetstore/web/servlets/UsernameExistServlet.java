package org.csu.mypetstore.web.servlets;

import com.alibaba.fastjson.JSON;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.util.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        AccountService accountService = new AccountService();
        Account result = accountService.getAccount(username);

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Result result1 = new Result();


        if(result == null && !username.isEmpty()){
            result1.setCode(0);
            result1.setMsg("Not Exist");
        } else {
            result1.setCode(1);
            result1.setMsg("Exist");
        }

        String str = JSON.toJSONString(result1);
        out.print(str);

        out.flush();
        out.close();
    }
}
