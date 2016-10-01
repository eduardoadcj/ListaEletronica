package eduardocruz.listaeletronica2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.PreparedStatement;
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

    public ArrayList<Produto> listar() throws Exception{

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM produto";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<Produto> list = new ArrayList();


        if(cursor.getCount()>0){
            cursor.moveToFirst();

            for (int i =0;i<cursor.getCount();i++){
                Produto p = new Produto();

                p.setId(cursor.getInt(cursor.getColumnIndex("id")));
                p.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                p.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));
                p.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                p.setLink(cursor.getString(cursor.getColumnIndex("link")));
                list.add(p);

                cursor.moveToNext();
            }

        }

        for(Produto p : list){

            System.out.println(p.getId()+" - "+p.getNome());

        }


        return list;
    }

    public ArrayList<Produto> pesquisar(String nome) throws Exception{

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM produto WHERE nome LIKE('%"+nome+"%')";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<Produto> list = new ArrayList();

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            for (int i =0;i<cursor.getCount();i++){
                Produto p = new Produto();

                p.setId(cursor.getInt(cursor.getColumnIndex("id")));
                p.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                p.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));
                p.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                p.setLink(cursor.getString(cursor.getColumnIndex("link")));
                list.add(p);

                cursor.moveToNext();
            }

        }

        for(Produto p : list){

            System.out.println(p.getId()+" - "+p.getNome());

        }

        return list;
    }



    public void excluir(Integer id){

        System.out.println("Esse daki q é o id ó:"+id);
        db = dbHelper.getWritableDatabase();
        String where = "id="+id;
        db.delete("produto",where,null);
        db.close();

    }

    public void editar(Produto p){

        ContentValues valores;
        String where;
        String nome = p.getNome();
        Double preco = p.getPreco();
        String descricao = p.getDescricao();
        String link = p.getLink();


        db = dbHelper.getWritableDatabase();

        where = "id="+p.getId();

        valores = new ContentValues();

        valores.put(DBHelper.PRODUTO_NOME,nome);
        valores.put(DBHelper.PRODUTO_PRECO,preco.toString());
        valores.put(DBHelper.PRODUTO_DESCRICAO,descricao);
        valores.put(DBHelper.PRODUTO_LINK,link);

        db.update("produto",valores,where,null);
        db.close();

    }


    //https://www.youtube.com/watch?v=B5CeH1EZzi4

}
