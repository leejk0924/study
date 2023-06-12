package strategy.designPuzzle.behavior;

public class KnifeBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("칼로 공격!");
    }
}
