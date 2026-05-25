package splitwise.practice2.entities;

import java.util.*;

public class Group {
    String groupId;
    String groupName;
    List<User> members = new ArrayList<>();

    public Group(String groupName, List<User> users) {
        this.groupId = UUID.randomUUID().toString();
        this.groupName = groupName;
        this.members = users;
    }
}
