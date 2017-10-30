package datarecording.begi.com.datarecording;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public  Button bntAdd, btnDelete,btnUpdate;
    public EditText edtName, edtSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bntAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        edtName = (EditText)findViewById(R.id.edtName);
        edtSurname = (EditText)findViewById(R.id.edtSurname);

        bntAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =edtName.getText().toString();
                String surname = edtSurname.getText().toString();

                try {
                    Database database = new Database(MainActivity.this);
                    database.openConnection();
                    database.Add(name, surname);
                    database.closeConnection();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });

     /*   btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/
    }


}
