package com.example.gestiondepenses;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dao.DepenseAdapter;
import modele.Depense;
import utils.Utilitaire;

public class ResultatActivity extends AppCompatActivity {

    public static final String EXTRA_VOL = "vol";
    public static final String EXTRA_HEBERGEMENT = "hebergement";
    public static final String EXTRA_RESTAURANT = "restaurant";

    private TextView txtVol, txtHebergement, txtRestaurant;
    private TextView txtTotal, txtTaux, txtContribution;

    private DepenseAdapter adapter;
    private Depense depense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setWidgets();
        adapter = new DepenseAdapter(ResultatActivity.this);
        recupererDepense();
        afficherDepense();
    }

    private void afficherDepense() {
        txtVol.setText(Utilitaire.formaterMontant(depense.getVol()));
        txtHebergement.setText(Utilitaire.formaterMontant(depense.getHebergement()));
        txtRestaurant.setText(Utilitaire.formaterMontant(depense.getRestaurant()));
        txtTotal.setText(Utilitaire.formaterMontant(depense.getTotal()));
        txtTaux.setText(Utilitaire.formaterTaux(Utilitaire.determinerTaux(depense.getTotal())));
        txtContribution.setText(Utilitaire.formaterMontant(depense.getContribution()));
    }

    private void recupererDepense() {
        double vol         = getIntent().getDoubleExtra(EXTRA_VOL, 0);
        double hebergement = getIntent().getDoubleExtra(EXTRA_HEBERGEMENT, 0);
        double restaurant  = getIntent().getDoubleExtra(EXTRA_RESTAURANT, 0);
        depense = new Depense(vol, hebergement, restaurant);
    }

    private void setWidgets() {
        txtVol          = findViewById(R.id.txtVol);
        txtHebergement  = findViewById(R.id.txtHebergement);
        txtRestaurant   = findViewById(R.id.txtRestaurant);
        txtTotal        = findViewById(R.id.txtTotal);
        txtTaux         = findViewById(R.id.txtTaux);
        txtContribution = findViewById(R.id.txtContribution);
    }

    public void onModifier(View view) {
        this.finish();

    }

    public void onConfirmer(View view) {
        adapter.inserer(depense);
        Toast.makeText(this, R.string.msg_enregistre, Toast.LENGTH_LONG).show();
        this.finish();
    }
}