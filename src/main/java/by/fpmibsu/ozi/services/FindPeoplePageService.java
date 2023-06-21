package by.fpmibsu.ozi.services;

import by.fpmibsu.ozi.dao.DaoException;
import by.fpmibsu.ozi.dao.UserDao;
import by.fpmibsu.ozi.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.List;

public class FindPeoplePageService
{
    static Logger logger = LogManager.getLogger(FindPeoplePageService.class.getName());
    private final UserDao userDao;

    public FindPeoplePageService(UserDao userDao)
    {
        logger.log(Level.INFO, "Created FindPeoplePageService.\n");
        this.userDao = userDao;
    }

    public List<User> findByName(String name) throws DaoException, InterruptedException {
        logger.log(Level.INFO, "Finding people by name in method findByName.");
        return userDao.findByName(name);
    }
}
