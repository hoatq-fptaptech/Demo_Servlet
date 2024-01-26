package com.example.demo2;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/demo")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World! AA";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        request.setAttribute("name","Demo JSP Servlet");
        RequestDispatcher dispatcher = request.getRequestDispatcher("hello.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        this.doGet(request,response);
    }

    public void destroy() {
    }
}