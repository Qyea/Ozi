package by.fpmibsu.ozi.dao;

import by.fpmibsu.ozi.db.ConnectionPool;
import by.fpmibsu.ozi.entity.Message;
import by.fpmibsu.ozi.entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao implements Dao<Message>
{
    public static final String SQL_SELECT_ALL = "SELECT * FROM messages ORDER BY date";

    public static final String SQL_DELETE_MESSAGE = "DELETE FROM messages WHERE id = ?";

    public static final String SQL_UPDATE_MESSAGE = "UPDATE messages SET message = ? WHERE id = ?";

    public static final String SQL_CREATE_MESSAGE = "INSERT INTO messages(receiver_id, sender_id, message, date) " +
            "VALUES(?, ?, ?, ?)";

    public static final String SQL_SELECT_BY_RECEIVER_AND_SENDER = "SELECT * FROM messages WHERE (receiver_id = ? AND sender_id = ?) OR (receiver_id = ? AND sender_id = ?) ORDER BY date DESC, id DESC;";

    @Override
    public List<Message> findAll() throws DaoException, InterruptedException {
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
    public boolean delete(Message message) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MESSAGE))
        {
            statement.setInt(1, message.getId());
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
    public boolean create(Message message) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_MESSAGE))
        {
            if (message.getReceiveUser() == null || message.getSentUser() == null) return false;
            statement.setInt(1, message.getReceiveUser().getId());
            statement.setInt(2, message.getSentUser().getId());
            statement.setString(3, message.getText());
            statement.setDate(4, message.getMessageDate());

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
    public Message update(Message message) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MESSAGE))
        {
            statement.setString(1, message.getText());

            if (statement.executeUpdate() > 0)
                return message;
            else
                return null;
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
    }

    public List<Message> findBySenderAndReceiverId(User receiver, User sender) throws DaoException, InterruptedException {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_RECEIVER_AND_SENDER))
        {
            if (receiver == null || sender == null) return new ArrayList<>();
            statement.setInt(1, receiver.getId());
            statement.setInt(2, sender.getId());
            statement.setInt(3, sender.getId());
            statement.setInt(4, receiver.getId());

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

    private List<Message> createFromResultSet(ResultSet set) throws SQLException, DaoException
    {
        try {
            UserDao userDao = new UserDao();
            ArrayList<Message> list = new ArrayList<>();
            while (set.next()) {
                list.add(
                        new Message(
                                set.getInt("id"),
                                userDao.findById(set.getInt("sender_id")),
                                userDao.findById(set.getInt("receiver_id")),
                                set.getDate("date"),
                                set.getString("message")
                        )
                );
            }

            return list;
        }
        catch (SQLException | DaoException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}