package adapter.ducks;

import adapter.ducks.behavior.FlyNoWay;
import adapter.ducks.behavior.Quack;

public class ModelDuck extends Duck{
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    public void display() {
        System.out.println("I'm Model duck");
    }
}
