package eduardocruz.listaeletronica2.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import eduardocruz.listaeletronica2.entidades.Produto;

public class ProdutoDao {

    private static DBHelper dbHelper = null;

    public ProdutoDao(Context context) {

        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }

    }

    public void salvar(Produto p){

        String nome = p.getNome();
        Double preco = p.getPreco();
        Double quantidade = p.getQuantidade();
        String descricao = p.getDescricao();

        String sql = "INSERT INTO produto (nome,preco,quantidade,descricao) VALUES ("+nome+","
                +preco+","+quantidade+","+descricao+")";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
    }

    public List<Produto> listar(){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM produto";
        Cursor cursor = db.rawQuery(sql,null);
        List<Produto> list = null;

        if(cursor != null && cursor.moveToFirst()){
            list = new ArrayList();

            do{
                Produto p = new Produto();

                p.setId(cursor.getInt(1));
                p.setNome(cursor.getString(2));
                p.setPreco(cursor.getDouble(3));
                p.setQuantidade(cursor.getDouble(4));
                p.setDescricao(cursor.getString(5));

                list.add(p);

            }while(cursor.moveToNext());

        }
        return list;
    }


    //https://www.youtube.com/watch?v=B5CeH1EZzi4

}
