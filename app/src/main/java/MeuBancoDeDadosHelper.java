import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MeuBancoDeDadosHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO_DE_DADOS = "meu_banco_de_dados.db";
    private static final int VERSAO_BANCO_DE_DADOS = 1;

    public MeuBancoDeDadosHelper(Context context) {
        super(context, NOME_BANCO_DE_DADOS, null, VERSAO_BANCO_DE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crie a tabela para armazenar as palavras
        String queryCriacaoTabela = "CREATE TABLE tabela_palavras (_id INTEGER PRIMARY KEY AUTOINCREMENT, palavra TEXT)";
        db.execSQL(queryCriacaoTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualize o banco de dados, se necessário
    }
}