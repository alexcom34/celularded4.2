package com.example.celularded4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.celularded4.ui.theme.data.PersonagemData

import DatabaseHelper

class ConfirmacaoActivity : AppCompatActivity() {

    private lateinit var tvDadosPersonagem: TextView
    private lateinit var btnCriarNovo: Button
    private lateinit var btnEncerrar: Button
    private lateinit var btnSalvar: Button
    private lateinit var btnConsultar: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacao)

        tvDadosPersonagem = findViewById(R.id.tv_dados_personagem)
        btnCriarNovo = findViewById(R.id.btn_criar_novo)
        btnEncerrar = findViewById(R.id.btn_encerrar)
        btnSalvar = findViewById(R.id.btnSalvar)
        btnConsultar = findViewById(R.id.btnConsultar)

        databaseHelper = DatabaseHelper(this)

        val nome = intent.getStringExtra("NOME_PERSONAGEM") ?: "Nome não disponível"
        val atributos = intent.getStringExtra("ATRIBUTOS") ?: "Atributos não disponíveis"
        val pontosVida = intent.getIntExtra("PONTOS_DE_VIDA", 0)

        tvDadosPersonagem.text = """
            Personagem Criado:
            Nome: $nome
            $atributos
            Pontos de Vida: $pontosVida
        """.trimIndent()

        btnCriarNovo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnEncerrar.setOnClickListener {
            finishAffinity()
        }

        btnSalvar.setOnClickListener {
            salvarPersonagem(nome, atributos, pontosVida)
        }

        btnConsultar.setOnClickListener {
            consultarPersonagens()
        }
    }

    private fun salvarPersonagem(nome: String, atributos: String, pontosVida: Int) {
        val atributosArray = atributos.split("\n").map { it.split(": ")[1].toInt() }

        if (atributosArray.size < 6) {
            Toast.makeText(this, "Erro: Atributos incompletos", Toast.LENGTH_SHORT).show()
            return
        }

        val forca = atributosArray[0]
        val destreza = atributosArray[1]
        val inteligencia = atributosArray[2]
        val constituicao = atributosArray[3]
        val carisma = atributosArray[4]
        val sabedoria = atributosArray[5]

        val personagemData = PersonagemData(
            id = System.currentTimeMillis().toString(),
            nome = nome,
            classe = "Classe do Personagem",
            forca = forca,
            destreza = destreza,
            inteligencia = inteligencia,
            constituicao = constituicao,
            carisma = carisma,
            sabedoria = sabedoria
        )

        databaseHelper.addPersonagem(personagemData)
        Toast.makeText(this, "Personagem salvo com sucesso!", Toast.LENGTH_SHORT).show()
    }

    private fun consultarPersonagens() {
        val personagens = databaseHelper.getAllPersonagens()
        val builder = StringBuilder()
        for (personagem in personagens) {
            builder.append("ID: ${personagem.id}\n")
            builder.append("Nome: ${personagem.nome}\n")
            builder.append("Classe: ${personagem.classe}\n")
            builder.append("Força: ${personagem.forca}\n")
            builder.append("Destreza: ${personagem.destreza}\n")
            builder.append("Inteligência: ${personagem.inteligencia}\n")
            builder.append("Constituição: ${personagem.constituicao}\n")
            builder.append("Carisma: ${personagem.carisma}\n")
            builder.append("Sabedoria: ${personagem.sabedoria}\n\n")
        }
        tvDadosPersonagem.text = builder.toString()
    }
}
