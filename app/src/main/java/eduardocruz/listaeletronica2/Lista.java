package eduardocruz.listaeletronica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

public class Lista extends AppCompatActivity {

    ViewFlipper flip;
    TextView nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        flip = (ViewFlipper) findViewById(R.id.viewFlipper);
        nameList = (TextView) findViewById(R.id.txtName_list);
        Intent i = getIntent();
        nameList.setText(i.getStringExtra("nome"));

    }

    public void nextView(View v){

        flip.showNext();

    }

    public void previousView(View v){

        flip.showPrevious();

    }

}
