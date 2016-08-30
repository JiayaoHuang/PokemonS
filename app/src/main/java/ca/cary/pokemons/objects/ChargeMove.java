package ca.cary.pokemons.objects;

/**
 * Created by jiayaohuang on 2016-08-18.
 */
public class ChargeMove {

    private String name;
    private String type;
    private int damage;
    private int charge;

    public ChargeMove(String name) {
        this.name = name;
        this.type = null;
        this.damage = 0;
        this.charge = 0;
    }

    public ChargeMove(String name, String type, int damage, int charge) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.charge = charge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

}
