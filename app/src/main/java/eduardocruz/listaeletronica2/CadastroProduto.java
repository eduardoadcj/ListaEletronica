package eduardocruz.listaeletronica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import eduardocruz.listaeletronica2.database.ProdutoDao;
import eduardocruz.listaeletronica2.entidades.Produto;

public class CadastroProduto extends AppCompatActivity {

    EditText txtNome;
    EditText txtPreco;
    EditText txtDescricao;
    EditText txtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        txtNome = (EditText) findViewById(R.id.cadastroProduto_nome);
        txtPreco = (EditText) findViewById(R.id.cadastroProduto_preco);
        txtDescricao = (EditText) findViewById(R.id.cadastroProduto_descricao);
        txtLink = (EditText) findViewById(R.id.cadastroProduto_link);

    }

    public void salvar(View v){

        Produto p = new Produto();

        p.setNome(txtNome.getText().toString());
        p.setPreco(Double.parseDouble(txtPreco.getText().toString()));
        p.setDescricao(txtDescricao.getText().toString());
        p.setLink(txtLink.getText().toString());
        ProdutoDao pd = new ProdutoDao(this);
        String resultado = pd.salvar(p);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

        Intent i = new Intent(CadastroProduto.this,ListaProduto.class);
        startActivity(i);

    }

}
