package by.fpmibsu.ozi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dialog implements Serializable, Cloneable
{
    private User user1;

    private User user2;
    private final List<Message> messages;

    public Dialog()
    {
        messages = new ArrayList<>();
    }

    public Dialog(User user1, User user2, List<Message> messages)
    {
        this.user1 = user1;
        this.user2 = user2;
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public User getUser2() {
        return user2;
    }

    public User getUser1() {
        return user1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dialog between ").append(user1.getId()).append(" and ").append(user2.getId()).append(":\n");
        for (var item : messages)
        {
            stringBuilder.append(item);
        }

        return stringBuilder.toString();
    }
}
