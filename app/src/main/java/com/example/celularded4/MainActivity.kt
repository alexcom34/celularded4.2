package com.example.celularded4

import DistribuidorPontos
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ListaRaca.*
import Personagem
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var etNomePersonagem: EditText
    private lateinit var spinnerRaca: Spinner
    private lateinit var tvPontosVida: TextView
    private lateinit var btnConfirmar: Button
    private lateinit var btnResetar: Button
    private lateinit var tvAtributos: TextView
    private lateinit var tvPontosRestantes: TextView
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
        btnResetar = findViewById(R.id.btn_resetar)
        tvAtributos = findViewById(R.id.tv_atributos)
        tvPontosRestantes = findViewById(R.id.tv_pontos_restantes)

        etForca = findViewById(R.id.et_forca)
        etDestreza = findViewById(R.id.et_destreza)
        etInteligencia = findViewById(R.id.et_inteligencia)
        etConstituicao = findViewById(R.id.et_constituicao)
        etCarisma = findViewById(R.id.et_carisma)
        etSabedoria = findViewById(R.id.et_sabedoria)

        distribuidorPontos = DistribuidorPontos()
        tvPontosRestantes.text = "Pontos Restantes: ${distribuidorPontos.pontosRestantes()}"

        setupRacasSpinner()

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
            distribuidorPontos.resetarPontos()
            try {
                // Adicionando pontos com o distribuidor a partir dos valores digitados
                val forca = etForca.text.toString().toIntOrNull() ?: 0
                val destreza = etDestreza.text.toString().toIntOrNull() ?: 0
                val inteligencia = etInteligencia.text.toString().toIntOrNull() ?: 0
                val constituicao = etConstituicao.text.toString().toIntOrNull() ?: 0
                val carisma = etCarisma.text.toString().toIntOrNull() ?: 0
                val sabedoria = etSabedoria.text.toString().toIntOrNull() ?: 0

                val sucessoForca = distribuidorPontos.adicionarPontos("forca", forca)
                val sucessoDestreza = distribuidorPontos.adicionarPontos("destreza", destreza)
                val sucessoInteligencia = distribuidorPontos.adicionarPontos("inteligencia", inteligencia)
                val sucessoConstituicao = distribuidorPontos.adicionarPontos("constituicao", constituicao)
                val sucessoCarisma = distribuidorPontos.adicionarPontos("carisma", carisma)
                val sucessoSabedoria = distribuidorPontos.adicionarPontos("sabedoria", sabedoria)

                if (sucessoForca && sucessoDestreza && sucessoInteligencia && sucessoConstituicao && sucessoCarisma && sucessoSabedoria && distribuidorPontos.pontosRestantes() == 0) {
                    personagem = Personagem(nome, raca)
                    personagem.setForca(forca)
                    personagem.setDestreza(destreza)
                    personagem.setInteligencia(inteligencia)
                    personagem.setConstituicao(constituicao)
                    personagem.setCarisma(carisma)
                    personagem.setSabedoria(sabedoria)
                    personagem.atualizarPontosDeVida()

                    val intent = Intent(this, ConfirmacaoActivity::class.java).apply {
                        putExtra("NOME_PERSONAGEM", personagem.nome)
                        putExtra("ATRIBUTOS", personagem.exibirAtributos())
                        putExtra("PONTOS_DE_VIDA", personagem.pontosDeVida)
                    }
                    startActivity(intent)

                    tvAtributos.text = "Atributos do Personagem:\n${personagem.exibirAtributos()}"
                    tvPontosVida.text = "Pontos de Vida: ${personagem.pontosDeVida}"
                } else {
                    tvAtributos.text = "Distribua corretamente todos os pontos."
                }

                tvPontosRestantes.text = "Pontos Restantes: ${distribuidorPontos.pontosRestantes()}"
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

        etForca.setText("")
        etDestreza.setText("")
        etInteligencia.setText("")
        etConstituicao.setText("")
        etCarisma.setText("")
        etSabedoria.setText("")

        tvAtributos.text = "Distribuição de pontos redefinida."
    }
}
