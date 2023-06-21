package by.fpmibsu.ozi.db;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class ConnectionPool {
    private static BlockingQueue<Connection> connectionQueue;
    private static final int POOL_SIZE = 10;

    public static Properties PROPERTIES = new Properties();

    static
    {
        try
        {
            PROPERTIES = ConnectionCreator.PROPERTIES;
            connectionQueue = new ArrayBlockingQueue<Connection> (POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = ConnectionCreator.createConnection();
                connectionQueue.offer(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws InterruptedException {
        Connection connection = null;
        connection = connectionQueue.take();
        return connection;
    }
    public static void closeConnection(Connection connection) {
        connectionQueue.offer(connection);
    }
}
