package by.fpmibsu.ozi.entity;

import java.util.List;

public class UserFriends
{
    private User user;

    private List<User> friends;

    public UserFriends()
    {

    }

    public UserFriends(User user, List<User> friends)
    {
        this.user = user;
        this.friends = friends;
    }

    public User getUser() {
        return user;
    }

    public List<User> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user).append(" has friends: \n");
        for (var item : friends)
        {
            stringBuilder.append(item).append('\n');
        }

        return stringBuilder.toString();
    }
}
