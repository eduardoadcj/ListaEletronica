package eduardocruz.listaeletronica2.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
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

    public ArrayList<ItensLista> searchItensByListId(int id){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM itens_lista WHERE id_lista='"+id+"'";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<ItensLista> list = new ArrayList();


        if(cursor.getCount()>0){
            cursor.moveToFirst();

            for (int i =0;i<cursor.getCount();i++){
                ItensLista il = new ItensLista();

                il.setId(cursor.getInt(cursor.getColumnIndex("id")));
                il.setId_lista(cursor.getInt(cursor.getColumnIndex("id_lista")));
                il.setId_produto(cursor.getInt(cursor.getColumnIndex("id_produto")));
                il.setQuantidade(cursor.getDouble(cursor.getColumnIndex("quantidade")));
                list.add(il);

                cursor.moveToNext();
            }

        }

        db.close();
        return list;

    }

    public boolean deleteByListId(int id){

        System.out.println("Esse daki q é o id ó:"+id);
        db = dbHelper.getWritableDatabase();

        try {
            String where = "id_lista=" + id;
            db.delete("itens_lista", where, null);
            db.close();
            return true;
        }catch(Exception e){
            db.close();
            return false;
        }

    }


}
