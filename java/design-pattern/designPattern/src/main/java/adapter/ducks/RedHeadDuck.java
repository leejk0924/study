package adapter.ducks;

import adapter.ducks.behavior.FlyWithWings;
import adapter.ducks.behavior.Quack;

public class RedHeadDuck extends Duck{
    public RedHeadDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }
}
