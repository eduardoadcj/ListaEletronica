package eduardocruz.listaeletronica2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import eduardocruz.listaeletronica2.entidades.Listas;

public class ListasDao {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public ListasDao(Context context) {

        dbHelper = new DBHelper(context);

    }

    public String salvar(Listas l){

        ContentValues valores;
        long result;

        String nome = l.getNome();
        Double total = l.getTotal();

        db = dbHelper.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DBHelper.LISTAS_NOME,nome);
        valores.put(DBHelper.LISTAS_TOTAL,total.toString());
        result = db.insert(DBHelper.TABLE_LISTAS,null,valores);
        db.close();

        if(result == -1){
            return "Erro ao inserir registro";
        }else{
            return "Registro inserido com sucesso";
        }

    }

}