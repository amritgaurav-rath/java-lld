package coffeeVendingMachine.practice1.states;

import coffeeVendingMachine.practice1.CoffeeVendingMachine;
import coffeeVendingMachine.practice1.CoffeeDecorators.*;;

public class ReadyState implements VendingMachineState {
    public void selectItem(CoffeeVendingMachine machine, Coffee coffee) {
        machine.setSelectedCoffee(coffee);
        machine.setState(new SelectingState());

        System.err.println("Coffee is one the way");
    }

    public void insertMoney(CoffeeVendingMachine machine, int amount) {
        System.out.print("Select coffee first");
    }

    public void dispenseCoffee(CoffeeVendingMachine machine) {
        System.out.print("Select coffee first");
    }

    public void cancel(CoffeeVendingMachine machine) {
        System.err.println("Nothing to cancel");
    }
}
