package by.fpmibsu.ozi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao<T>
{
    List<T> findAll() throws DaoException, InterruptedException;

    boolean delete(T t) throws DaoException, InterruptedException;

    boolean create(T t) throws DaoException, InterruptedException;

    T update(T t) throws DaoException, InterruptedException;

    default void close(Statement statement) throws DaoException
    {
        try
        {
            if (statement != null)
            {
                statement.close();
            }
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    default void close(Connection connection) throws DaoException
    {
        try
        {
            if (connection != null)
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }
}
