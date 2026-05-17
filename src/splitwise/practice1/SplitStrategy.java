package splitwise.practice1;
import java.util.*;

public interface SplitStrategy {
    List<Split> SplitExpense(Double SplitAmount, User payer, List<User> participants, List<Double> Values);
}
