package eduardocruz.listaeletronica2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "ListaEletronicaDataBase";
    private static int DV_VERSION = 1;

    private static String TABLE_PRODUTO = "CREATE TABLE produto(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome TEXT," +
            "preco NUM," +
            "descricao TEXT" +
            ")";

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
        super(context, DB_NAME, null, DV_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_PRODUTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
}
