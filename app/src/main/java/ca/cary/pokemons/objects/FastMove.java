package ca.cary.pokemons.objects;

/**
 * Created by jiayaohuang on 2016-08-18.
 */
public class FastMove {

    private String name;
    private String type;
    private int damage;
    private float speed;

    public FastMove(String name) {
        this.name = name;
        this.type = null;
        this.damage = 0;
        this.speed = 0;
    }

    public FastMove(String name, String type, int damage, float speed) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.speed = speed;
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
