package eduardocruz.listaeletronica2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "ListaEletronicaDataBase";
    private static final int VERSAO = 1;

    public static final String TABLE_PRODUTO = "produto";
    public static final String PRODUTO_ID = "id";
    public static final String PRODUTO_NOME = "nome";
    public static final String PRODUTO_PRECO = "preco";
    public static final String PRODUTO_DESCRICAO = "descricao";
    public static final String PRODUTO_LINK = "link";

    private static String TABLE_LISTA = "CREATE TABLE lista(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "nome TEXT NOT NULL," +
            "total NUM," +
            ")";

    private static String TABLE_ITENS_LISTA = "CREATE TABLE itenslista(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_lista INTEGER NOT NULL," +
            "FOREIGN KEY(id_lista) REFERENCES lista(id)," +
            "id_produto INTEGER NOTNULL," +
            "FOREIGN KEY(id_produto) REFERENCES produto(id)," +
            "quantidade NUM" +
            ")";

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_PRODUTO+"("
                +PRODUTO_ID+" integer not null primary key autoincrement,"
                +PRODUTO_NOME+" text,"
                +PRODUTO_PRECO+" num,"
                +PRODUTO_DESCRICAO+" text,"
                +PRODUTO_LINK+" text"+
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_PRODUTO);
        onCreate(db);

    }
}
