package strategy.designPuzzle.behavior;

public class BowAndArrowBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("활과 화살로 공격!");
    }
}
