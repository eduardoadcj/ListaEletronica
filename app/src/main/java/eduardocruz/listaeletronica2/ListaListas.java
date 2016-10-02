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

import java.util.ArrayList;

import eduardocruz.listaeletronica2.adapters.ItensProdutoAdapterListView;
import eduardocruz.listaeletronica2.adapters.ListasAdapterListView;
import eduardocruz.listaeletronica2.database.ListasDao;
import eduardocruz.listaeletronica2.entidades.Listas;

public class ListaListas extends AppCompatActivity {

    ListView listView;
    ArrayList<Listas> list;
    ListasAdapterListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_listas);

        listView = (ListView) findViewById(R.id.listaListas_listview);



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



                    AlertDialog.Builder fechar = alertDialogBuilder.setCancelable(false)
                            .setPositiveButton("Adicionar à lista", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    //caso o botao Ok seja selecionado


                                }
                            })
                            .setNegativeButton("Fechar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            //caso o botao cancelar seja selecionado

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
