package strategy.ducks;

import strategy.ducks.behavior.FlyWithWings;
import strategy.ducks.behavior.Quack;

public class RedHeadDuck extends Duck{
    public RedHeadDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    void display() {

    }
}
