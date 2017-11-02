package datarecording.begi.com.datarecording.DAL.Repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by asus1 on 2.11.2017.
 */
public class DbGateway extends SQLiteOpenHelper{

    public DbGateway(Context context, String dbname, SQLiteDatabase.CursorFactory cf, int version)
    {
        super(context, dbname, cf, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Kategoriler(No INTEGER PRIMARY KEY, Aciklama TEXT);");
        db.execSQL("Create table Hatirlatmalar(_id INTEGER PRIMARY KEY, Metin TEXT, Kategori NUMBER, Tarih TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
        Log.i("DbGateway:onOpen", "Bağlantı açıldı");
    }
}
