package datarecording.begi.com.datarecording;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus1 on 30.10.2017.
 */
public class Database {

    private static final String DATABASE_NAME = "Kisiler";
    private static final String DATABASE_TABLE = "Rehber";
    private static final int DATABASE_VERSİON =1;

    private final Context contextim;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_NAME = "isim";
    public static final String KEY_PHONE = "telefon";

    public Database(Context contextim) {
        this.contextim = contextim;
    }

    public Database openConnection() throws SQLiteException{

        databaseHelper = new DatabaseHelper(contextim);
        database = databaseHelper.getWritableDatabase();

        return this;
    }

    public void closeConnection(){

        databaseHelper.close();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME,null,DATABASE_VERSİON);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROW_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_NAME
                    + " TEXT NOT NULL, " + KEY_PHONE + " TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(sqLiteDatabase);

        }
    }

    public long Add(String name, String surname){

        ContentValues cv = new ContentValues();

        cv.put("KEY_NAME",name);
        cv.put("KEY_SURNAME",surname);

        return  database.insert(DATABASE_TABLE,null,cv);
    }

    public String tumKayitlar() {
        // TODO Auto-generated method stub

        String[] sutunlar = new String[] { KEY_ROW_ID, KEY_NAME, KEY_PHONE };
        Cursor c = database.query(DATABASE_TABLE, sutunlar, null, null,
                null, null, null);

        String tumKayitlar = "";

//sütunların id'leri değişkenlere atandı
//id sütunu 0, isim sütunu 1 ve yas sütünu 2 indexlerine sahip
        int idSiraNo = c.getColumnIndex(KEY_ROW_ID);
        int isimSiraNo = c.getColumnIndex(KEY_NAME);
        int yasSiraNo = c.getColumnIndex(KEY_PHONE);

//tüm kayıtların okunması bu for döngüsüyle sağlandı
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            tumKayitlar = tumKayitlar + c.getString(idSiraNo) + "    "
                    + c.getString(isimSiraNo) + "  " + c.getString(yasSiraNo)
                    + " \n";
        }

        return tumKayitlar;
    }

    public void kaydiSil(long silinecekID) {
        // TODO Auto-generated method stub
        database .delete(DATABASE_TABLE, KEY_ROW_ID + "=" + silinecekID, null);
    }

    public void kaydiGuncelle(long guncellenecekID, String adGuncelle,
                              String yasGuncelle) {
        // TODO Auto-generated method stub
        ContentValues cvGuncelle = new ContentValues();
        cvGuncelle.put(KEY_NAME, adGuncelle);
        cvGuncelle.put(KEY_PHONE, yasGuncelle);

        database.update(DATABASE_TABLE, cvGuncelle, KEY_ROW_ID + "="
                + guncellenecekID, null);

    }
}
