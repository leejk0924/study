package strategy.designPuzzle;

import strategy.designPuzzle.behavior.SwordBehavior;

public class King extends Character {
    @Override
    protected void fight() {
        System.out.println("왕이 싸운다.");
    }

    public King() {
        weaponBehavior = new SwordBehavior();
    }
}
