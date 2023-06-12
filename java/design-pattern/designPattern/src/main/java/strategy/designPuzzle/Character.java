package strategy.designPuzzle;

import strategy.designPuzzle.behavior.WeaponBehavior;

public abstract class Character {
    WeaponBehavior weaponBehavior;
    abstract void fight();

    public void setWeaponBehavior(WeaponBehavior wb){
        weaponBehavior = wb;
    }

    public void attack() {
        String whoAmI = this.getClass().getSimpleName();
        System.out.print(whoAmI+"이(가) ");
        weaponBehavior.useWeapon();
    }
}