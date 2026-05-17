package splitwise.practice1;

import java.util.*;

public class Group {
    String groupName;
    List<User> user;

    public Group(String groupName, List<User> user) {
        this.groupName = groupName;
        this.user = user;
    }

    public List<User> getUser() {
        return this.user;
    }

    public void addUser(User user) {
        this.user.add(user);
    }

    public String getGroupName() {
        return this.groupName;
    }
}
