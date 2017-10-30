package datarecording.begi.com.datarecording.DAL.Entities;

/**
 * Created by asus1 on 30.10.2017.
 */
public class KategoriEntity extends EntityBase {

    private String Aciklama;

    public KategoriEntity(int ID, String aciklama) {
        super(ID);
        Aciklama = aciklama;
    }

    public String getAciklama() {
        return Aciklama;
    }

    public void setAciklama(String aciklama) {
        Aciklama = aciklama;
    }
}
