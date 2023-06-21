package by.fpmibsu.ozi.services;

import by.fpmibsu.ozi.dao.DaoException;
import by.fpmibsu.ozi.dao.UserDao;
import by.fpmibsu.ozi.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationPageService
{
    static Logger logger = LogManager.getLogger(RegistrationPageService.class.getName());
    UserDao userDao;

    public RegistrationPageService(UserDao userDao)
    {
        this.userDao = userDao;
        logger.log(Level.INFO, "Create RegistrationPageService.");
    }

    public User register(User user)
    {
        logger.log(Level.INFO, "Registering user.");
        try
        {
            boolean res = userDao.create(user);
            if (!res)
            {
                return null;
            }
            logger.log(Level.INFO, "User: " + userDao.findByPhone(user.getPhone()).toString() + " registered.");
            return userDao.findByPhone(user.getPhone());
        }
        catch (DaoException | InterruptedException e)
        {
            logger.log(Level.ERROR, "Unable to create user because " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return null;
    }
}
