package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NOM     = "depenses.sdb";
    public static final int    VERSION_BD = 1;
    public static final String TABLE_DEPENSE = "depenses";

    public static final String COL_ID = "_id";
    public static final String COL_VOL          = "MontantVol";
    public static final String COL_HEBERGEMENT  = "MontantHebergement";
    public static final String COL_RESTAURANT   = "MontantRestaurant";
    public static final String COL_TOTAL        = "MontantTotal";
    public static final String COL_CONTRIBUTION = "Contribution";
    public static final String COL_DATE         = "DateSaisie";
    private static final String DEPENSE_DDL = "CREATE TABLE " + TABLE_DEPENSE + 
            "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_VOL + " REAL, "
            + COL_HEBERGEMENT + " REAL, "
            + COL_RESTAURANT + " REAL, "
            + COL_TOTAL + " REAL, "
            + COL_CONTRIBUTION + " REAL, "
            + COL_DATE + " TEXT"
            + ")";;

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DEPENSE_DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // to do
    }
}
