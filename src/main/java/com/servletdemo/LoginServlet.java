package com.servletdemo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns ={ "/LoginServlet" },
        initParams = {
            @WebInitParam(name = "user", value = "Akhil"),
            @WebInitParam(name = "password", value = "Capgemini")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        //String userId  = getServletConfig().getInitParameter("user");
        //String password = getServletConfig().getInitParameter("password");
        if(user.matches("^[A-Z][A-Za-z]{2,}$") && pwd.matches("^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?!.*[<>`])" + "(?=[^@#$%^&+=]*[@#$%^&+=][^@#$%^&+=]*$)" + ".{8,}$")){
            request.setAttribute("user",user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request,response);
        }else{
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username or password is wrong.</font>");
            requestDispatcher.include(request,response);
        }
    }

}
