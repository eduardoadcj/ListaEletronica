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

import java.util.ArrayList;

import eduardocruz.listaeletronica2.adapters.ProdutoAdapterListView;
import eduardocruz.listaeletronica2.database.ItensListaDao;
import eduardocruz.listaeletronica2.database.ProdutoDao;
import eduardocruz.listaeletronica2.entidades.ItensLista;
import eduardocruz.listaeletronica2.entidades.Produto;

public class Lista extends AppCompatActivity {

    ViewFlipper flip;
    TextView nameList;

    ListView pesqProduto;
    static ProdutoAdapterListView adapter;
    ArrayList<Produto> fullList= new ArrayList();
    ArrayList<ItensLista> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        flip = (ViewFlipper) findViewById(R.id.viewFlipper);
        nameList = (TextView) findViewById(R.id.txtName_list);
        Intent i = getIntent();
        nameList.setText(i.getStringExtra("nome"));

        pesqProduto = (ListView) findViewById(R.id.lista_listViewPesqProduto);

        upDateFullList();

    }

    public void nextView(View v){

        flip.showNext();

    }

    public void previousView(View v){

        flip.showPrevious();

    }


    private void upDateFullList(){
        try{

            ProdutoDao pd = new ProdutoDao(getApplicationContext());
            this.fullList = new ArrayList();
            this.fullList = pd.listar();
            this.adapter = new ProdutoAdapterListView(getApplicationContext(),fullList);
            pesqProduto.setAdapter(adapter);

        }catch(Exception e){

        }
    }

    public void saveLista(View v){

        ItensListaDao ild = new ItensListaDao(this.getApplicationContext());



    }

}
