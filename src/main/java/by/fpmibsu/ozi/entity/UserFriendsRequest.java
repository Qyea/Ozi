package by.fpmibsu.ozi.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class UserFriendsRequest implements Serializable, Cloneable
{
    private User user;

    private List<User> requests;

    public UserFriendsRequest()
    {

    }

    public UserFriendsRequest(User user, List<User> requests)
    {
        this.user = user;
        this.requests = requests;
    }

    public User getUser() {
        return user;
    }

    public List<User> getRequests() {
        return requests;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user).append(" has friends request from: \n");
        for (var item : requests)
        {
            stringBuilder.append(item).append('\n');
        }

        return stringBuilder.toString();
    }
}
