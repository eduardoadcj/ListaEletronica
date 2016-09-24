package eduardocruz.listaeletronica2.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import eduardocruz.listaeletronica2.entidades.ItensLista;

public class ItensListaDao {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public ItensListaDao(Context context) {

        dbHelper = new DBHelper(context);

    }

    public void salvar(List<ItensLista> listIl){

        ContentValues valores;

        for(ItensLista il : listIl) {

            Integer id_lista = il.getId_lista();
            Integer id_produto = il.getId_produto();
            Double quantidade = il.getQuantidade();

            db = dbHelper.getWritableDatabase();
            valores = new ContentValues();
            valores.put(DBHelper.ITENS_LISTA_ID_LISTA, id_lista);
            valores.put(DBHelper.ITENS_LISTA_ID_PRODUTO, id_produto);
            valores.put(DBHelper.ITENS_LISTA_QUANTIDADE, quantidade);
            db.insert(DBHelper.TABLE_ITENS_LISTA, null, valores);

        }
        db.close();

    }


}
