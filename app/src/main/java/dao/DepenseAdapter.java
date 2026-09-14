package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import modele.Depense;

public class DepenseAdapter {
    private DbHelper helper;
    private SQLiteDatabase db;

    public DepenseAdapter(Context context) {
        helper = new DbHelper(context, DbHelper.DB_NOM, null, DbHelper.VERSION_BD);
    }

    private void openBD() {
        db = helper.getWritableDatabase();
    }

    private void closeBD() {
        db.close();
    }

    public long inserer(Depense depense) {
        openBD();
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.COL_VOL,          depense.getVol());
        cv.put(DbHelper.COL_HEBERGEMENT,  depense.getHebergement());
        cv.put(DbHelper.COL_RESTAURANT,   depense.getRestaurant());
        cv.put(DbHelper.COL_TOTAL,        depense.getTotal());
        cv.put(DbHelper.COL_CONTRIBUTION, depense.getContribution());
        cv.put(DbHelper.COL_DATE,         depense.getDateSaisie());
        long id = db.insert(DbHelper.TABLE_DEPENSE, null, cv);
        closeBD();
        return id;
    }

    public ArrayList<Depense> lister() {
        openBD();
        String[] cols = {
                DbHelper.COL_ID, DbHelper.COL_VOL, DbHelper.COL_HEBERGEMENT,
                DbHelper.COL_RESTAURANT, DbHelper.COL_TOTAL,
                DbHelper.COL_CONTRIBUTION, DbHelper.COL_DATE
        };
        ArrayList<Depense> resultats = new ArrayList<>();

        Cursor curseur = db.query(DbHelper.TABLE_DEPENSE, cols, null, null,
                null, null, DbHelper.COL_DATE + " DESC");

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            Depense d = new Depense(
                    curseur.getInt(0),
                    curseur.getDouble(1),
                    curseur.getDouble(2),
                    curseur.getDouble(3),
                    curseur.getDouble(4),
                    curseur.getDouble(5),
                    curseur.getString(6));
            resultats.add(d);
            curseur.moveToNext();
        }
        curseur.close();
        closeBD();
        return resultats;
    }
}
