package by.fpmibsu.ozi.entity;

import java.io.Serializable;
import java.sql.Date;

public class Post implements Serializable, Cloneable
{
    private Integer id;

    private User user;

    private String text;

    private Date date;

    public Post()
    {

    }

    public Post(Integer id, User user, String text, Date date)
    {
        this.id = id;
        this.user = user;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(" ").append(user).append(" ").append(text).append(" ").append(date);
        return stringBuilder.toString();
    }
}
