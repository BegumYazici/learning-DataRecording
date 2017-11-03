package datarecording.begi.com.datarecording.DAL;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import datarecording.begi.com.datarecording.DAL.Entities.EntityBase;
import datarecording.begi.com.datarecording.DAL.Entities.HatirlatmaEntity;
import datarecording.begi.com.datarecording.DAL.Entities.KategoriEntity;
import datarecording.begi.com.datarecording.DAL.PL.HatirlatmaAdapter;
import datarecording.begi.com.datarecording.DAL.PL.KategoriAdapter;
import datarecording.begi.com.datarecording.DAL.Repositories.IRepository;
import datarecording.begi.com.datarecording.DAL.Repositories.RepositoryContainer;
import datarecording.begi.com.datarecording.DAL.Repositories.RepositoryNames;
import datarecording.begi.com.datarecording.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout pnlMain;
    private Spinner spnCategory, spnID;
    private EditText txtText, txtDate;

    private RepositoryContainer repositoryContainer;
    private IRepository repository;
    private boolean ins_upd_flg = false;
    private int cat_id = 0, sel_id = 0;

    private void init()
    {
        repositoryContainer = RepositoryContainer.create(this);

        pnlMain = (LinearLayout) findViewById(R.id.pnlMain);
        spnCategory = (Spinner) findViewById(R.id.spnCategory);
        spnID = (Spinner) findViewById(R.id.spnID);
        txtText = (EditText) findViewById(R.id.txtText);
        txtDate = (EditText) findViewById(R.id.txtDate);
    }

    // Kategorilerin alınması
    private void BuildCategories()
    {
        repository = repositoryContainer.getRepository(RepositoryNames.CATEGORY);
        if (repository.getCount()== 0) {
            repository.Add(new KategoriEntity(0, "Toplantı"));
            repository.Add(new KategoriEntity(0, "Eğitim"));
            repository.Add(new KategoriEntity(0, "Ders"));
            repository.Add(new KategoriEntity(0, "Özel Gün"));
        }
    }

    // Kategorilerin spinner'a bind edilmesi
    public void BindToCategorySpinner()
    {
        repository = repositoryContainer.getRepository(RepositoryNames.CATEGORY);
        KategoriAdapter adp = new KategoriAdapter(this, repository.getResult());
        spnCategory.setAdapter(adp);
    }

    // Hatırlatmaların spinner'a bind edilmesi
    public void BindToHatirlatmaSpinner()
    {
        repository = repositoryContainer.getRepository(RepositoryNames.TODO);
        HatirlatmaAdapter adp = new HatirlatmaAdapter(this, repository.getResult());
        spnID.setAdapter(adp);
    }

    // Hatırlatma seçimi
    private void spnID_Selection()
    {
        spnID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                                        {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view,
                                                                       int position, long id)
                                            {
                                                TextView tID = (TextView) view;
                                                sel_id = Integer.parseInt(tID.getText().toString());
                                                repository = repositoryContainer.getRepository(RepositoryNames.TODO);
                                                DisplayEntity(repository.getRecord(sel_id));
                                                ins_upd_flg = false;

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent)
                                            {

                                            }
                                        }


        );
    }

    // Kategori seçimi
    private void spnCategory_Selection()
    {
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                                              {
                                                  @Override
                                                  public void onItemSelected(AdapterView<?> parent, View view,
                                                                             int position, long id)
                                                  {
                                                      LinearLayout p = (LinearLayout) view;
                                                      TextView tID = (TextView) p.getChildAt(0);
                                                      cat_id = Integer.parseInt(tID.getText().toString());

                                                  }

                                                  @Override
                                                  public void onNothingSelected(AdapterView<?> parent)
                                                  {

                                                  }
                                              }


        );
    }

    private void registerHandlers()
    {
        spnID_Selection();
        spnCategory_Selection();
    }

    // HatırlatmaEntity nesnesinin ekranda gösterimi
    private void DisplayEntity(EntityBase e)
    {
        HatirlatmaEntity he = (HatirlatmaEntity) e;

        txtDate.setText(he.getTarih());
        txtText.setText(he.getMetin());

        // Hatırlatmaya ait kategorinin spinner'da seçili hale getirilmesi
        for (int i = 0; i <= spnCategory.getCount() - 1; ++i) {
            KategoriEntity itm = (KategoriEntity) spnCategory.getItemAtPosition(i);

            if (he.getKategori() == itm.getID()) {
                spnCategory.setSelection(i);
                break;
            }
        }
    }

    // Ekrandaki alanlardan bir HatirlatmaEntity elde edilmesi
    private EntityBase GetEntityFromScreen(int id)
    {
        HatirlatmaEntity he = new HatirlatmaEntity(id,
                txtText.getText().toString(),
                cat_id,
                txtDate.getText().toString());
        return he;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            init();
            BuildCategories();
            BindToCategorySpinner();
            BindToHatirlatmaSpinner();
            registerHandlers();
        }
        catch (Exception exc)
        {
            Log.e("Activity:onCreate", exc.getMessage());
        }
    }

    // Silme işlemi
    public void mnuDelete_Click(MenuItem m)
    {
        if (!ins_upd_flg) {
            repository.Delete(sel_id);
            BindToHatirlatmaSpinner();
        }
    }

    // ins_upd_flg'nin değerine göre insert | update işlemi
    public void mnuSave_Click(MenuItem m)
    {
        repository = repositoryContainer.getRepository(RepositoryNames.TODO);
        if (ins_upd_flg) {
            repository.Add(GetEntityFromScreen(0));
            BindToHatirlatmaSpinner();
        } else {
            repository.Update(GetEntityFromScreen(sel_id));
            BindToHatirlatmaSpinner();
        }
    }

    // Ekranın temizlenmesi
    public void mnuClear_Click(MenuItem m)
    {
        ins_upd_flg = true;
        spnCategory.setSelection(0);

        for (int i = 0; i <= pnlMain.getChildCount() - 1; ++i) {
            View v = pnlMain.getChildAt(i);

            if (v instanceof EditText) {
                ((EditText) v).setText("");
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu m)
    {
        this.getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }
}
