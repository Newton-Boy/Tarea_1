package com.github.cc3002.citricjuice.model;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public class Player extends Character {
  private int normaLevel;
  private int victoryPoint;

  /**
   * Creates a new player character with a norma level 1 and an
   * implicit 0 starting victory points
   * @param name
   *    the player's name.
   * @param hp
   *    the initial (and max) hit points of the player.
   * @param atk
   *    the base damage the player does.
   * @param def
   *    the base defense of the player.
   * @param evd
   *    the base evasion of the player.
   */
  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {
    super(name, hp, atk, def, evd);
    normaLevel = 1;
  }
  /**
   * Returns the current norma level
   */
  public int getNormaLevel() {
    return normaLevel;
  }
  /**
   * Performs a norma clear action;
   * the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    normaLevel++;
  }
  /**
   * Returns the current victory points*/
  public int getVictoryPoint() {
    return victoryPoint;
  }
  /**
   * Increases this player's  victory points by an amount.*/
  public void increaseVictoryPointsBy(int amount) {
    this.victoryPoint += amount;
  }
  /**
   * Compare if an object o is a player equal
   * to this player
   */
  @Override
  public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Player)) {
        return false;
      }
    final Player player = (Player) o;
    return getMaxHP() == player.getMaxHP() &&
           getAtk() == player.getAtk() &&
           getDef() == player.getDef() &&
           getEvd() == player.getEvd() &&
           getNormaLevel() == player.getNormaLevel() &&
            getVictoryPoint() == player.getVictoryPoint() &&
           getStars() == player.getStars() &&
           getCurrentHP() == player.getCurrentHP() &&
           getName().equals(player.getName());
  }
  /**
   * Returns a copy of a player
   */
  @Override
  public Player copy(){
      return new Player(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
  }
  /**
   * Increases the base damage of player by an amount.
   */
  public Player getBoostAtk(int amount) {
    return new Player(this.getName(), this.getMaxHP(),
            this.getAtk() + amount, this.getDef(), this.getEvd());
  }
  /**
   * Increases the base defense of player by an amount.
   */
  public Player getBoostDef(int amount) {
    return new Player(this.getName(), this.getMaxHP(),
            this.getAtk(), this.getDef() + amount, this.getEvd());
  }
  /**
   * Increases the base evasion of player by an amount.
   */
  public Player getBoostEvd(int amount) {
    return new Player(this.getName(), this.getMaxHP(),
            this.getAtk(), this.getDef(), this.getEvd() + amount);
  }
  /**
   * Whenever a player reduces the hit points of the opponent
   * to 0, take a amount of Stars and gain victory points.
   */
  public void winBattle(Character opponent) {
    this.winStars(opponent);
    this.winVictory(opponent);
  }
  /**
   * Assuming opponent's hit points = 0, this character
   * steal stars from the amount of the opponent depending on
   * Character type of himself and the opponent.
   * if this character is a Non-Player Character always takes half
   * of the opponent's stars.
   * if it's a Player, takes all the stars from Non-Player Characters
   * and half to another PLayer.
   */
  @Override
  public void winStars(Character opponent) {
    int stars = opponent.getStars() + 1;
    if (opponent instanceof Player) {
      opponent.reduceStarsBy(stars/2);
      this.increaseStarsBy(stars/2);
    }
    else {
      opponent.reduceStarsBy(stars-1);
      this.increaseStarsBy(stars-1);
    }
  }
  /**
   * Assuming opponent's hit points = 0, this player
   * increases his victory points depending on the opponent
   * character type.
   * if opponent is WildUnit gain 1 VP, 2 if is Player and
   * 3 if it's a Boss.
   */
  public void winVictory(Character opponent) {
    if (opponent instanceof Player) {
      this.increaseVictoryPointsBy(2);
    }
    if ( opponent instanceof WildUnit) {
      this.increaseVictoryPointsBy(1);
    }
    if ( opponent instanceof Boss) {
      this.increaseVictoryPointsBy(3);
    }
  }
}
