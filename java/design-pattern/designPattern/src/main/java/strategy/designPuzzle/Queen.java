package strategy.designPuzzle;

import strategy.designPuzzle.behavior.BowAndArrowBehavior;

public class Queen extends Character{
    @Override
    void fight() {
        System.out.println("여왕이 싸운다.");
    }

    public Queen() {
        weaponBehavior = new BowAndArrowBehavior();
    }
}
