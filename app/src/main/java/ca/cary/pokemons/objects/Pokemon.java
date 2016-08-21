package ca.cary.pokemons.objects;

/**
 * Created by jiayaohuang on 2016-08-18.
 */
public class Pokemon {

    private String name;
    private int combatPower;
    private int healthPoints;
    private String type;
    private float weight;
    private float height;
    private int powerUpStardust;
    private FastMove fastMove;
    private ChargeMove chargeMove;
    private int level;
    private int attack;
    private int attackBonus;
    private int defense;
    private int defenseBonus;
    private int stamina;
    private int staminaBonus;
    private float perfection;

    public Pokemon(String name) {
        this.name = name;
        this.combatPower = 0;
        this.healthPoints = 0;
        this.type = null;
        this.weight = 0;
        this.height = 0;
        this.powerUpStardust = 0;
        this.fastMove = null;
        this.chargeMove = null;
        this.level = 0;
        this.attack = 0;
        this.attackBonus = 0;
        this.defense = 0;
        this.defenseBonus = 0;
        this.stamina = 0;
        this.staminaBonus = 0;
        this.perfection = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCombatPower() {
        return combatPower;
    }

    public void setCombatPower(int combatPower) {
        this.combatPower = combatPower;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getPowerUpStardust() {
        return powerUpStardust;
    }

    public void setPowerUpStardust(int powerUpStardust) {
        this.powerUpStardust = powerUpStardust;
    }

    public FastMove getFastMove() {
        return fastMove;
    }

    public void setFastMove(FastMove fastMove) {
        this.fastMove = fastMove;
    }

    public ChargeMove getChargeMove() {
        return chargeMove;
    }

    public void setChargeMove(ChargeMove chargeMove) {
        this.chargeMove = chargeMove;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public void setDefenseBonus(int defenseBonus) {
        this.defenseBonus = defenseBonus;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStaminaBonus() {
        return staminaBonus;
    }

    public void setStaminaBonus(int staminaBonus) {
        this.staminaBonus = staminaBonus;
    }

    public float getPerfection() {
        return perfection;
    }

    public void setPerfection(float perfection) {
        this.perfection = perfection;
    }

}
