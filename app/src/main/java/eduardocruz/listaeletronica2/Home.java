package eduardocruz.listaeletronica2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void newLista(View v){ //metodo responsavel por abrir a lista


        //codigo responsavel por abrir um PopUp
        LayoutInflater layoutInflater = LayoutInflater.from(Home.this);
        View promptView = layoutInflater.inflate(R.layout.activity_home_insert_name, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Home.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //caso o botao Ok seja selecionado

                        Intent i = new Intent(Home.this, Lista.class); //inicia um intent para Lista
                        i.putExtra("nome", editText.getText().toString()); //pega o valor de texto inserido pelo usuario e o anexa a intent
                        startActivity(i); //inicia a intent

                    }
                })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //caso o botao cancelar seja selecionado

                                dialog.cancel();//cancelar operacao

                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    public void listarProdutos(View v){

        Intent i = new Intent(Home.this, ListaProduto.class);
        startActivity(i);

    }

    public void listarListas(View v){
        Intent i = new Intent(Home.this, ListaListas.class);
        startActivity(i);
    }


}
