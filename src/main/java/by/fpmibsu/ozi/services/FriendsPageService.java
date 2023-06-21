package by.fpmibsu.ozi.services;

import by.fpmibsu.ozi.dao.DaoException;
import by.fpmibsu.ozi.dao.FriendDao;
import by.fpmibsu.ozi.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.List;

public class FriendsPageService
{
    static Logger logger = LogManager.getLogger(FriendsPageService.class.getName());
    FriendDao friendDao;

    public FriendsPageService(FriendDao friendDao)
    {
        logger.log(Level.INFO, "Created FriendsPageService.");
        this.friendDao = friendDao;
    }

    public List<User> getAllFriends(Integer userId) throws DaoException, InterruptedException {
        logger.log(Level.INFO, "Getting all friends of user with id " + userId + '.');
        return friendDao.findFriendsByUserId(userId).getFriends();
    }
}
