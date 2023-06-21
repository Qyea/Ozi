package by.fpmibsu.ozi.services;

import by.fpmibsu.ozi.dao.DaoException;
import by.fpmibsu.ozi.dao.FriendRequestDao;
import by.fpmibsu.ozi.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.List;

public class FollowersPageService
{
    static Logger logger = LogManager.getLogger(FollowersPageService.class.getName());
    FriendRequestDao friendRequestDao;

    public FollowersPageService(FriendRequestDao friendRequestDao)
    {
        logger.log(Level.INFO, "Created FollowersPageService.");
        this.friendRequestDao = friendRequestDao;
    }

    public List<User> getFollowers(Integer userId) throws DaoException, InterruptedException {
        logger.log(Level.INFO, "Getting all followers of user with id " + userId + '.');
        return friendRequestDao.findAllByReceiverId(userId).getRequests();
    }
}
