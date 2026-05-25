package splitwise.practice2.strategy;

import java.util.*;
import splitwise.practice2.entities.*;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> createSplits(Double totalAmount, List<User> users, List<Double> values) {
        List<Split> splits = new ArrayList<>();

        int noOfUsers = users.size();

        Double individualAmount = totalAmount / noOfUsers;
        
        for(User u:users) {
            splits.add(new Split(u, individualAmount));
        }
        return splits;
    }
}
