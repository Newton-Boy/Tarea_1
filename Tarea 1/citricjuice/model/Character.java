package com.github.cc3002.citricjuice.model;

import java.util.Random;

public abstract class Character {
    private final Random random;
    private final String name;
    private final int maxHP;
    private final int atk;
    private final int def;
    private final int evd;
    private int stars;
    private int currentHP;
    /**
     * Creates a new character.
     *
     * @param name
     *     the character's name.
     * @param hp
     *     the initial (and max) hit points of the character.
     * @param atk
     *     the base damage the character does.
     * @param def
     *     the base defense of the character.
     * @param evd
     *     the base evasion of the character.
     */

    public Character(final String name, final int hp, final int atk, final int def,
                     final int evd) {
        this.name = name;
        this.maxHP = currentHP = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        random = new Random();
    }

    /**
     * Increases this character's star count by an amount.
     */
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }

    /**
     * Returns this character's star count.
     */

    public int getStars() {
        return stars;
    }
    /**
     * Set's the seed for this character's random number generator.
     * <p>
     * The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */

    public void setSeed(final long seed) {
        random.setSeed(seed);
    }
    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }
    /**
     * Returns the character's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the character's max hit points.
     */
    public int getMaxHP() {
        return maxHP;
    }
    /**
     * Returns the current character's attack points.
     */
    public int getAtk() {
        return atk;
    }
    /**
     * Returns the current character's defense points.
     */
    public int getDef() {
        return def;
    }
    /**
     * Returns the current character's evasion points.
     */
    public int getEvd() {
        return evd;
    }
    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHP() {
        return currentHP;
    }
    /**
     * Sets the current character's hit points.
     * <p>
     * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
     */
    public void setCurrentHP(final int newHP) {

        this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
    }
    /**
     * Set the damage that it's taken by the character's opponent, while
     * defending, in the máx value between 1 and the rolls difference.
     * @param attack
     *    the attack roll of the character.
     * @param defense
     *    the defense roll of the opponent.
     */

    public int defenseAction(int attack, int defense) {
        return Math.max(1,attack - defense);
    }
    /**
     * Set the damage that it's taken by the character's opponent, while
     * evading, on 0 or the attack roll result.
     * @param attack
     *    the attack roll of the character.
     * @param evade
     *    the evade roll of the opponent.
     */
    public int evadeAction(int attack, int evade) {
        int damage = attack - evade;
        if (damage < 0) {
            return 0;
        }
        else {
            return attack;
        }
    }
    /**
     * Make the character decide randomly if defending an attack
     * or evading it by rolling a dice and choosing odd or even
     */

    public String defenseOrEvade() {
        if (this.roll()%2==0) {
            return "Defense";
        }
        else {
            return "Evade";
        }
    }
    /**
     * Make an attack to a character's opponent and
     * deal damage depending on his decision in defending or
     * evading the attack, and in the rolls results.
     * @param opponent
     *     the target of the attack.
     * @param defenseOrEvade
     *     the reaction taken by the opponent in front of the attack.
     */
    public void attackCharacter(Character opponent, String defenseOrEvade) {
        int attack = roll() + this.getAtk();
        if (defenseOrEvade.equals("Defense")) {
            int defense = roll() + opponent.getDef();
            opponent.setCurrentHP(opponent.getCurrentHP()
                    - opponent.defenseAction(attack, defense));
        }
        if (defenseOrEvade.equals("Evade")) {
            int evade = roll() + opponent.getEvd();
            opponent.setCurrentHP(opponent.getCurrentHP()
                    - opponent.evadeAction(attack,evade));
        }
    }

    /**
     * Returns a copy of this character.
     */
    public abstract Character copy();

   // public void startBattle(Character opponent) {
   //     String defenseOrEvade = opponent.defenseOrEvade();
   //     this.attackCharacter(opponent, defenseOrEvade);
   //     if (opponent.getCurrentHP() == 0) {
   //         System.out.println(this.getName() + "wins to" + opponent.getName());
   //         this.winBattle(opponent);
   //     }
   // }

    //public void battle(Character opponent) {
    //    this.startBattle(opponent);
    //    if (opponent.getCurrentHP()>0) {
    //       opponent.startBattle(this);
    //    }
    //}
    /**
     * An abstraction of equality between this character
     * and a object.
     * @param o
     *    the object to compare with.
     */
    public abstract boolean equals(final Object o);

    /**
     * Reduces this player's star count by a given amount.
     * <p>
     * The star count will must always be greater or equal to 0
     */
    public void reduceStarsBy(final int amount) {
        stars = Math.max(0, stars - amount);
    }
    /**
     * An abstraction of win a battle and take the opponent's
     * stars.
     */
    public abstract void winStars(Character opponent);
}