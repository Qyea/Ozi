package by.fpmibsu.ozi.entity;

import java.sql.Date;

public class Friend
{
    private final User person;
    private final User friend;

    private final Date date;

    public Friend()
    {
        this.person = null;
        this.friend = null;
        this.date = null;
    }

    public Friend(User person, User friend, Date date)
    {
        this.person = person;
        this.friend = friend;
        this.date = date;
    }

    public User getPerson() {
        return person;
    }

    public User getFriend() {
        return friend;
    }

    @Override
    public String toString() {
        return person + " and " + friend + " are friends.";
    }

    public Date getDate() {
        return date;
    }
}
