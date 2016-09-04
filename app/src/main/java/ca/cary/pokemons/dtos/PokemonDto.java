package ca.cary.pokemons.dtos;

import java.util.Date;

import ca.cary.pokemons.objects.Pokemon;

/**
 * Created by jiayaohuang on 2016-09-01.
 */
public class PokemonDto {

    private Pokemon pokemon;
    private double latitude;
    private double longitude;
    private Date disappearDate;
    private String iconPath;
    private byte[] icon;

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

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

}
