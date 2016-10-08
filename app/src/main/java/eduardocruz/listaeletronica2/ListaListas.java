package eduardocruz.listaeletronica2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import eduardocruz.listaeletronica2.adapters.ItensProdutoAdapterListView;
import eduardocruz.listaeletronica2.adapters.ListasAdapterListView;
import eduardocruz.listaeletronica2.database.ItensListaDao;
import eduardocruz.listaeletronica2.database.ListasDao;
import eduardocruz.listaeletronica2.database.ProdutoDao;
import eduardocruz.listaeletronica2.entidades.ItensLista;
import eduardocruz.listaeletronica2.entidades.Listas;
import eduardocruz.listaeletronica2.entidades.Produto;

public class ListaListas extends AppCompatActivity {

    ListView listView;
    ArrayList<Listas> list;
    ArrayList<Produto> produtos;
    ListasAdapterListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_listas);

        listView = (ListView) findViewById(R.id.listaListas_listview);

        try{

            ProdutoDao pd = new ProdutoDao(getApplicationContext());
            produtos = pd.listar();

        }catch(Exception e){
            System.err.println("Erro ao pegar os dados dos produtos cadastrados");
        }

        try{

            upDateList();

            listView.setClickable(true);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    final Listas lista = list.get(i);

                    LayoutInflater layoutInflater = LayoutInflater.from(ListaListas.this);
                    View promptView = layoutInflater.inflate(R.layout.activity_listalistas_info, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListaListas.this);
                    alertDialogBuilder.setView(promptView);

                    final TextView nome = (TextView) promptView.findViewById(R.id.txt_InfoLista_nome);
                    final TextView total = (TextView) promptView.findViewById(R.id.txt_InfoLista_total);
                    final ListView lv = (ListView) promptView.findViewById(R.id.listView_InfoLista_Itens);

                    nome.setText(lista.getNome());
                    total.setText("Total: "+lista.getTotal()+" R$");

                    //fazer a listview funcionar aqui

                    ItensListaDao ild = new ItensListaDao(getApplicationContext());

                    ArrayList<ItensLista> itensList = ild.searchItensByListId(lista.getId());

                    ItensProdutoAdapterListView ipalv = new ItensProdutoAdapterListView(getApplicationContext(),produtos,itensList);

                    lv.setAdapter(ipalv);

                    AlertDialog.Builder fechar = alertDialogBuilder.setCancelable(false)
                            .setNeutralButton("Excluir", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    ItensListaDao ild = new ItensListaDao(getApplicationContext());

                                    if(ild.deleteByListId(lista.getId())){

                                        ListasDao ld = new ListasDao(getApplicationContext());
                                        ld.deleteList(lista.getId());

                                        Toast.makeText(getApplicationContext(), "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                                        upDateList();


                                    }else{

                                        Toast.makeText(getApplicationContext(), "Erro ao excluir o registro!", Toast.LENGTH_LONG).show();
                                        upDateList();

                                    }

                                }
                            })
                            .setPositiveButton("Fechar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            dialog.cancel();//cancelar operacao

                                        }
                                    });

                    AlertDialog alert = alertDialogBuilder.create();
                    alert.show();

                }

            });


        }catch(Exception e){

        }
    }


    private void upDateList(){

        try{

            ListasDao ld = new ListasDao(this.getApplicationContext());
            this.list = ld.listar();
            adapter = new ListasAdapterListView(getApplicationContext(),list);
            listView.setAdapter(adapter);

        }catch(Exception e){
            System.err.println("Erro ao atualizar a lista");
        }

    }


}
