package datarecording.begi.com.datarecording.DAL.Repositories;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by asus1 on 2.11.2017.
 */
public class RepositoryContainer {

    private static RepositoryContainer rc = null;
    private Context context;

    private ArrayList<IRepository> repositories;

    private RepositoryContainer(Context context)
    {
        repositories = new ArrayList<IRepository>();
        this.context = context;
    }

    // Singleton
    public static RepositoryContainer create(Context context)
    {
        if (rc == null)
        {
            rc = new RepositoryContainer(context);
        }
        return rc;
    }

    // Repository adına göre nesne döndüren factory
    public IRepository getRepository(int repName)
    {
        IRepository ir = null;
        switch (repName)
        {
            case RepositoryNames.TODO:
                ir = new HatirlatmaRepository(context);
                break;
            case  RepositoryNames.CATEGORY:
                ir = new KategoriRepository(context);
                break;
        }
        return ir;
    }
}
