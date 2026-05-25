package coffeeVendingMachine.practice1.states;

import coffeeVendingMachine.practice1.CoffeeVendingMachine;
import coffeeVendingMachine.practice1.CoffeeDecorators.*;;

public class SelectingState implements VendingMachineState {
    public void selectItem(CoffeeVendingMachine machine, Coffee coffee) {
        System.err.println("Coffee already selected");
    }

    public void insertMoney(CoffeeVendingMachine machine, int amount) {
        machine.setMoney(machine.getAmount()+amount);
        System.err.println("Money inserted: " + amount + ", Total: "+machine.getAmount());
        if(machine.getAmount() >= machine.getSelectedCoffee().getPrice()) {
            System.err.println("Dispensing in a minute");
            machine.setState(new PaidState());
        }
    }

    public void dispenseCoffee(CoffeeVendingMachine machine) {
        System.err.println("Insufficient funds. Please insert more money. Required: " + 
            machine.getSelectedCoffee().getPrice() + ", Inserted: " + machine.getAmount());
    }

    public void cancel(CoffeeVendingMachine machine) {
        System.err.println("Transaction cancelled, refunding: " + machine.getAmount());
        machine.reset();
        machine.setState(new ReadyState());
    }
}
