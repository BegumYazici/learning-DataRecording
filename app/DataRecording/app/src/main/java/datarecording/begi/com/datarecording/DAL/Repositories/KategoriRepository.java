package datarecording.begi.com.datarecording.DAL.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

import datarecording.begi.com.datarecording.DAL.Entities.BlankEntity;
import datarecording.begi.com.datarecording.DAL.Entities.EntityBase;
import datarecording.begi.com.datarecording.DAL.Entities.KategoriEntity;

/**
 * Created by asus1 on 2.11.2017.
 */
public class KategoriRepository extends IRepository {

    //Tablo ve kolonların isimleri
    public static final String TABLE_NAME = "Kategoriler";
    public static final String ID = "No";
    public static final String ACIKLAMA = "Aciklama";

    public KategoriRepository(Context context) {
        super(context);
    }

    //kayıt sayısının alınması
    @Override
    public long getCount() {
        SQLiteDatabase db = super.dbg.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from " + TABLE_NAME, null);
        long r = cur.getCount();
        db.close();
        return r;
    }

    //Kayıt ekleme
    @Override
    public boolean Add(EntityBase e) {
        KategoriEntity he = (KategoriEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ACIKLAMA", he.getAciklama());
        long r = db.insert(TABLE_NAME, null, cv);
        db.close();
        if (r > 0) {
            return true;
        } else
            return false;
    }

    //Guncelleme
    @Override
    public boolean Update(EntityBase e) {
        KategoriEntity he = (KategoriEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ACIKLAMA", he.getAciklama());
        long r = db.update(TABLE_NAME, cv, ID + " = ?", new String[]{String.valueOf(he.getID())});
        db.close();
        if (r > 0) {
            return true;
        } else
            return false;
    }

    @Override
    public boolean Delete(int id) {
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        long r = db.delete(TABLE_NAME, ID + " =?", new String[]{String.valueOf(id)});
        db.close();
        if (r > 0) {
            return true;
        } else
            return false;
    }


    @Override
    public EntityBase getRecord(int id) {
        EntityBase entity = null;
        SQLiteDatabase db = super.dbg.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + "where " + ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            entity = new KategoriEntity(id, cursor.getString(cursor.getColumnIndex(ACIKLAMA)));
        } else {
            entity = new BlankEntity();
        }
        return entity;
    }

    @Override
    public Vector<EntityBase> getResult() {
        EntityBase entity = null;
        SQLiteDatabase db = super.dbg.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, ACIKLAMA}, "", null, "", "", "");
        Vector<EntityBase> records = new Vector<EntityBase>(cursor.getCount());

        while (cursor.moveToNext()) {
            entity = new KategoriEntity(cursor.getInt(0), cursor.getString(cursor.getColumnIndex(ACIKLAMA)));
            records.add(entity);
        }
        return records;
    }
}
