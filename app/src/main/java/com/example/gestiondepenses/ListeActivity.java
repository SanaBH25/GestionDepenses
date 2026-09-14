package com.example.gestiondepenses;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import dao.DepenseAdapter;
import modele.Depense;

public class ListeActivity extends AppCompatActivity {

    private ListView listing;
    private DepenseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listing = findViewById(R.id.listing);
        adapter = new DepenseAdapter(ListeActivity.this);
    }

    /** Rechargé à chaque retour sur l'écran : la liste reste à jour. */
    @Override
    protected void onResume() {
        super.onResume();
        afficherDepenses();
    }

    private void afficherDepenses() {
        ArrayList<Depense> depenses = adapter.lister();

        if (depenses.isEmpty()) {
            Toast.makeText(this, R.string.msg_aucune_depense, Toast.LENGTH_LONG).show();
        }

        ArrayAdapter<Depense> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, depenses);
        listing.setAdapter(listAdapter);
    }
}