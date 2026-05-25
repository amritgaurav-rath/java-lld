package coffeeVendingMachine.practice1.states;

import coffeeVendingMachine.practice1.CoffeeVendingMachine;
import coffeeVendingMachine.practice1.CoffeeDecorators.*;;

public interface VendingMachineState {
    void selectItem(CoffeeVendingMachine machine, Coffee coffee);
    void insertMoney(CoffeeVendingMachine machine, int amount);
    void dispenseCoffee(CoffeeVendingMachine machine);
    void cancel(CoffeeVendingMachine machine);
}
