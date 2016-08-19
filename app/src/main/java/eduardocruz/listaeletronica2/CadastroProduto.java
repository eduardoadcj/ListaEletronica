package eduardocruz.listaeletronica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CadastroProduto extends AppCompatActivity {

    EditText txtNome;
    EditText txtPreco;
    EditText txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtPreco = (EditText) findViewById(R.id.txtPreco);
        txtDescricao = (EditText) findViewById(R.id.txtDescricao);

    }

}
