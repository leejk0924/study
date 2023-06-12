package strategy.ducks;

import strategy.ducks.behavior.FlyWithWings;
import strategy.ducks.behavior.Quack;

public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    void display() {
        System.out.println("I'm MallardDuck");
    }
}
