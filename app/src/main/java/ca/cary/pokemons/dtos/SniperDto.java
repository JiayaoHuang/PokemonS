package ca.cary.pokemons.dtos;

import java.util.Date;

import ca.cary.pokemons.objects.Pokemon;

/**
 * Created by jiayaohuang on 2016-08-18.
 */
public class SniperDto {

    private int sniperId;
    private Pokemon pokemon;
    private double latitude;
    private double longitude;
    private Date disappearDate;
    private String icon;

    public int getSniperId() {
        return sniperId;
    }

    public void setSniperId(int sniperId) {
        this.sniperId = sniperId;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getDisappearDate() {
        return disappearDate;
    }

    public void setDisappearDate(Date disappearDate) {
        this.disappearDate = disappearDate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
