package splitwise.practice2.strategy;

import java.util.*;
import splitwise.practice2.entities.*;

public interface SplitStrategy {
    List<Split> createSplits(Double totalAmount, List<User> users, List<Double> values);
}
