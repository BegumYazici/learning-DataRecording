package datarecording.begi.com.datarecording.DAL.Entities;

/**
 * Created by asus1 on 30.10.2017.
 */
public class HatirlatmaEntity extends EntityBase {

    private int Kategori;
    private String Metin;
    private String Tarih;

    public HatirlatmaEntity(int ID, int kategori, String metin, String tarih) {
        super(ID);
        Kategori = kategori;
        Metin = metin;
        Tarih = tarih;
    }

    public int getKategori() {
        return Kategori;
    }

    public String getMetin() {
        return Metin;
    }

    public String getTarih() {
        return Tarih;
    }

    public void setKategori(int kategori) {

        Kategori = kategori;
    }

    public void setMetin(String metin) {
        Metin = metin;
    }

    public void setTarih(String tarih) {
        Tarih = tarih;
    }
}
