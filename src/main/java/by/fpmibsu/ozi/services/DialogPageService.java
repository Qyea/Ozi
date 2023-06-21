package by.fpmibsu.ozi.services;

import by.fpmibsu.ozi.dao.*;
import by.fpmibsu.ozi.entity.Message;
import by.fpmibsu.ozi.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class DialogPageService
{
    static Logger logger = LogManager.getLogger(DialogPageService.class.getName());
    private final UserDao userDao;

    private final MessageDao messageDao;

    private final FriendDao friendDao;

    public DialogPageService(UserDao userDao, MessageDao messageDao, FriendDao friendDao)
    {
        logger.log(Level.INFO, "Created DialogPageService.");
        this.userDao = userDao;
        this.friendDao = friendDao;
        this.messageDao = messageDao;
    }

    public List<User> getFriends(Integer userId) throws DaoException, InterruptedException {
        logger.log(Level.INFO, "Getting all friends of user with id: " + userId + '.');
        return friendDao.findFriendsByUserId(userId).getFriends();
    }

    public List<Message> getMessages(Integer userId, Integer messagePersonId) throws DaoException, InterruptedException {
        logger.log(Level.INFO, "Getting messages of users with ids: " + userId + " and " + messagePersonId + '.');
        return messageDao.findBySenderAndReceiverId(userDao.findById(userId), userDao.findById(messagePersonId));
    }

    public void sendMessage(Integer userId, Integer messagePersonId, String text, Date messageDate) throws DaoException, InterruptedException {
        logger.log(Level.INFO, "Sending message from user with id " + userId + " to user with id " + messagePersonId + '.');
        boolean res = messageDao.create(new Message(0, userDao.findById(userId), userDao.findById(messagePersonId), messageDate, text));
        if (res) logger.log(Level.INFO, "Successfully sent.");
        else logger.log(Level.INFO, "Unable to send.");
    }
}
