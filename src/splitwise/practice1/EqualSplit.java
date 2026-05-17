package splitwise.practice1;

import java.util.*;

public class EqualSplit implements SplitStrategy {
    public List<Split> SplitExpense(Double SplitAmount, User payer, List<User> participants, List<Double> Values) {
        List<Split> splits = new ArrayList<>();
        int noOfParticipants = participants.size();
        for(int i=0;i<noOfParticipants;i++) {
            Split s = new Split(participants.get(i), (1.0*SplitAmount)/(noOfParticipants*1.0));
            splits.add(s);
        }
        return splits;
    };
}
