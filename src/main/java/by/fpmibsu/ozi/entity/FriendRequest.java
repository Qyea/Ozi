package by.fpmibsu.ozi.entity;

import java.sql.Date;


public class FriendRequest
{
    private final User receiver;
    private final User sender;

    private final Date date;

    public FriendRequest()
    {
        this.receiver = null;
        this.sender = null;
        this.date = null;
    }

    public FriendRequest(User receiver, User sender, Date date)
    {
        this.receiver = receiver;
        this.sender = sender;
        this.date = date;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return sender + " wants to be a friend of " + receiver;
    }

    public Date getDate() {
        return date;
    }
}
