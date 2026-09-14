package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilitaire {
    public static double calculerTotal(double vol, double hebergement, double restaurant) {
        return vol + hebergement + restaurant;
    }

    public static double determinerTaux(double total) {
        if (total > 2000) {
            return 0.10;
        } else if (total >= 1000) {
            return 0.05;
        } else {
            return 0.015;
        }
    }

    public static double calculerContribution(double total) {
        return total * determinerTaux(total);
    }

    public static String dateCourante() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String formaterMontant(double montant) {
        return String.format(Locale.getDefault(), "%.2f $", montant);
    }

    public static String formaterTaux(double taux) {
        return String.format(Locale.getDefault(), "%.1f %%", taux * 100);
    }
}
