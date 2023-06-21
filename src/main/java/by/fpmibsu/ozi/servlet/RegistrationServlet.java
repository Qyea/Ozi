package by.fpmibsu.ozi.servlet;

import by.fpmibsu.ozi.dao.UserDao;
import by.fpmibsu.ozi.entity.User;
import by.fpmibsu.ozi.services.DialogPageService;
import by.fpmibsu.ozi.services.RegistrationPageService;
import com.mysql.cj.Session;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ozi/register")
public class RegistrationServlet extends HttpServlet
{
    static Logger logger = LogManager.getLogger(RegistrationServlet.class.getName());
    private final RegistrationPageService service = new RegistrationPageService(new UserDao());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = (Long) req.getSession().getAttribute("userId");
        if (id == null)
        {
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        else
        {
            resp.sendRedirect("/ozi/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                0,
                req.getParameter("phone"),
                req.getParameter("email"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("birthday").replace('-', '/'),
                req.getParameter("sex"),
                req.getParameter("country"),
                req.getParameter("city"),
                req.getParameter("about"),
                null
        );

        user = service.register(user);

        if (user == null)
        {
            logger.log(Level.INFO, "User is null");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        else
        {
            logger.log(Level.INFO, "User id is " + user.getId());
            Integer res = user.getId();
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", res);
            resp.sendRedirect("/ozi");
        }
    }
}
