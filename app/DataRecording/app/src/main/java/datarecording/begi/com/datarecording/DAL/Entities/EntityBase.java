package datarecording.begi.com.datarecording.DAL.Entities;

/**
 * Created by asus1 on 30.10.2017.
 */
public class EntityBase {

    private int ID;

    public EntityBase() {
    }

    public EntityBase(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
