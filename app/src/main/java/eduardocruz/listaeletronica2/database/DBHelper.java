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

    public static final String TABLE_LISTAS = "listas";
    public static final String LISTAS_ID = "id";
    public static final String LISTAS_NOME = "nome";
    public static final String LISTAS_TOTAL = "total";

    public static final String TABLE_ITENS_LISTA = "itens_lista";
    public static final String ITENS_LISTA_ID = "id";
    public static final String ITENS_LISTA_ID_LISTA = "id_lista";
    public static final String ITENS_LISTA_ID_PRODUTO = "id_produto";
    public static final String ITENS_LISTA_QUANTIDADE = "quantidade";

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
                ");";

        String sql2 = "CREATE TABLE "+TABLE_LISTAS+"("
                +LISTAS_ID+" integer not null primary key autoincrement,"
                +LISTAS_NOME+" text,"
                +LISTAS_TOTAL+" num"
                +");";

        String sql3 = "CREATE TABLE "+TABLE_ITENS_LISTA+"("
                +ITENS_LISTA_ID+" integer not null primary key autoincrement,"
                +ITENS_LISTA_ID_LISTA+" integer not null,"
                +ITENS_LISTA_ID_PRODUTO+" integer not null,"
                +ITENS_LISTA_QUANTIDADE+" num"
                +"foreign key ("+ITENS_LISTA_ID_LISTA+") references"+TABLE_LISTAS+"("+LISTAS_ID+"),"
                +"foreign key ("+ITENS_LISTA_ID_PRODUTO+") references"+TABLE_PRODUTO+"("+PRODUTO_ID+"),"
                +")";

        db.execSQL(sql+sql2+sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_PRODUTO);
        onCreate(db);

    }
}
