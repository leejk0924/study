package strategy.designPuzzle.behavior;

public class SwordBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("검으로 공격!");
    }
}
