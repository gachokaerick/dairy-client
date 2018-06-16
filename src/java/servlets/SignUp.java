package servlets;

import domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rest.user.UserService;

/**
 *
 * @author enrico
 */
@WebServlet("/signup")
public class SignUp extends HttpServlet {

    private User user;
    private final UserService client = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = "";
        try {
            String firstName = req.getParameter("first-name").trim();
            String lastName = req.getParameter("last-name").trim();
            int id = Integer.parseInt(req.getParameter("national-id").trim());
            String email = req.getParameter("email").trim();
            int phone = Integer.parseInt(req.getParameter("phone"));
            String address = req.getParameter("address").trim();
            String role = "supplier";
            String password = req.getParameter("password");
            user = new User(id, firstName, lastName, email, phone, address, role, password);

            String msg = client.saveUser(user);
            req.setAttribute("message", msg);
        } catch (NumberFormatException e) {
            error = "National id must consist of digits only";
        } catch (Exception e) {
            error = e.getLocalizedMessage();
        }
        if (!"".equals(error)) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
