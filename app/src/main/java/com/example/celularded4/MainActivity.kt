package com.example.celularded4

import DistribuidorPontos
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ListaRaca.*
import Personagem





class MainActivity : AppCompatActivity() {

    private lateinit var etNomePersonagem: EditText
    private lateinit var spinnerRaca: Spinner
    private lateinit var tvPontosVida: TextView
    private lateinit var btnConfirmar: Button
    private lateinit var btnResetar: Button
    private lateinit var tvAtributos: TextView
    private lateinit var tvPontosRestantes: TextView // Exibe os pontos restantes
    private lateinit var etForca: EditText
    private lateinit var etDestreza: EditText
    private lateinit var etInteligencia: EditText
    private lateinit var etConstituicao: EditText
    private lateinit var etCarisma: EditText
    private lateinit var etSabedoria: EditText
    private lateinit var distribuidorPontos: DistribuidorPontos
    private lateinit var personagem: Personagem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando os componentes da interface
        etNomePersonagem = findViewById(R.id.et_nome_personagem)
        spinnerRaca = findViewById(R.id.spinner_raca)
        tvPontosVida = findViewById(R.id.tv_pontos_vida)
        btnConfirmar = findViewById(R.id.btn_confirmar)
        btnResetar = findViewById(R.id.btn_resetar) // Botão para resetar pontos
        tvAtributos = findViewById(R.id.tv_atributos)
        tvPontosRestantes = findViewById(R.id.tv_pontos_restantes) // Pontos restantes

        etForca = findViewById(R.id.et_forca)
        etDestreza = findViewById(R.id.et_destreza)
        etInteligencia = findViewById(R.id.et_inteligencia)
        etConstituicao = findViewById(R.id.et_constituicao)
        etCarisma = findViewById(R.id.et_carisma)
        etSabedoria = findViewById(R.id.et_sabedoria)

        // Inicializando o distribuidor de pontos e exibindo os pontos restantes
        distribuidorPontos = DistribuidorPontos()
        tvPontosRestantes.text = "Pontos Restantes: ${distribuidorPontos.pontosRestantes()}"

        // Configurando o Spinner com as raças
        setupRacasSpinner()

        // Configurando botões de ação
        btnConfirmar.setOnClickListener { onConfirmarClicked() }
        btnResetar.setOnClickListener { onResetarClicked() }
    }

    private fun setupRacasSpinner() {
        val racas = arrayOf("Draconato", "Elfo", "Humano", "Orc", "Anao")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, racas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRaca.adapter = adapter
    }

    private fun onConfirmarClicked() {
        val nome = etNomePersonagem.text.toString()
        val racaSelecionada = spinnerRaca.selectedItem.toString()

        val raca: Raca? = when (racaSelecionada) {
            "Draconato" -> Draconato()
            "Elfo" -> Elfo()
            "Humano" -> Humano()
            "Orc" -> Orc()
            "Anao" -> Anao()
            else -> null
        }

        if (raca != null && nome.isNotBlank()) {
            // Inicializando o distribuidor de pontos
            distribuidorPontos.resetarPontos()  // Garantir que o distribuidor está resetado
            try {
                // Adicionando pontos com o distribuidor
                distribuidorPontos.adicionarPontos("forca", etForca.text.toString().toIntOrNull() ?: 0)
                distribuidorPontos.adicionarPontos("destreza", etDestreza.text.toString().toIntOrNull() ?: 0)
                distribuidorPontos.adicionarPontos("inteligencia", etInteligencia.text.toString().toIntOrNull() ?: 0)
                distribuidorPontos.adicionarPontos("constituicao", etConstituicao.text.toString().toIntOrNull() ?: 0)
                distribuidorPontos.adicionarPontos("carisma", etCarisma.text.toString().toIntOrNull() ?: 0)
                distribuidorPontos.adicionarPontos("sabedoria", etSabedoria.text.toString().toIntOrNull() ?: 0)

                // Criar o personagem com os pontos distribuídos
                personagem = Personagem(nome, raca)

                // Atualiza os pontos de vida após a distribuição de pontos
                personagem.atualizarPontosDeVida()

                // Atualiza a UI com os atributos e pontos de vida
                tvPontosVida.text = "Pontos de Vida: ${personagem.pontosDeVida}"
                tvAtributos.text = "Atributos do Personagem:\n${personagem.exibirAtributos()}"
                tvPontosRestantes.text = "Pontos Restantes: ${distribuidorPontos.pontosRestantes()}" // Atualizar pontos restantes
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Erro ao processar os pontos: ${e.message}", Toast.LENGTH_LONG).show()
            }
        } else {
            tvAtributos.text = "Por favor, preencha todos os campos."
        }
    }




    private fun onResetarClicked() {
        distribuidorPontos.resetarPontos()
        tvPontosRestantes.text = "Pontos Restantes: ${distribuidorPontos.pontosRestantes()}"

        // Reseta os campos dos atributos na UI
        etForca.setText("")
        etDestreza.setText("")
        etInteligencia.setText("")
        etConstituicao.setText("")
        etCarisma.setText("")
        etSabedoria.setText("")

        // Feedback visual para o reset
        tvAtributos.text = "Distribuição de pontos redefinida."
    }
}
