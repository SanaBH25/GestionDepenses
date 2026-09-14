package modele;

import utils.Utilitaire;

public class Depense {

    private int id;
    private double vol;
    private  double hebergement;
    private double restaurant;
    private double total;
    private double contribution;
    private String dateSaisie;

    public Depense() {
    }

    public Depense(double vol,double hebergement,double restaurant ) {
        this.vol = vol;
        this.hebergement = hebergement;
        this.restaurant = restaurant;
        this.total = Utilitaire.calculerTotal(vol, hebergement, restaurant);
        this.contribution = Utilitaire.calculerContribution(this.total);
        this.dateSaisie = Utilitaire.dateCourante();
    }

    public Depense(int id, double vol, double hebergement, double restaurant, double total, double contribution, String dateSaisie) {
        this.id = id;
        this.vol = vol;
        this.hebergement = hebergement;
        this.restaurant = restaurant;
        this.total = total;
        this.contribution = contribution;
        this.dateSaisie = dateSaisie;
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double contribution) {
        this.contribution = contribution;
    }

    public String getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(String dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public double getHebergement() {
        return hebergement;
    }

    public void setHebergement(double hebergement) {
        this.hebergement = hebergement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(double restaurant) {
        this.restaurant = restaurant;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    @Override
    public String toString() {
        return dateSaisie + " | Vol: " + String.format(java.util.Locale.getDefault(), "%.2f", vol) + 
               "$ | Héb: " + String.format(java.util.Locale.getDefault(), "%.2f", hebergement) + 
               "$ | Resto: " + String.format(java.util.Locale.getDefault(), "%.2f", restaurant) + 
               "$ -> Total: " + String.format(java.util.Locale.getDefault(), "%.2f", total) + 
               "$ (Contrib: " + String.format(java.util.Locale.getDefault(), "%.2f", contribution) + "$)";
    }
}
