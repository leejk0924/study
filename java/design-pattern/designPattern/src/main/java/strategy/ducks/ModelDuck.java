package strategy.ducks;

import strategy.ducks.behavior.FlyNoWay;
import strategy.ducks.behavior.Quack;

public class ModelDuck extends Duck{
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    public void display() {
        System.out.println("I'm Model duck");
    }
}
