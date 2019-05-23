package com.message.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.PrintWriter;
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();

    }


    public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

         String username = request.getParameter("username");

         String password = request.getParameter("password");

         PrintWriter  output = response.getWriter();

         output.println("<html><head><title> hello</title></head>");


         Mysql mss = new Mysql();      //实例化数据库对象

        String result = mss.checkUser(username,password);

    if (result.equals("hasUserNameAndPasswordCorrect")) {

    output.println("<body>user and password correct</body></html>");

          System.out.println("用户名和密码均正确");

    } else if (result.equals("hasUserNameButPasswordInCorrect")) {
    output.println("<body>user correct but password wrong</body></html>");

        System.out.println("用户名正确,密码不正确");

        } else if (result.equals("hasNoUserName")) {
        output.println("<body>no user</body></html>");
            System.out.println("没有此用户");
    }


    output.flush();


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
