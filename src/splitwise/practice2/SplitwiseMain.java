package splitwise.practice2;

import splitwise.practice2.entities.*;
import splitwise.practice2.strategy.*;
import java.util.*;

public class SplitwiseMain {
    public static void main(String[] args) {
        SplitwiseSvc svc = new SplitwiseSvc();

        // Create 4 users
        User u1 = svc.addUsers("u1", "User 1");
        User u2 = svc.addUsers("u2", "User 2");
        User u3 = svc.addUsers("u3", "User 3");
        User u4 = svc.addUsers("u4", "User 4");

        // Create 2 groups
        Group g1 = svc.addGroup("Group 1", Arrays.asList(u1, u2, u3));
        Group g2 = svc.addGroup("Group 2", Arrays.asList(u1, u2, u4));

        System.err.println("=== SCENARIO 1: Equal Split in Group 1 ===");
        // u1 pays 300 for u1, u2, u3 (Each owes 100)
        Expense.ExpenseBuilder b1 = new Expense.ExpenseBuilder();
        b1.setExpenseId("exp1");
        b1.setExpenseName("G1 Dinner");
        b1.setAmount(300.0);
        b1.setPaidBy(u1);
        b1.setUsers(Arrays.asList(u1, u2, u3));
        b1.setStrategy(new EqualSplitStrategy());
        svc.createExpense(b1.build());

        System.err.println("=== SCENARIO 2: Exact Split in Group 2 ===");
        // u4 pays 500 for u1, u2, u4 (u1 owes 100, u2 owes 200, u4 owes 200)
        Expense.ExpenseBuilder b2 = new Expense.ExpenseBuilder();
        b2.setExpenseId("exp2");
        b2.setExpenseName("G2 Movie");
        b2.setAmount(500.0);
        b2.setPaidBy(u4);
        b2.setUsers(Arrays.asList(u1, u2, u4));
        b2.setValues(Arrays.asList(100.0, 200.0, 200.0));
        b2.setStrategy(new ExactSplitStrategy());
        svc.createExpense(b2.build());

        System.err.println("=== SCENARIO 3: Percent Split across users ===");
        // u2 pays 1000 for u1, u3, u4 (u1 owes 200 (20%), u3 owes 300 (30%), u4 owes 500 (50%))
        Expense.ExpenseBuilder b3 = new Expense.ExpenseBuilder();
        b3.setExpenseId("exp3");
        b3.setExpenseName("Trip tickets");
        b3.setAmount(1000.0);
        b3.setPaidBy(u2);
        b3.setUsers(Arrays.asList(u1, u3, u4));
        b3.setValues(Arrays.asList(20.0, 30.0, 50.0));
        b3.setStrategy(new PercentSplitStrategy());
        svc.createExpense(b3.build());

        System.err.println("\n=== FINAL SETTLEMENTS ===");
        svc.simplifyDebts(u1, u2);
        svc.simplifyDebts(u1, u3);
        svc.simplifyDebts(u1, u4);
        svc.simplifyDebts(u2, u3);
        svc.simplifyDebts(u2, u4);
        svc.simplifyDebts(u3, u4);
    }
}
