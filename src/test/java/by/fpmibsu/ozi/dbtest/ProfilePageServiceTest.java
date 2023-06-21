package by.fpmibsu.ozi.dbtest;

import by.fpmibsu.ozi.dao.*;
import by.fpmibsu.ozi.db.ConnectionPool;
import by.fpmibsu.ozi.entity.Post;
import by.fpmibsu.ozi.services.ProfilePageService;
import by.fpmibsu.ozi.services.Status;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProfilePageServiceTest
{
    private final ProfilePageService service = new ProfilePageService(new UserDao(), new FriendDao(), new PostDao(), new FriendRequestDao());
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
    public void getFriendCountTest()
    {
        try {
            Integer ans = 4;
            Assert.assertEquals(ans, service.getFriendsCount(1));
            ans = 1;
            Assert.assertEquals(ans, service.getFriendsCount(2));
        } catch (DaoException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getFollowersCountTest()
    {
        Integer ans = 2;
        try {
            Assert.assertEquals(ans, service.getFollowersCount(2));
            ans = 0;
            Assert.assertEquals(ans, service.getFollowersCount(1));
            ans = 1;
            Assert.assertEquals(ans, service.getFriendsCount(3));
        } catch (DaoException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getUserPosts()
    {

        int ans = 2;
        try {
            Assert.assertEquals(ans, service.getUserPost(1).size());
            List<Post> posts = service.getUserPost(1);
            Post post = new Post(1, new UserDao().findById(1), "I am being cool", new java.sql.Date(Date.parse("05/11/2023")));
            Assert.assertEquals(post.getText(), posts.get(0).getText());
            Assert.assertEquals(post.getDate(), posts.get(0).getDate());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void getUserInfoTest()
    {
        try {
            Assert.assertEquals("+375445918769", service.getUserInfo(1).getPhone());
            Assert.assertNull(service.getUserInfo(12));
        } catch (DaoException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getStatusTest()
    {
        try {
            Assert.assertEquals(Status.ME, service.getStatus(1, 1));
            Assert.assertEquals(Status.NOT_REGISTERED, service.getStatus(null, 2));
            Assert.assertEquals(Status.FRIEND, service.getStatus(1, 4));
            Assert.assertEquals(Status.FRIEND, service.getStatus(4, 1));
            Assert.assertEquals(Status.FOLLOWER, service.getStatus(2, 3));
            Assert.assertEquals(Status.REQUEST_SEND, service.getStatus(3, 2));
            Assert.assertEquals(Status.NO_ONE, service.getStatus(1, 6));
            Assert.assertEquals(Status.ERROR, service.getStatus(1, null));
        } catch (DaoException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void editAbout()
    {
        try {
            service.editAbout(1, "Hello world!!!");
            Assert.assertEquals("Hello world!!!", service.getUserInfo(1).getAbout());
        } catch (DaoException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @After
    public void tearDown() {
        new DataDestructor();
    }
}
