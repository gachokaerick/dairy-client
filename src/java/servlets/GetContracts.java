/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Contract;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rest.contract.ContractService;

/**
 *
 * @author enrico
 */
@WebServlet("/getContracts")
public class GetContracts extends HttpServlet {

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    ContractService client = new ContractService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Contract> list = null;
        if (req.getParameter("denied") != null) {
            //get denied contracts
            list = client.getDeniedContracts();
            //set an attribute to track the status of the contracts you want to display
            req.getSession().setAttribute("requiredStatus", "denied");
        } else if (req.getParameter("pending") != null) {
            //get pending contracts
            list = client.getPendingContracts();
            //set an attribute to track the status of the contracts you want to display
            req.getSession().setAttribute("requiredStatus", "pending");
        } else if (req.getParameter("approved") != null) {
            //get approved contracts
            list = client.getApprovedContracts();
            //set an attribute to track the status of the contracts you want to display
            req.getSession().setAttribute("requiredStatus", "approved");
        } else {
            req.getRequestDispatcher("/applyContract.jsp").forward(req, resp);
        }
        req.getSession().setAttribute("list", list);
        req.getRequestDispatcher("/manageContracts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = "";
        HttpSession session = req.getSession();
        try {
            int id = (int) session.getAttribute("id");
            Date startDate = formatter.parse(req.getParameter("start").trim());
            Date endDate = formatter.parse(req.getParameter("end").trim());
            System.out.println(req.getParameter("start").getClass().getName());
            System.out.println(req.getParameter("end").getClass().getName());
            String start = formatter.format(startDate);
            String end = formatter.format(endDate);
            float amount = Float.parseFloat(req.getParameter("amount"));
            float cost = Float.parseFloat(req.getParameter("cost"));
            String pending = "true";

            Contract contract = new Contract(id, start, end, amount, cost, pending);
            req.setAttribute("contract", contract);
            String msg = client.saveContract(contract);
            req.setAttribute("message", msg);
            req.getRequestDispatcher("/applyContract.jsp").forward(req, resp);
        } catch (Exception ex) {
            error = ex.getMessage();
            req.setAttribute("error", error);
            Logger.getLogger(GetContracts.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
