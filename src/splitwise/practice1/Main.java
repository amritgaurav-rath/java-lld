package splitwise.practice1;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        List<Group> groups = new ArrayList<>();

        User u1 = new User("u1");
        users.add(u1);
        User u2 = new User("u2");
        users.add(u2);
        User u3 = new User("u3");
        users.add(u3);

        Group g = new Group("Trip", users);
        groups.add(g);

        SplitwiseController s = new SplitwiseController(users, groups);

        System.out.println("--- Day 1: Equal Split ---");
        // u1 pays 300, split equally among u1, u2, u3
        s.createExpense("E1", 300.0, u1, users, null, new EqualSplit());
        s.showBalances();

        System.out.println("\n--- Day 2: Exact Split ---");
        // u2 pays 100, u1 owes 40, u3 owes 60
        List<Double> exactAmounts = Arrays.asList(40.0, 0.0, 60.0);
        s.createExpense("E2", 100.0, u2, users, exactAmounts, new ExactSplit());
        s.showBalances();

        System.out.println("\n--- Day 3: Percentage Split ---");
        // u3 pays 200, u1 owes 20%, u2 owes 30%, u3 owes 50%
        List<Double> percentages = Arrays.asList(20.0, 30.0, 50.0);
        s.createExpense("E3", 200.0, u3, users, percentages, new PercentageSplit());
        s.showBalances();

        System.out.println("\n--- Final Balances for u1 ---");
        s.showBalances("u1");

        System.out.println("\n--- Settle Up ---");
        // u1 owes u3 60, u2 owes u3 0 (calculated from trace)
        // u2 owes u1 60
        s.settleUp("u1", "u3", 60.0);
        s.settleUp("u2", "u1", 60.0);
        
        System.out.println("\n--- Final Balances After Settle Up ---");
        s.showBalances();
    }
}
