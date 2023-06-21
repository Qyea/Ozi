package by.fpmibsu.ozi.dbtest;

import by.fpmibsu.ozi.dao.DaoException;
import by.fpmibsu.ozi.dao.UserDao;
import by.fpmibsu.ozi.db.ConnectionPool;
import by.fpmibsu.ozi.services.FindPeoplePageService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class FindPeopleServiceTest
{
    private FindPeoplePageService service = new FindPeoplePageService(new UserDao());
    private static final String SQL_SELECT_USER = "SELECT count(*) as tmp FROM user;";
    private static final String SQL_SELECT_FRIENDS = "SELECT count(*) as tmp FROM friends;";
    private static final String SQL_SELECT_MESSAGES = "SELECT count(*) as tmp FROM messages;";
    private static final String SQL_SELECT_POSTS = "SELECT count(*) as tmp FROM posts;";
    private static final String SQL_SELECT_FRIEND_REQUEST = "SELECT count(*) as tmp FROM friend_requests;";

    @Before
    public void init() {
        new DataInitializer();
    }

    @Test
    public void initialTest()
    {
        if (!ConnectionPool.PROPERTIES.getProperty("db.url").equals("jdbc:mysql://127.0.0.1:3306/ozitest")) throw new RuntimeException();
        try {
            Connection connection = ConnectionPool.getConnection();
            try {
                PreparedStatement user = connection.prepareStatement(SQL_SELECT_USER);
                PreparedStatement friends = connection.prepareStatement(SQL_SELECT_FRIENDS);
                PreparedStatement messages = connection.prepareStatement(SQL_SELECT_MESSAGES);
                PreparedStatement posts = connection.prepareStatement(SQL_SELECT_POSTS);
                PreparedStatement friend_request = connection.prepareStatement(SQL_SELECT_FRIEND_REQUEST);
                Integer userCount = -1, friendsCount = -1, messagesCount = -1, postsCount = -1, friend_requestCount = -1;
                ResultSet tmp = user.executeQuery();
                if (tmp.next()) userCount = tmp.getInt("tmp");
                tmp = friends.executeQuery();
                if (tmp.next()) friendsCount = tmp.getInt("tmp");
                tmp = messages.executeQuery();
                if (tmp.next()) messagesCount = tmp.getInt("tmp");
                tmp = posts.executeQuery();
                if (tmp.next()) postsCount = tmp.getInt("tmp");
                tmp = friend_request.executeQuery();
                if (tmp.next()) friend_requestCount = tmp.getInt("tmp");

                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(userCount);
                list.add(friendsCount);
                list.add(messagesCount);
                list.add(postsCount);
                list.add(friend_requestCount);

                ArrayList<Integer> mustBe = new ArrayList<>(Arrays.asList(5, 8, 6, 8, 4));
                Assert.assertEquals(list.toArray(), mustBe.toArray());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                ConnectionPool.closeConnection(connection);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void findByNameTest()
    {
        try
        {
            Assert.assertEquals((int)4, service.findByName("s").size());
            Assert.assertEquals((int)2, service.findByName("dasha").size());
        }
        catch (DaoException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @After
    public void tearDown() {
        new DataDestructor();
    }
}
