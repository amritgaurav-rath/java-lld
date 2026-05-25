package coffeeVendingMachine.practice1.states;

import coffeeVendingMachine.practice1.CoffeeVendingMachine;
import coffeeVendingMachine.practice1.CoffeeDecorators.*;import coffeeVendingMachine.practice1.Inventory;
import coffeeVendingMachine.practice1.enums.Ingredients;
;

public class PaidState implements VendingMachineState {
    public void selectItem(CoffeeVendingMachine machine, Coffee coffee) {
        System.err.println("Dispensing Coffee, please wait!");
    }
    public void insertMoney(CoffeeVendingMachine machine, int amount) {
        System.err.println("Dispensing Coffee, please wait!");
    }
    public void dispenseCoffee(CoffeeVendingMachine machine) {
        Inventory inv = Inventory.getInstance();

        if(machine.getAmount() < machine.getSelectedCoffee().getPrice()) {
            System.err.println("Insufficient Funds");
            this.cancel(machine);
        }

        if(!inv.hasIngredients(machine.getSelectedCoffee().getRecipe())) {
            System.err.println("Out of Stock");
            machine.setState(new OutOfInventoryState());
            machine.getState().cancel(machine);
            return;
        }

        inv.deductStock(machine.getSelectedCoffee().getRecipe());
        machine.getSelectedCoffee().prepare();

        int change = machine.getAmount() - machine.getSelectedCoffee().getPrice();
        if (change > 0) {
            System.err.println("Returning Change: "+ change);
        }

        machine.reset();
        machine.setState(new ReadyState());
    }
    public void cancel(CoffeeVendingMachine machine) {
        new SelectingState().cancel(machine);
    }
}
