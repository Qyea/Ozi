package by.fpmibsu.ozi.dao;

import by.fpmibsu.ozi.db.ConnectionPool;
import by.fpmibsu.ozi.entity.Friend;
import by.fpmibsu.ozi.entity.FriendRequest;
import by.fpmibsu.ozi.entity.User;
import by.fpmibsu.ozi.entity.UserFriends;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendDao implements Dao<Friend>
{
    public static final String SQL_SELECT_ALL = "SELECT * FROM friends;";
    public static final String SQL_DELETE_FRIEND = "DELETE FROM friends WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?);";
    public static final String SQL_CREATE_FRIEND = "INSERT INTO friends(user_id, friend_id, date) VALUES(?, ?, ?),(?, ?, ?);";
    public static final String SQL_SELECT_ALL_BY_USER_ID = "SELECT * FROM friends WHERE user_id = ? ORDER BY friend_id;";
    @Override
    public List<Friend> findAll() throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL))
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
    public boolean delete(Friend friend) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FRIEND))
        {
            int user_id = friend.getPerson().getId();
            int friend_id = friend.getFriend(). getId();
            statement.setInt(1, user_id);
            statement.setInt(2, friend_id);
            statement.setInt(3, friend_id);
            statement.setInt(4,user_id);
            FriendRequestDao friendRequestDao = new FriendRequestDao();
            friendRequestDao.create(new FriendRequest(friend.getPerson(), friend.getFriend(), new Date(new java.util.Date().getTime())));
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }

    }

    @Override
    public boolean create(Friend friend) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(SQL_CREATE_FRIEND))
        {
            int user_id = friend.getPerson().getId();
            int frined_id = friend.getFriend().getId();
            Date date = friend.getDate();
            statement.setInt(1, user_id);
            statement.setInt(2, frined_id);
            statement.setDate(3, date);
            statement.setInt(5, user_id);
            statement.setInt(4, frined_id);
            statement.setDate(6, date);
            FriendRequestDao friendRequestDao = new FriendRequestDao();

            UserDao userDao = new UserDao();
            friendRequestDao.delete(new FriendRequest(friend.getPerson(), friend.getFriend(), new Date(new java.util.Date().getTime())));
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
    public Friend update(Friend friend) throws DaoException {
        return null;
    }

    public UserFriends findFriendsByUserId(int id) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_BY_USER_ID))
        {
            statement.setInt(1, id);
            List<Friend> friends = createFromResultSet(statement.executeQuery());
            User main = new UserDao().findById(id);
            List<User> result = new ArrayList<>();
            for (var item : friends)
            {
                result.add(item.getFriend());
            }
            return new UserFriends(main, result);
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    private List<Friend> createFromResultSet(ResultSet set) throws SQLException, DaoException
    {
        try {
            UserDao userDao = new UserDao();
            ArrayList<Friend> list = new ArrayList<>();
            while (set.next()) {
                list.add(new Friend(
                        userDao.findById(set.getInt("user_id")),
                        userDao.findById(set.getInt("friend_id")),
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
