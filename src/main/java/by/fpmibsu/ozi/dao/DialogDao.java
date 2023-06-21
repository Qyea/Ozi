package by.fpmibsu.ozi.dao;

import java.sql.Connection;

import by.fpmibsu.ozi.db.ConnectionPool;
import by.fpmibsu.ozi.entity.Dialog;
import by.fpmibsu.ozi.entity.Message;
import by.fpmibsu.ozi.entity.User;
import by.fpmibsu.ozi.entity.UserDialogs;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DialogDao implements Dao<Dialog>
{
    public static final String SQL_SELECT_ALL_USERS_WITH_WHOM_USER_HAS_DIALOG = "SELECT DISTINCT receiver_id as id FROM messages WHERE sender_id = ? " +
            "UNION " +
            "SELECT DISTINCT sender_id as id FROM messages WHERE receiver_id = ?;";
    @Override
    public List<Dialog> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Dialog dialog) throws DaoException {
        try
        {
            MessageDao messageDao = new MessageDao();
            for (var item : dialog.getMessages())
            {
                messageDao.delete(item);
            }

            return true;
        }
        catch (DaoException | InterruptedException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean create(Dialog dialog) throws DaoException {
        return false;
    }

    @Override
    public Dialog update(Dialog dialog) throws DaoException {
        MessageDao messageDao = new MessageDao();

        for (var item : dialog.getMessages())
        {
            try {
                messageDao.update(item);
            } catch (InterruptedException e) {
                throw new DaoException(e);
            }
        }

        return dialog;
    }

    public UserDialogs getUserDialogs(User user) throws DaoException, InterruptedException
    {
        Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS_WITH_WHOM_USER_HAS_DIALOG))
        {
            statement.setInt(1, user.getId());
            statement.setInt(2, user.getId());

            ResultSet set = statement.executeQuery();
            ArrayList<Dialog> userDialogs = new ArrayList<>();
            while(set.next())
            {
                UserDao userDao = new UserDao();
                MessageDao messageDao = new MessageDao();
                User secondUser = userDao.findById(set.getInt("id"));
                List<Message> messages = messageDao.findBySenderAndReceiverId(user, secondUser);

                userDialogs.add((new Dialog(user, secondUser, messages)));
            }

            return new UserDialogs(userDialogs, user);
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
    }
}
