package eduardocruz.listaeletronica2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "listDataBase";
    private static int DV_VERSION = 1;

    private static String TABLE_PRODUTO = "CREATE TABLE produto(" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "nome TEXT NOT NULL," +
            "preco NUM" +
            "quantidade NUM" +
            "descricao TEXT" +
            ")";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DV_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_PRODUTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
