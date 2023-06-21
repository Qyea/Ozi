package by.fpmibsu.ozi.dbtest;

import by.fpmibsu.ozi.db.ConnectionPool;
import by.fpmibsu.ozi.entity.User;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataInitializer
{
    private static final String SQL_CREATION = "INSERT INTO user VALUES\n" +
            "(1, '+375445918769', 'stas315172004@mail.ru', ?, 'Stas', 'Zaycev', '2004-02-05', 'm', 'Belarus', 'Minsk', 'My name is Stas and I like playing guitar', null),\n" +
            "(2, '+375445918768', 'stas315172005@mail.ru', ?, 'Dasha', 'Glovackay', '2004-05-30', 'f', 'Belarus', 'Minsk', 'My name is Dasha', null),\n" +
            "(3, '+375445918767', 'stas315172006@mail.ru', ?, 'Nastya', 'Krech', '2004-02-03', 'f', 'Belarus', 'Minsk', 'My name is Nastya', null),\n" +
            "(4, '+375445918766', 'stas315172007@mail.ru', ?, 'Dasha', 'Vusik', '2004-03-16', 'f', 'Belarus', 'Minsk', 'My name is Dasha and i am dasha', null),\n" +
            "(5, '+375445918765', 'stas315172008@mail.ru', ?, 'Maxim', 'Kinchikov', '2003-06-04', 'm', 'Belarus', 'Minsk', 'My name is Max', null);\n";
    private static final String SQL_CREATION2 = "INSERT INTO posts VALUES\n" +
            "(1, 1, 'I am being cool', '2023-05-12'),\n" +
            "(2, 1, 'I am being cool', '2023-05-11'),\n" +
            "(3, 2, 'I am being cool', '2023-05-05'),\n" +
            "(4, 2, 'I am being cool', '2023-03-11'),\n" +
            "(5, 3, 'I am being cool', '2023-01-01'),\n" +
            "(6, 4, 'I am being cool', '2023-02-12'),\n" +
            "(7, 5, 'I am being cool', '2023-02-28'),\n" +
            "(8, 5, 'I am being cool', '2023-01-01');\n";
    private static final String SQL_CREATION3 = "INSERT INTO friends VALUES\n" +
            "(1, '2022-01-01', 1, 5),\n" +
            "(2, '2022-02-01', 1, 4),\n" +
            "(3, '2022-03-01', 1, 3),\n" +
            "(4, '2022-04-01', 1, 2),\n" +
            "(5, '2022-01-01', 5, 1),\n" +
            "(6, '2022-02-01', 4, 1),\n" +
            "(7, '2022-03-01', 3, 1),\n" +
            "(8, '2022-04-01', 2, 1);\n";
    private static final String SQL_CREATION4 = "INSERT INTO friend_requests VALUES\n" +
            "(1, '2022-01-01', 2, 3),\n" +
            "(2, '2022-02-01', 3, 4),\n" +
            "(3, '2022-03-01', 5, 4),\n" +
            "(4, '2022-04-01', 2, 5);\n";
    private static final String SQL_CREATION5 = "INSERT INTO messages VALUES\n" +
            "(1, '2022-01-01', 'Hello!', 1, 2),\n" +
            "(2, '2022-01-01', 'Hi!', 2, 1),\n" +
            "(3, '2022-01-01', 'How are you?!', 1, 2),\n" +
            "(4, '2022-01-01', 'Hey!!', 2, 3),\n" +
            "(5, '2022-01-01', 'Hello)))!', 3, 2),\n" +
            "(6, '2022-01-01', 'My name is LOL!', 4, 5);\n";
    public DataInitializer()
    {
        if (!ConnectionPool.PROPERTIES.getProperty("db.url").equals("jdbc:mysql://127.0.0.1:3306/ozitest")) throw new RuntimeException();
        try {
            Connection connection = ConnectionPool.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQL_CREATION)) {
                for (int i = 1; i < 6; ++i) {
                    statement.setString(i, User.makeHash("qwerty123"));
                }
                statement.executeUpdate();

                Statement statement1 = connection.createStatement();
                statement1.execute(SQL_CREATION2);
                statement1.execute(SQL_CREATION3);
                statement1.execute(SQL_CREATION4);
                statement1.execute(SQL_CREATION5);

                statement1.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            finally {
                ConnectionPool.closeConnection(connection);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
