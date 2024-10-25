package com.example.celularded4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nomeEditText: EditText
    private lateinit var spinnerRaca: Spinner
    private lateinit var btnConfirmar: Button
    private lateinit var tvPontosVida: TextView

    // Adicione SeekBars e TextViews para os atributos
    private lateinit var seekBarForca: SeekBar
    private lateinit var tvForcaValor: TextView
    private lateinit var seekBarDestreza: SeekBar
    private lateinit var tvDestrezaValor: TextView
    private lateinit var seekBarInteligencia: SeekBar
    private lateinit var tvInteligenciaValor: TextView
    private lateinit var seekBarConstituicao: SeekBar
    private lateinit var tvConstituicaoValor: TextView
    private lateinit var seekBarCarisma: SeekBar
    private lateinit var tvCarismaValor: TextView
    private lateinit var seekBarSabedoria: SeekBar
    private lateinit var tvSabedoriaValor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando as views
        nomeEditText = findViewById(R.id.et_nome_personagem)
        spinnerRaca = findViewById(R.id.spinner_raca)
        btnConfirmar = findViewById(R.id.btn_confirmar)
        tvPontosVida = findViewById(R.id.tv_pontos_vida)

        // Inicializando SeekBars e TextViews
        seekBarForca = findViewById(R.id.seekBar_forca)
        tvForcaValor = findViewById(R.id.tv_forca_valor)

        seekBarDestreza = findViewById(R.id.seekBar_destreza)
        tvDestrezaValor = findViewById(R.id.tv_destreza_valor)

        seekBarInteligencia = findViewById(R.id.seekBar_inteligencia)
        tvInteligenciaValor = findViewById(R.id.tv_inteligencia_valor)

        seekBarConstituicao = findViewById(R.id.seekBar_constituicao)
        tvConstituicaoValor = findViewById(R.id.tv_constituicao_valor)

        seekBarCarisma = findViewById(R.id.seekBar_carisma)
        tvCarismaValor = findViewById(R.id.tv_carisma_valor)

        seekBarSabedoria = findViewById(R.id.seekBar_sabedoria)
        tvSabedoriaValor = findViewById(R.id.tv_sabedoria_valor)

        // Lógica para atualizar o valor dos atributos conforme o SeekBar é movido
        seekBarForca.setOnSeekBarChangeListener(createSeekBarChangeListener(tvForcaValor))
        seekBarDestreza.setOnSeekBarChangeListener(createSeekBarChangeListener(tvDestrezaValor))
        seekBarInteligencia.setOnSeekBarChangeListener(createSeekBarChangeListener(tvInteligenciaValor))
        seekBarConstituicao.setOnSeekBarChangeListener(createSeekBarChangeListener(tvConstituicaoValor))
        seekBarCarisma.setOnSeekBarChangeListener(createSeekBarChangeListener(tvCarismaValor))
        seekBarSabedoria.setOnSeekBarChangeListener(createSeekBarChangeListener(tvSabedoriaValor))

        // Configurando o botão para confirmar a distribuição
        btnConfirmar.setOnClickListener {
            // Lógica para o que acontece quando o botão é clicado
        }
    }

    private fun createSeekBarChangeListener(tv: TextView): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tv.text = "${tv.text.split(":")[0]}: ${progress + 8}" // +8 para incluir a base
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }
    }
}
