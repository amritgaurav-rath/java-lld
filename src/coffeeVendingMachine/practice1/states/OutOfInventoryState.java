package coffeeVendingMachine.practice1.states;

import coffeeVendingMachine.practice1.CoffeeVendingMachine;
import coffeeVendingMachine.practice1.CoffeeDecorators.*;;

public class OutOfInventoryState implements VendingMachineState {
    public void selectItem(CoffeeVendingMachine machine, Coffee coffee){
        System.err.println("Out of Stock!");
    }
    public void insertMoney(CoffeeVendingMachine machine, int amount) {
        System.err.println("Out of Stock!");
    }
    public void dispenseCoffee(CoffeeVendingMachine machine) {
        System.err.println("Out of Stock!");
    }
    public void cancel(CoffeeVendingMachine machine) {
        System.err.println("Refunding: " + machine.getAmount());

        machine.reset();
        machine.setState(new ReadyState());
    }
}
