package com.github.cc3002.citricjuice.model;

public class WildUnit extends Character {
    /**
     * Creates an new wild unit character.
     * @param name
     *    Wild unit's name.
     * @param hp
     *    Wild unit's initial (and max) hit points.
     * @param atk
     *    The base damage the wild unit does.
     * @param def
     *    The base defense of the wild unit.
     * @param evd
     *    The base evasion of the wild unit.
     */
    public WildUnit(final String name,final int hp,final int atk, final int def,
                    final int evd){
        super(name, hp, atk, def, evd);
    }

    /**
     * Compare if an object o is a wild unit equal
     * to this wild unit.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WildUnit)) {
            return false;
        }
        final WildUnit unit = (WildUnit) o;
        return getMaxHP() == unit.getMaxHP() &&
                getAtk() == unit.getAtk() &&
                getDef() == unit.getDef() &&
                getEvd() == unit.getEvd() &&
                getStars() == unit.getStars() &&
                getCurrentHP() == unit.getCurrentHP() &&
                getName().equals(unit.getName());
    }
    /**
     * Returns a copy of a wild unit.
     */
    @Override
    public WildUnit copy(){
        return new WildUnit(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
    }
    /**
     * Win a battle to a character (a player for the purposes game)
     * makes the character gives the half of it's stars to
     * this wild unit.
     */
    @Override
    public void winStars(Character opponent) {
        int stars = (opponent.getStars() + 1)/2;
        opponent.reduceStarsBy(stars);
        this.increaseStarsBy(stars);
        }
    }
