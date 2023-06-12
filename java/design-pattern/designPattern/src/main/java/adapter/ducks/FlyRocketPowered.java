package adapter.ducks;

import adapter.ducks.behavior.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("로켓 추진으로 날아간다.");
    }
}
