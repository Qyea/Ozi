package by.fpmibsu.ozi.entity;

import java.io.Serializable;
import java.sql.Date;
public class Message implements Serializable, Cloneable
{
    private Integer id;

    private User sentUser;

    private User receiveUser;

    private Date messageDate;

    private String text;

    public Message()
    {

    }

    public Message(
            Integer id,
            User sentUser,
            User receiveUser,
            Date messageDate,
            String text
    )
    {
        this.id = id;
        this.sentUser = sentUser;
        this.receiveUser = receiveUser;
        this.messageDate = messageDate;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public User getSentUser() {
        return sentUser;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Message id: ").append(id).append('\n');
        stringBuilder.append("Message text: ").append(text).append('\n');
        stringBuilder.append("Receiver name: ").append(receiveUser.getName()).append('\n');
        stringBuilder.append("Sender name: ").append(sentUser.getName()).append('\n');
        stringBuilder.append("Date: ").append(messageDate).append('\n');

        return stringBuilder.toString();
    }
}
