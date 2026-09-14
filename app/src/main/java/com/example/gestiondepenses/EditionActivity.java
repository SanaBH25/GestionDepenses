package com.example.gestiondepenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import utils.Utilitaire;

public class EditionActivity extends AppCompatActivity {
    private EditText txtVol, txtHebergement, txtRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edition);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setWidgets();
    }

    private void setWidgets() {
        txtVol         = findViewById(R.id.txtVol);
        txtHebergement = findViewById(R.id.txtHebergement);
        txtRestaurant  = findViewById(R.id.txtRestaurant);
        txtVol.requestFocus();
    }

    public void onRetour(View view) {
        this.finish();
    }

    public void onCalculer(View view) {
        Double vol         = lireMontant(txtVol);
        Double hebergement = lireMontant(txtHebergement);
        Double restaurant  = lireMontant(txtRestaurant);

        // un champ invalide : lireMontant a déjà posé l'erreur et le focus
        if (vol == null || hebergement == null || restaurant == null) {
            return;
        }

        if (Utilitaire.calculerTotal(vol, hebergement, restaurant) <= 0) {
            txtVol.setError(getString(R.string.err_total_nul));
            txtVol.requestFocus();
            return;
        }

        Intent intent = new Intent(EditionActivity.this, ResultatActivity.class);
        intent.putExtra(ResultatActivity.EXTRA_VOL, vol.doubleValue());
        intent.putExtra(ResultatActivity.EXTRA_HEBERGEMENT, hebergement.doubleValue());
        intent.putExtra(ResultatActivity.EXTRA_RESTAURANT, restaurant.doubleValue());
        startActivity(intent);
    }

    /**
     * Lit un champ montant. Renvoie null si la saisie est vide, non numérique
     * ou négative, après avoir signalé l'erreur sur le champ fautif.
     */
    private Double lireMontant(EditText champ) {
        String saisie = champ.getText().toString().trim();

        if (saisie.isEmpty()) {
            champ.setError(getString(R.string.err_champ_vide));
            champ.requestFocus();
            return null;
        }

        double valeur;
        try {
            valeur = Double.parseDouble(saisie.replace(',', '.'));
        } catch (NumberFormatException e) {
            champ.setError(getString(R.string.err_champ_vide));
            champ.requestFocus();
            return null;
        }

        if (valeur < 0) {
            champ.setError(getString(R.string.err_montant_negatif));
            champ.requestFocus();
            return null;
        }

        return valeur;
    }
}