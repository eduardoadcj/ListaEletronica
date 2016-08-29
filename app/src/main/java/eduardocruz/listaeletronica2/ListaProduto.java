package eduardocruz.listaeletronica2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import eduardocruz.listaeletronica2.adapters.ProdutoAdapterListView;
import eduardocruz.listaeletronica2.database.ProdutoDao;
import eduardocruz.listaeletronica2.entidades.Produto;

public class ListaProduto extends AppCompatActivity {

    ArrayList<Produto> list = new ArrayList();
    ListView produtoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);
        produtoListView = (ListView) findViewById(R.id.listView_lista_produto);

        ProdutoDao pd = new ProdutoDao(getApplicationContext());

        try {

            list = pd.listar();
            final ProdutoAdapterListView adapter = new ProdutoAdapterListView(getApplicationContext(),list);
            produtoListView.setAdapter(adapter);


            produtoListView.setClickable(true);
            produtoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int item, long l) {

                    Produto p = new Produto();
                    p = adapter.getItem(item);
                    final String link = p.getLink();



                    LayoutInflater layoutInflater = LayoutInflater.from(ListaProduto.this);
                    View promptView = layoutInflater.inflate(R.layout.info_produto, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListaProduto.this);
                    alertDialogBuilder.setView(promptView);

                    final TextView nome = (TextView) promptView.findViewById(R.id.txt_InfoProduto_nome);
                    final TextView preco = (TextView) promptView.findViewById(R.id.txt_InfoProduto_preco);
                    final TextView descricao = (TextView) promptView.findViewById(R.id.txt_InfoProduto_descricao);



                    nome.setText(p.getNome());
                    preco.setText("Preço: "+p.getPreco()+" R$");
                    descricao.setText("Descrição: "+p.getDescricao());


                    AlertDialog.Builder fechar = alertDialogBuilder.setCancelable(false)
                            .setPositiveButton("Mais informações", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    //caso o botao Ok seja selecionado
                                    try{

                                        Intent abreLink = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + link));
                                        startActivity(abreLink);

                                    }catch(Exception e){

                                        Toast.makeText(getApplicationContext(), "Erro! Link invalido.", Toast.LENGTH_LONG).show();

                                    }


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
            System.out.println(e.getMessage());
        }

    }

    public void cadastrarProduto(View v){

        Intent i = new Intent(ListaProduto.this, CadastroProduto.class);
        startActivity(i);

    }

}
