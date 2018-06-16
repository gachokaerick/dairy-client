/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.LoginUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rest.user.login.LoginService;

/**
 *
 * @author enrico
 */
@WebServlet("/login")
public class Login extends HttpServlet {

    private final LoginService client = new LoginService();
    private String msg = "";
    private String error = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String password = req.getParameter("password");

            LoginUser loginUser = new LoginUser(id, password);
            msg = client.checkUser(loginUser);
            int count = Integer.parseInt(msg);
            if (count == 1) {
                req.setAttribute("message", "Login Successful");
            }

            //set up HTTP session
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            req.getRequestDispatcher("/applyContract.jsp").forward(req, resp);
        } catch (Exception e) {
            error = e.getLocalizedMessage();
            req.setAttribute("error", "Login Error");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

}
