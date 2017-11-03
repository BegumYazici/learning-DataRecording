package datarecording.begi.com.datarecording.DAL.PL;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import datarecording.begi.com.datarecording.DAL.Entities.EntityBase;
import datarecording.begi.com.datarecording.DAL.Entities.KategoriEntity;

/**
 * Created by asus1 on 3.11.2017.
 */
public class KategoriAdapter extends BaseAdapter {

    private Context context;
    private Vector<EntityBase> model;

    public KategoriAdapter(Context context, Vector<EntityBase> model)
    {
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount()
    {
        return model.size();
    }

    @Override
    public Object getItem(int position)
    {
        return model.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return ((KategoriEntity) getItem(position)).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        KategoriEntity ke = (KategoriEntity) getItem(position);
        LinearLayout p = new LinearLayout(context);
        p.setOrientation(LinearLayout.HORIZONTAL);
        TextView tID = new TextView(context);
        tID.setText(String.valueOf(ke.getID()));
        tID.setVisibility(View.INVISIBLE);
        TextView tAciklama = new TextView(context);
        tAciklama.setText(ke.getAciklama());
        tAciklama.setTextSize(30);
        p.addView(tID);
        p.addView(tAciklama);
        return p;
    }
}
