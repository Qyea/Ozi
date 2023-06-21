package by.fpmibsu.ozi.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter({"/ozi", "/", "/ozi/post", "/ozi/editabout", "/ozi/friends", "/ozi/editFriends", "/ozi/find"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final HttpSession session = req.getSession();
        final Integer id = (Integer) session.getAttribute("userId");


        if (nonNull(session) && nonNull(id)) {
            filterChain.doFilter(request, response);
        } else {
            res.sendRedirect("/ozi/login");
        }
    }
    @Override
    public void destroy() {
    }
}