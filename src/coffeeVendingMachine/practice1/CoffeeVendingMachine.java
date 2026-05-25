package coffeeVendingMachine.practice1;

import coffeeVendingMachine.practice1.states.*;

import java.util.List;

import coffeeVendingMachine.practice1.CFactory.CoffeeFactory;
import coffeeVendingMachine.practice1.CoffeeDecorators.*;
import coffeeVendingMachine.practice1.enums.*;

public class CoffeeVendingMachine {
    private static CoffeeVendingMachine INSTANCE = new CoffeeVendingMachine();
    private VendingMachineState state;
    private int moneyInserted;
    private Coffee selectedCoffee;

    private CoffeeVendingMachine() {
        this.state = new ReadyState();
        this.moneyInserted = 0;
    }

    public static CoffeeVendingMachine getInstance() {
        return INSTANCE;
    }

    public int getAmount() {
        return this.moneyInserted;
    }

    public void reset() {
        this.selectedCoffee = null;
        this.moneyInserted = 0;
    }

    public void setState(VendingMachineState vState) {
        this.state = vState;
    }
    public void setSelectedCoffee(Coffee coffee) {
        this.selectedCoffee = coffee;
    }

    public Coffee getSelectedCoffee() {
        return this.selectedCoffee;
    }
    public VendingMachineState getState() {
        return this.state;
    }
    public void setMoney(int quantity) {
        this.moneyInserted = quantity;
    }

    public void selectCoffee(CoffeeType type, List<Toppings> toppings) {
        // 1. Create the base coffee using the factory
        Coffee coffee = CoffeeFactory.createCoffee(type);

        // 2. Wrap it with decorators
        for (Toppings topping : toppings) {
            switch (topping) {
                case Toppings.ExtraSugar:
                    coffee = new ExtraSugarDecorator(coffee);
                    break;
                case Toppings.Caramel:
                    coffee = new CaramelDecorator(coffee);
                    break;
            }
        }
        // Let the state handle the rest
        this.state.selectItem(this, coffee);
    }

    public void insertMoney(int amount) { state.insertMoney(this, amount); }
    public void dispenseCoffee() { state.dispenseCoffee(this); }
    public void cancel() { state.cancel(this); }

    public void setMoneyInserted(int moneyInserted) { this.moneyInserted = moneyInserted; }
}
