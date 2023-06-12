package strategy.designPuzzle.behavior;

public class AxeBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("도끼로 공격!");
    }
}
