package splitwise.practice2;

import java.util.*;
import splitwise.practice2.entities.*;

public class SplitwiseSvc {
    List<User> users;
    List<Group> groups;
    List<Transaction> txns;

    public SplitwiseSvc() {
        this.users = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.txns = new ArrayList<>();
    }

    public User addUsers(String userId, String userName) {
        User u = new User(userId, userName);
        users.add(u);
        return u;
    }

    public Group addGroup(String groupName, List<User> members) {
        Group g = new Group(groupName, users);

        groups.add(g);
        return g;
    }

    public void createExpense(Expense exp) {
        User paidUser = exp.getPaidUser();

        for(Split s:exp.getSplits()) {
            if(paidUser.equals(s.getUser()))
            continue;

            s.getUser().getBalances().adjustBalance(paidUser, -1.0*s.getAmount());
            paidUser.getBalances().adjustBalance(s.getUser(), s.getAmount());
        }
        System.err.println("Expense has been splitted, paid by: "+paidUser);
    }

    public void simplifyDebts(User u1, User u2) {
        Double amt = u1.getBalances().getBalanceForUser(u2);

        if(Math.abs(amt) <= 0.01) {
            System.err.println("Already Settled");
        } else if(amt < 0) {
            Transaction t1 = new Transaction(u1, u2, -1.0*amt);
            this.txns.add(t1);

            System.err.println("User "+u1.getName()+ " pays User "+ u2.getName() + " Amount: "+(-1.0*amt));
        } else {
            Transaction t1 = new Transaction(u2, u1, amt);
            this.txns.add(t1);

            System.err.println("User "+u2.getName()+ " pays User "+ u1.getName() + " Amount: "+amt);
        }
    }
}
