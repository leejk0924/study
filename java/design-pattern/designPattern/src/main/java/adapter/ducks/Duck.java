package adapter.ducks;

import adapter.ducks.behavior.FlyBehavior;
import adapter.ducks.behavior.QuackBehavior;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    public void performFly() {
        flyBehavior.fly();
    }
    public void performQuack() {
        quackBehavior.quack();
    }
    public void swim() {
        System.out.println("모든 오리는 수영할 수 있다.");
    }
}
