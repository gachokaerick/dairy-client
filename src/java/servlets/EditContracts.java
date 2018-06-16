/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Contract;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rest.contract.ContractService;

/**
 *
 * @author enrico
 */
@WebServlet("/editContracts")
public class EditContracts extends HttpServlet {

    private ContractService client = new ContractService();
    private String statusMsg = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/manageContracts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = Integer.parseInt(req.getParameter("contract"));
        List<Contract> list = (List<Contract>) req.getSession().getAttribute("list");
        Contract contract = list.get(index);
        if (req.getParameter("approve") != null) {
            //set status to approved before sending the contract
            contract.setStatus("approved");
            statusMsg = client.editContractStatus(contract);
            req.setAttribute("editMsg", statusMsg);
        }
        if (req.getParameter("deny") != null) {
            //set status to denied before sending the contract
            contract.setStatus("denied");
            statusMsg = client.editContractStatus(contract);
            req.setAttribute("editMsg", statusMsg);
        }
        if (req.getParameter("delete") != null) {
            //delete the contract
            String contractId = contract.getSupplierId() + "";
            statusMsg = client.deleteContract(contractId);
            req.setAttribute("editMsg", statusMsg);
        }
        req.getRequestDispatcher("/manageContracts.jsp").forward(req, resp);
    }

}
