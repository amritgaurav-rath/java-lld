package splitwise.practice2.strategy;

import java.util.*;
import splitwise.practice2.entities.*;

public class PercentSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> createSplits(Double totalAmount, List<User> users, List<Double> values) {
        if(users.size() != values.size()) {
            throw new IllegalArgumentException("Size of users has to be equal to values");
        }

        Double checkAmount = 0.0;
        for(Double v:values) {
            checkAmount = checkAmount + v;
        }

        if(Math.abs(checkAmount-100) > 0.01) {
            throw new IllegalArgumentException("Sum of list of values must be equal to 100 percent");
        }

        List<Split> splits = new ArrayList<>();
        
        for(int i=0;i<users.size();i++) {
            splits.add(new Split(users.get(i), (totalAmount*values.get(i))/100.0));
        }
        return splits;
    }
}
