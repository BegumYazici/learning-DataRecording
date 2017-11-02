package datarecording.begi.com.datarecording.DAL.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

import datarecording.begi.com.datarecording.DAL.Entities.BlankEntity;
import datarecording.begi.com.datarecording.DAL.Entities.EntityBase;
import datarecording.begi.com.datarecording.DAL.Entities.HatirlatmaEntity;

/**
 * Created by asus1 on 2.11.2017.
 */
public class HatirlatmaRepository extends IRepository {

    public static final String TABLE_NAME = "Hatirlatmalar";
    public static final String ID = "_id";
    public static final String METIN = "Metin";
    public static final String KATEGORI = "Kategori";
    public static final String TARIH = "Tarih";

    public HatirlatmaRepository(Context context)
    {
        super(context);
    }

    @Override
    public long getCount()
    {
        SQLiteDatabase db = super.dbg.getReadableDatabase();
        Cursor cur =  db.rawQuery("Select * from " + TABLE_NAME, null);
        long r = cur.getCount();
        db.close();
        return r;
    }

    @Override
    public boolean Add(EntityBase e)
    {
        HatirlatmaEntity he = (HatirlatmaEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(METIN, he.getMetin());
        cv.put(KATEGORI, he.getKategori());
        cv.put(TARIH, he.getTarih());
        long r = db.insert(TABLE_NAME, null, cv);
        db.close();
        if (r > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean Update(EntityBase e)
    {
        HatirlatmaEntity he = (HatirlatmaEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(METIN, he.getMetin());
        cv.put(KATEGORI, he.getKategori());
        cv.put(TARIH, he.getTarih());
        long r = db.update(TABLE_NAME, cv, ID + " = ?",
                new String[]{String.valueOf(he.getID())});
        db.close();
        if (r > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean Delete(int id)
    {
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        long r = db.delete(TABLE_NAME, ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        if (r > 0)
            return true;
        else
            return false;
    }

    @Override
    public EntityBase getRecord(int id)
    {
        EntityBase entity = null;
        SQLiteDatabase db = dbg.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from " + TABLE_NAME +
                " where " + ID + " = ?", new String[]{String.valueOf(id)});

        if (cur.moveToNext()) {

           entity = new HatirlatmaEntity(id,cur.getString(cur.getColumnIndex(METIN)),cur.getInt(2),cur.getString(3));
        }
        else
        {
            entity = new BlankEntity();
        }
        return entity;
    }

    @Override
    public Vector<EntityBase> getResult()
    {
        EntityBase entity = null;
        SQLiteDatabase db = dbg.getReadableDatabase();
        Cursor cur = db.query(TABLE_NAME,
                new String[] {ID, METIN, KATEGORI, TARIH }, "",
                null, "", "", "");
        Vector<EntityBase> records = new Vector<EntityBase>(cur.getCount());
        while (cur.moveToNext())
        {
            entity = new HatirlatmaEntity(cur.getInt(0),
                    cur.getString(cur.getColumnIndex(METIN)),
                    cur.getInt(2), cur.getString(3));
            records.add(entity);
        }
        return records;
    }
}
