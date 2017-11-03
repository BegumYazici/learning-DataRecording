package datarecording.begi.com.datarecording.DAL.Repositories;

import android.content.Context;

import java.util.Vector;

import datarecording.begi.com.datarecording.DAL.Entities.EntityBase;
import datarecording.begi.com.datarecording.DAL.Repositories.DbGateway;

/**
 * Created by asus1 on 2.11.2017.
 */
public abstract class IRepository {

    protected Context context;
    protected DbGateway dbg;

    public IRepository(Context context) {
        this.context = context;
        dbg = new DbGateway(context,"todo.db",null,1);
    }

    //CRUD işlemleri için eylemler
    public abstract long getCount();
    public abstract boolean Add(EntityBase e);
    public abstract boolean Delete(int id);
    public abstract boolean Update(EntityBase e);
    public abstract EntityBase getRecord(int id);
    public abstract Vector<EntityBase> getResult();
}
