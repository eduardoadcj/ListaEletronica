package eduardocruz.listaeletronica2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import eduardocruz.listaeletronica2.entidades.Produto;

public class ProdutoDao {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public ProdutoDao(Context context) {

        dbHelper = new DBHelper(context);

    }

    public String salvar(Produto p){

        ContentValues valores;
        long result;

        String nome = p.getNome();
        Double preco = p.getPreco();
        String descricao = p.getDescricao();
        String link = p.getLink();

        db = dbHelper.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DBHelper.PRODUTO_NOME,nome);
        valores.put(DBHelper.PRODUTO_PRECO,preco.toString());
        valores.put(DBHelper.PRODUTO_DESCRICAO,descricao);
        valores.put(DBHelper.PRODUTO_LINK,link);
        result = db.insert(DBHelper.TABLE_PRODUTO,null,valores);
        db.close();

        if(result == -1){
            return "Erro ao inserir registro";
        }else{
            return "Registro inserido com sucesso";
        }

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
                p.setDescricao(cursor.getString(4));

                list.add(p);

            }while(cursor.moveToNext());

        }
        return list;
    }


    //https://www.youtube.com/watch?v=B5CeH1EZzi4

}
