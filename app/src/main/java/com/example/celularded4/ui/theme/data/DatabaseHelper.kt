import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.celularded4.ui.theme.data.PersonagemData

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "meubanco.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "personagens"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "nome"
        private const val COLUMN_CLASSE = "classe"
        private const val COLUMN_FORCA = "forca"
        private const val COLUMN_DESTREZA = "destreza"
        private const val COLUMN_INTELIGENCIA = "inteligencia"
        private const val COLUMN_CONSTITUICAO = "constituicao"
        private const val COLUMN_CARISMA = "carisma"
        private const val COLUMN_SABEDORIA = "sabedoria"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_CLASSE TEXT,
                $COLUMN_FORCA INTEGER,
                $COLUMN_DESTREZA INTEGER,
                $COLUMN_INTELIGENCIA INTEGER,
                $COLUMN_CONSTITUICAO INTEGER,
                $COLUMN_CARISMA INTEGER,
                $COLUMN_SABEDORIA INTEGER
            )
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addPersonagem(personagem: PersonagemData) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, personagem.nome)
            put(COLUMN_CLASSE, personagem.classe)
            put(COLUMN_FORCA, personagem.forca)
            put(COLUMN_DESTREZA, personagem.destreza)
            put(COLUMN_INTELIGENCIA, personagem.inteligencia)
            put(COLUMN_CONSTITUICAO, personagem.constituicao)
            put(COLUMN_CARISMA, personagem.carisma)
            put(COLUMN_SABEDORIA, personagem.sabedoria)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllPersonagens(): List<PersonagemData> {
        val personagens = mutableListOf<PersonagemData>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val classe = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLASSE))
                val forca = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORCA))
                val destreza = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DESTREZA))
                val inteligencia = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELIGENCIA))
                val constituicao = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSTITUICAO))
                val carisma = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARISMA))
                val sabedoria = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SABEDORIA))

                val personagem = PersonagemData(
                    id.toString(), // Converting ID to String to match the previous data type
                    nome, classe, forca, destreza, inteligencia, constituicao, carisma, sabedoria
                )
                personagens.add(personagem)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return personagens
    }
}
