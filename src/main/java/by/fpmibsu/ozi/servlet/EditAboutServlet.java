package by.fpmibsu.ozi.servlet;

import by.fpmibsu.ozi.dao.*;
import by.fpmibsu.ozi.services.ProfilePageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ozi/editabout")
public class EditAboutServlet extends HttpServlet
{
    ProfilePageService service = new ProfilePageService(new UserDao(), new FriendDao(), new PostDao(), new FriendRequestDao());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            service.editAbout((Integer) req.getSession().getAttribute("userId"),(String) req.getParameter("about"));
            req.setAttribute("about", service.getUserInfo((Integer)req.getSession().getAttribute("userId")).getAbout());
        } catch (DaoException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/ozi");
    }
}
