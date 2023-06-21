package by.fpmibsu.ozi.dao;

import by.fpmibsu.ozi.db.ConnectionPool;
import by.fpmibsu.ozi.entity.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDao implements Dao<Post>
{
    public static final String SQL_SELECT_ALL_POSTS = "SELECT * FROM posts ORDER BY date, id;";

    public static final String SQL_SELECT_ALL_POSTS_BY_USER_ID = "SELECT * FROM posts WHERE user_id = ? ORDER BY date, id";

    public static final String SQL_CREATE_POST = "INSERT INTO posts(user_id, text, date) values(?, ?, ?)";

    public static final String SQL_UPDATE_POST = "UPDATE posts SET text = ? WHERE id = ?";

    public static final String SQL_DELETE_POST = "DELETE FROM posts WHERE id = ?";

    @Override
    public List<Post> findAll() throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_POSTS))
        {
            ResultSet set = statement.executeQuery();
            return createFromResultSet(set);
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    @Override
    public boolean delete(Post post) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_POST))
        {
            statement.setInt(1, post.getId());
            return statement.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    @Override
    public boolean create(Post post) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_POST))
        {
            statement.setInt(1, post.getUser().getId());
            statement.setString(2, post.getText());
            statement.setDate(3, post.getDate());

            return statement.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    @Override
    public Post update(Post post) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_POST))
        {
            statement.setString(1, post.getText());
            statement.setInt(2, post.getId());

            return statement.executeUpdate() > 0 ? post : null;
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    public List<Post> findAllByUserId(Integer id) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_POSTS_BY_USER_ID))
        {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            return createFromResultSet(set);
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    private List<Post> createFromResultSet(ResultSet set) throws SQLException, DaoException
    {
        try {
            UserDao userDao = new UserDao();
            ArrayList<Post> list = new ArrayList<>();
            while (set.next()) {
                list.add(new Post(
                        set.getInt("id"),
                        userDao.findById(set.getInt("user_id")),
                        set.getString("text"),
                        set.getDate("date")
                ));
            }

            return list;
        }
        catch (SQLException | DaoException | InterruptedException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }
}
