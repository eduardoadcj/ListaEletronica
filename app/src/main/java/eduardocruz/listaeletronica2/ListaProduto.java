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
import android.widget.EditText;
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
    static ProdutoAdapterListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaproduto);
        produtoListView = (ListView) findViewById(R.id.listView_lista_produto);



        try {

            upDateList();


            produtoListView.setClickable(true);
            produtoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int item, long l) {

                    Produto p = new Produto();
                    p = adapter.getItem(item);
                    final String link = p.getLink();



                    LayoutInflater layoutInflater = LayoutInflater.from(ListaProduto.this);
                    View promptView = layoutInflater.inflate(R.layout.activity_listaproduto_info, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListaProduto.this);
                    alertDialogBuilder.setView(promptView);

                    final TextView nome = (TextView) promptView.findViewById(R.id.txt_InfoLista_nome);
                    final TextView preco = (TextView) promptView.findViewById(R.id.txt_InfoLista_total);
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

            produtoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    final int item = i;
                    Produto p = new Produto();
                    p = adapter.getItem(item);
                    final Integer prod_id = p.getId();
                    final String nome = p.getNome();
                    final String preco = p.getPreco().toString();
                    final String descricao = p.getDescricao();
                    final String link = p.getLink();

                    LayoutInflater layoutInflater = LayoutInflater.from(ListaProduto.this);
                    View promptView = layoutInflater.inflate(R.layout.activity_listaproduto_edit, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListaProduto.this);
                    alertDialogBuilder.setView(promptView);

                    final EditText txt_nome = (EditText) promptView.findViewById(R.id.txt_item_edit_nome);
                    final EditText txt_preco = (EditText) promptView.findViewById(R.id.txt_item_edit_preco);
                    final EditText txt_descricao = (EditText) promptView.findViewById(R.id.txt_item_edit_descricao);
                    final EditText txt_link = (EditText) promptView.findViewById(R.id.txt_item_edit_link);

                    txt_nome.setText(nome);
                    txt_preco.setText(preco);
                    txt_descricao.setText(descricao);
                    txt_link.setText(link);

                    AlertDialog.Builder fechar = alertDialogBuilder.setCancelable(false)
                            .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    //caso o botao salvar seja selecionado

                                    try{

                                        if(txt_nome.getText().toString().equals(nome) &&
                                                txt_preco.getText().toString().equals(preco) &&
                                                txt_descricao.getText().toString().equals(descricao) &&
                                                txt_link.getText().toString().equals(link))
                                        {

                                            System.out.println("Sem auterações");
                                            dialog.cancel();

                                        }else{

                                            Produto p2 = new Produto();
                                            p2.setId(prod_id);
                                            p2.setNome(txt_nome.getText().toString());
                                            p2.setPreco(Double.parseDouble(txt_preco.getText().toString()));
                                            p2.setDescricao(txt_descricao.getText().toString());
                                            p2.setLink(txt_descricao.getText().toString());

                                            ProdutoDao pd = new ProdutoDao(getApplicationContext());
                                            pd.editar(p2);
                                            Toast.makeText(getApplicationContext(), "Registro atualizado com sucesso!", Toast.LENGTH_LONG).show();
                                            upDateList();
                                        }


                                    }catch(Exception e){

                                        Toast.makeText(getApplicationContext(), "Erro ao atualizar o registro!", Toast.LENGTH_LONG).show();

                                    }

                                }
                            })
                            .setNeutralButton("Cancelar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            //caso o botao cancelar seja selecionado

                                            dialog.cancel();//cancelar operacao

                                        }
                                    })

                            .setNegativeButton("Excluir",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    //caso o botao excluir seja selecionado
                                    try{
                                        ProdutoDao pd = new ProdutoDao(getApplicationContext());
                                        System.out.println("O id antes de entra no metodo é esse:"+prod_id);
                                        pd.excluir(prod_id);
                                        upDateList();
                                        Toast.makeText(getApplicationContext(), "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();
                                    }catch(Exception e){
                                        Toast.makeText(getApplicationContext(), "Erro ao excluir o registro!", Toast.LENGTH_LONG).show();
                                    }

                                    dialog.cancel();//cancelar operacao


                                }
                            });

                    AlertDialog alert = alertDialogBuilder.create();
                    alert.show();


                    return true; //permite o outro evento simultaneo

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

    private void upDateList(){
        try{

            ProdutoDao pd = new ProdutoDao(getApplicationContext());
            this.list = new ArrayList();
            this.list = pd.listar();
            this.adapter = new ProdutoAdapterListView(getApplicationContext(),list);
            produtoListView.setAdapter(adapter);

        }catch(Exception e){

        }

    }

}
