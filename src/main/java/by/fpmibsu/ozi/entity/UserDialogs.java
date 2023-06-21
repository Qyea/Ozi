package by.fpmibsu.ozi.entity;

import java.io.Serializable;
import java.util.List;

public class UserDialogs implements Serializable, Cloneable
{
    private final User user;
    private final List<Dialog> dialogs;

    public UserDialogs(List<Dialog> dialogs, User user)
    {
        this.dialogs = dialogs;
        this.user = user;
    }

    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user).append(" has dialogs with: \n");
        for (var item : dialogs)
        {
            stringBuilder.append(item.getUser2()).append('\n');
        }
        return stringBuilder.toString();
    }
}
