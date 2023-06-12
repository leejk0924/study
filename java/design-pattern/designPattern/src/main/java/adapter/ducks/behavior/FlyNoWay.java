package adapter.ducks.behavior;

public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("No Fly");
    }
}
