/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import java.util.Date;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class Complaint {
    private String ingevoerd;
    private Date datum;
    private int aantal;
    private String straatnaam;
    private String postcode;
    private String plaatsnaam;
    private String aardOverlast;
    private String subOverlast;
    private String subSubaardOverlast;
    private Boolean terugkoppeling;
    private double Latitude;
    private double Longitude;

    public Complaint(String ingevoerd, Date datum, int aantal, String straatnaam, String postcode, String plaatsnaam, String aardOverlast, String subOverlast, String subSubaardOverlast, Boolean terugkoppeling) {
        this.ingevoerd = ingevoerd;
        this.datum = datum;
        this.aantal = aantal;
        this.straatnaam = straatnaam;
        this.postcode = postcode;
        this.plaatsnaam = plaatsnaam;
        this.aardOverlast = aardOverlast;
        this.subOverlast = subOverlast;
        this.subSubaardOverlast = subSubaardOverlast;
        this.terugkoppeling = terugkoppeling;
    }

    public String getIngevoerd() {
        return ingevoerd;
    }

    public void setIngevoerd(String ingevoerd) {
        this.ingevoerd = ingevoerd;
    }

   
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPlaatsnaam() {
        return plaatsnaam;
    }

    public void setPlaatsnaam(String plaatsnaam) {
        this.plaatsnaam = plaatsnaam;
    }

    public String getAardOverlast() {
        return aardOverlast;
    }

    public void setAardOverlast(String aardOverlast) {
        this.aardOverlast = aardOverlast;
    }

    public String getSubOverlast() {
        return subOverlast;
    }

    public void setSubOverlast(String subOverlast) {
        this.subOverlast = subOverlast;
    }

    public String getSubSubaardOverlast() {
        return subSubaardOverlast;
    }

    public void setSubSubaardOverlast(String subSubaardOverlast) {
        this.subSubaardOverlast = subSubaardOverlast;
    }

    public Boolean getTerugkoppeling() {
        return terugkoppeling;
    }

    public void setTerugkoppeling(Boolean terugkoppeling) {
        this.terugkoppeling = terugkoppeling;
    }
    
    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double Longitude) {
        this.Longitude = Longitude;
    }
    
    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    @Override
    public String toString() {
        return "Klacht{" + "ingevoerd=" + ingevoerd + ", datum=" + datum + ", aantal=" + aantal + ", straatnaam=" + straatnaam + ", postcode=" + postcode + ", plaatsnaam=" + plaatsnaam + ", aardOverlast=" + aardOverlast + ", subOverlast=" + subOverlast + ", subSubaardOverlast=" + subSubaardOverlast + ", terugkoppeling=" + terugkoppeling + '}';
    }
    
}
