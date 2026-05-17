package splitwise.practice1;
import java.util.List;
import java.util.Map;

public class SplitwiseController {
    List<User> users;
    List<Group> groups;

    public SplitwiseController(List<User> users, List<Group> groups) {
        this.users = users;
        this.groups = groups;
    }

    public User addUser(String name) {
        User u = new User(name);
        users.add(u);

        return u;
    }

    public Group addGroup(String name,List<User> users) {
        Group g = new Group(name, users);
        groups.add(g);
        return g;
    }

    public void createExpense(String expenseId, Double amount, User paidBy, List<User> paidTo, List<Double> distribution, SplitStrategy splitStrategy) {
        Expense e = new Expense(expenseId, amount, paidBy, paidTo, distribution, splitStrategy);
        for (Split split : e.getSplits()) {
            User paidToUser = split.getUser();
            Double splitAmount = split.getAmount();

            if (paidBy.equals(paidToUser)) continue;

            // Update balance for paidBy (Money owed to paidBy)
            Map<User, Double> paidByBalances = paidBy.getBalanceSheet();
            paidByBalances.put(paidToUser, paidByBalances.getOrDefault(paidToUser, 0.0) + splitAmount);

            // Update balance for paidToUser (Money paidToUser owes)
            Map<User, Double> paidToBalances = paidToUser.getBalanceSheet();
            paidToBalances.put(paidBy, paidToBalances.getOrDefault(paidBy, 0.0) - splitAmount);
        }
    }

    public void settleUp(String payerName, String payeeName, Double amount) {
        User payer = findUser(payerName);
        User payee = findUser(payeeName);
        if (payer != null && payee != null) {
            Map<User, Double> payerBalances = payer.getBalanceSheet();
            payerBalances.put(payee, payerBalances.getOrDefault(payee, 0.0) + amount);

            Map<User, Double> payeeBalances = payee.getBalanceSheet();
            payeeBalances.put(payer, payeeBalances.getOrDefault(payer, 0.0) - amount);
            System.out.println(payerName + " settled " + amount + " with " + payeeName);
        }
    }

    public void showBalances() {
        for (User u : users) {
             showBalancesHelper(u);
        }
    }

    public void showBalances(String userName) {
        User user = findUser(userName);
        if (user != null) {
            showBalancesHelper(user);
        }
    }

    private void showBalancesHelper(User u) {
        for (User otherUser : u.getBalanceSheet().keySet()) {
            Double balance = u.getBalanceSheet().get(otherUser);
            if (balance > 0) {
                System.out.println(otherUser.getUserName() + " owes " + u.getUserName() + ": " + balance);
            }
        }
    }

    private User findUser(String userName) {
        for (User u : users) {
            if (u.getUserName().equals(userName)) return u;
        }
        return null;
    }
}
