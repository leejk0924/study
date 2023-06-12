package strategy.ducks.behavior;

public class Squak implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("Squack!");
    }
}
