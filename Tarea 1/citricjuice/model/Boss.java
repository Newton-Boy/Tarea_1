package com.github.cc3002.citricjuice.model;

public class Boss extends Character {
    /**
     * Creates an new boss character.
     * @param name
     *    Boss's name.
     * @param hp
     *    Boss's initial (and max) hit points.
     * @param atk
     *    The base damage the boss does.
     * @param def
     *    The base defense of the boss.
     * @param evd
     *    The base evasion of the boss.
     */
    public Boss(final String name, final int hp, final int atk, final int def,
                final int evd){
        super(name, hp, atk, def, evd);
    }
    /**
     * Compare if an object o is a boss equal
     * to this boss
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Boss)) {
            return false;
        }
        final Boss unit = (Boss) o;
        return getMaxHP() == unit.getMaxHP() &&
                getAtk() == unit.getAtk() &&
                getDef() == unit.getDef() &&
                getEvd() == unit.getEvd() &&
                getStars() == unit.getStars() &&
                getCurrentHP() == unit.getCurrentHP() &&
                getName().equals(unit.getName());
    }
    /**
     * Returns a copy of a boss.
     */
    @Override
    public Boss copy(){
        return new Boss(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
    }
    /**
     * Win a battle to a character (a player for the purposes game)
     * makes the character gives the half of it's stars to
     * this boss.
     */
    @Override
    public void winStars(Character opponent) {
        int stars = (opponent.getStars() + 1)/2;
        opponent.reduceStarsBy(stars);
        this.increaseStarsBy(stars);
    }
}
