/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author enrico
 */
@WebServlet("/editDetails")
public class EditUser extends HttpServlet {

    private static final int MIN_CODE = 100000;
    private static final int MAX_CODE = 999999;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = generateVerificationCode();
    }

    private String generateVerificationCode() {
        Random rand = new Random();
        Integer code = rand.nextInt(MIN_CODE - MAX_CODE + 1) + MAX_CODE;
        return code.toString();
    }
}
