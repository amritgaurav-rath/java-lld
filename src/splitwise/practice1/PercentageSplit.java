package splitwise.practice1;

import java.util.*;

public class PercentageSplit implements SplitStrategy {
    public List<Split> SplitExpense(Double splitAmount, User payer, List<User> participants, List<Double> amounts) {
        if(participants.size() != amounts.size()) {
            throw new IllegalArgumentException("participants size must be equal to amounts size");
        }

        Double sum=0.0;
        for(Double amount:amounts) {
            sum = sum + amount;
        }
        if(Math.abs(sum-100.0)>0.01) {
            throw new IllegalArgumentException("sum of split amount must equal split amount.");
        }

        List<Split> splits = new ArrayList<>();
        for(int i=0;i<participants.size();i++) {
            Split s = new Split(participants.get(i), ((splitAmount*amounts.get(i))/100));
            splits.add(s);
        }

        return splits;
    }
}
