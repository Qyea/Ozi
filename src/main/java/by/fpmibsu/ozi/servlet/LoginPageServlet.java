package by.fpmibsu.ozi.servlet;

import by.fpmibsu.ozi.dao.UserDao;
import by.fpmibsu.ozi.services.LoginPageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/ozi/login")
public class LoginPageServlet extends HttpServlet {
    private final LoginPageService service = new LoginPageService(new UserDao());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Integer id = (Integer) req.getSession().getAttribute("userId");
        if (id != null) {
            resp.sendRedirect("/ozi");
            return;
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Integer id = service.login(req.getParameter("login"), req.getParameter("password"));
        if (id == null)
        {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        else
        {
            HttpSession session = req.getSession();
            session.setAttribute("userId", id);
            resp.sendRedirect("/ozi");
        }
    }
}
