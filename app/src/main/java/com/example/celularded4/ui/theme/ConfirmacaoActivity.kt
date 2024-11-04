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
    private lateinit var btnSalvar: Button // Novo botão para salvar o personagem
    private lateinit var databaseHelper: DatabaseHelper // Para interagir com o banco de dados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacao)

        // Inicializando os componentes da interface
        tvDadosPersonagem = findViewById(R.id.tv_dados_personagem)
        btnCriarNovo = findViewById(R.id.btn_criar_novo)
        btnEncerrar = findViewById(R.id.btn_encerrar)
        btnSalvar = findViewById(R.id.btnSalvar)

        // Inicializando o DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        // Recebendo os dados do personagem
        val nome = intent.getStringExtra("NOME_PERSONAGEM") ?: "Nome não disponível"
        val atributos = intent.getStringExtra("ATRIBUTOS") ?: "Atributos não disponíveis"
        val pontosVida = intent.getIntExtra("PONTOS_DE_VIDA", 0)

        // Exibindo os dados do personagem
        tvDadosPersonagem.text = """
            Personagem Criado:
            Nome: $nome
            $atributos
            Pontos de Vida: $pontosVida
        """.trimIndent()

        // Configurando o botão para criar novo personagem
        btnCriarNovo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Encerra a ConfirmacaoActivity
        }

        // Configurando o botão para encerrar o programa
        btnEncerrar.setOnClickListener {
            finishAffinity() // Encerra todas as atividades do aplicativo
        }

        // Configurando o botão para salvar o personagem
        btnSalvar.setOnClickListener {
            salvarPersonagem(nome, atributos, pontosVida)
        }
    }

    private fun salvarPersonagem(nome: String, atributos: String, pontosVida: Int) {
        // Aqui você deve coletar os atributos individuais do personagem
        // Vamos supor que os atributos sejam recebidos como uma String separada por vírgulas
        val atributosArray = atributos.split(",").map { it.trim() }

        if (atributosArray.size < 6) { // Verifique se todos os atributos foram passados
            Toast.makeText(this, "Erro: Atributos incompletos", Toast.LENGTH_SHORT).show()
            return
        }

        // Coletando os valores dos atributos
        val forca = atributosArray[0].toInt()
        val destreza = atributosArray[1].toInt()
        val inteligencia = atributosArray[2].toInt()
        val constituicao = atributosArray[3].toInt()
        val carisma = atributosArray[4].toInt()
        val sabedoria = atributosArray[5].toInt()

        // Criação do objeto PersonagemData para inserir no banco de dados
        val personagemData = PersonagemData(
            id = generateId(), // Implementar lógica para gerar um ID único
            nome = nome,
            classe = "Classe do Personagem", // Você pode precisar passar a classe também
            forca = forca,
            destreza = destreza,
            inteligencia = inteligencia,
            constituicao = constituicao,
            carisma = carisma,
            sabedoria = sabedoria
        )

        // Salvar personagemData no banco de dados
        databaseHelper.addPersonagem(personagemData) // Usando addPersonagem em vez de insertPersonagem
        Toast.makeText(this, "Personagem salvo com sucesso!", Toast.LENGTH_SHORT).show()
    }

    private fun generateId(): String {
        // Lógica para gerar um ID único
        return System.currentTimeMillis().toString() // Exemplo simples de ID baseado no tempo
    }
}
