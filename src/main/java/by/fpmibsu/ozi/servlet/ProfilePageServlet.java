package by.fpmibsu.ozi.servlet;

import by.fpmibsu.ozi.dao.*;
import by.fpmibsu.ozi.entity.User;
import by.fpmibsu.ozi.services.ProfilePageService;
import by.fpmibsu.ozi.services.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/ozi")
public class ProfilePageServlet extends HttpServlet
{
    private final ProfilePageService service = new ProfilePageService(new UserDao(), new FriendDao(), new PostDao(), new FriendRequestDao());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Integer pageId;
        try {
            String str = req.getParameter("pageId");
            if (str == null)
            {
                pageId = null;
            }
            else pageId = Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            pageId = null;
        }
        try {
            Status status = service.getStatus(pageId, userId);
            User user;
            if (status == Status.ME) user = service.getUserInfo(userId);
            else user = service.getUserInfo(pageId);

            Integer followersCount = service.getFollowersCount(user.getId());
            Integer friendsCount = service.getFriendsCount(user.getId());

            req.setAttribute("name", user.getName());

            req.setAttribute("surname", user.getSurname());

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d, yyyy");
            String inputDateStr = String.valueOf(service.getUserInfo(userId).getBirthday());
            java.util.Date inputDate = inputFormat.parse(inputDateStr);
            String outputDateStr = outputFormat.format(inputDate).toUpperCase();
            req.setAttribute("birthday", outputDateStr);

            req.setAttribute("friends", friendsCount.toString());
            req.setAttribute("followers", followersCount.toString());
            req.setAttribute("city", user.getCity());
            req.setAttribute("about", user.getAbout());
            req.setAttribute("status", status.toString());
            if (status == Status.ME)
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            else
                req.getRequestDispatcher("/another.jsp").forward(req, resp);
        } catch (DaoException | InterruptedException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getParameter("exit") != null)
        {
            req.getSession().invalidate();
            resp.sendRedirect("/ozi/login");
        }
        else if (req.getParameter("friend") != null)
        {
            if (req.getParameter("pageId") != null)
            {
                //String id = req.getParameter("pageId");
                resp.sendRedirect("/ozi/friends?id=" + req.getParameter("pageId"));
            }
            else if (req.getSession().getAttribute("userId") != null)
            {
                //String id = req.getParameter("pageId");
                resp.sendRedirect("/ozi/friends?id=" + req.getSession().getAttribute("userId"));
            }
        }
        else if (req.getParameter("follower") != null)
        {
            if (req.getParameter("pageId") != null)
            {
                //String id = req.getParameter("pageId");
                resp.sendRedirect("/ozi/followers?id=" + req.getParameter("pageId"));
            }
            else if (req.getSession().getAttribute("userId") != null)
            {
                //String id = req.getParameter("pageId");
                resp.sendRedirect("/ozi/followers?id=" + req.getSession().getAttribute("userId"));
            }
        }

    }

    void postHelpMethod(HttpServletRequest req, HttpServletResponse resp)
    {

    }
}
