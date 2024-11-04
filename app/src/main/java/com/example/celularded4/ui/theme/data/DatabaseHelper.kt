import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.celularded4.ui.theme.data.PersonagemData

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "meubanco.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "personagens"
        // Definição das colunas
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "nome"
        private const val COLUMN_CLASSE = "classe"
        private const val COLUMN_FORCA = "forca"
        private const val COLUMN_DESTRANZA = "destreza"
        private const val COLUMN_INTELIGENCIA = "inteligencia"
        private const val COLUMN_CONSTITUICAO = "constituicao"
        private const val COLUMN_CARISMA = "carisma"
        private const val COLUMN_SABEDORIA = "sabedoria"
    }

    // Método chamado para criar o banco de dados
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_CLASSE TEXT,
                $COLUMN_FORCA INTEGER,
                $COLUMN_DESTRANZA INTEGER,
                $COLUMN_INTELIGENCIA INTEGER,
                $COLUMN_CONSTITUICAO INTEGER,
                $COLUMN_CARISMA INTEGER,
                $COLUMN_SABEDORIA INTEGER
            )
        """.trimIndent()
        db?.execSQL(createTable)
    }

    // Método chamado quando a versão do banco de dados é atualizada
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Método para adicionar um personagem ao banco de dados
    fun addPersonagem(personagem: PersonagemData) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, personagem.nome)
            put(COLUMN_CLASSE, personagem.classe)
            put(COLUMN_FORCA, personagem.forca)
            put(COLUMN_DESTRANZA, personagem.destreza)
            put(COLUMN_INTELIGENCIA, personagem.inteligencia)
            put(COLUMN_CONSTITUICAO, personagem.constituicao)
            put(COLUMN_CARISMA, personagem.carisma)
            put(COLUMN_SABEDORIA, personagem.sabedoria)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Aqui você pode adicionar outros métodos, como recuperar, atualizar e deletar personagens.
}
