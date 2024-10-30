package com.example.celularded4

import CalculadorPontosVida
import DistribuidorPontos
import ListaRaca.Raca

class Personagem(
    val nome: String,
    val raca: Raca,
    forcaInicial: Int,
    destrezaInicial: Int,
    inteligenciaInicial: Int,
    constituicaoInicial: Int,
    carismaInicial: Int,
    sabedoriaInicial: Int
) {
    // Atributos com valores iniciais
    var forca: Int = forcaInicial
        private set
    var destreza: Int = destrezaInicial
        private set
    var inteligencia: Int = inteligenciaInicial
        private set
    var constituicao: Int = constituicaoInicial
        private set
    var carisma: Int = carismaInicial
        private set
    var sabedoria: Int = sabedoriaInicial
        private set

    // Cálculo de pontos de vida usando a classe CalculadorPontosVida
    var pontosDeVida: Int = 0
        private set

    private val distribuidorPontos = DistribuidorPontos()

    init {
        // Aplica os bônus da raça no momento da criação
        raca.aplicarBonus(this)

        atualizarPontosDeVida()
    }

    private fun atualizarPontosDeVida() {
        val calculador = CalculadorPontosVida(constituicao)
        pontosDeVida = calculador.calcularPontosVida()
    }

    fun distribuirPontos(atributo: String, pontos: Int): Boolean {
        val sucesso = distribuidorPontos.adicionarPontos(atributo, pontos)
        if (sucesso) {
            when (atributo) {
                "forca" -> forca += pontos
                "destreza" -> destreza += pontos
                "inteligencia" -> inteligencia += pontos
                "constituicao" -> {
                    constituicao += pontos
                    atualizarPontosDeVida()
                }
                "carisma" -> carisma += pontos
                "sabedoria" -> sabedoria += pontos
            }
            return true
        }
        return false
    }

    fun exibirAtributos() {
        println("Atributos de $nome:")
        println("Raça: ${raca.nome}")
        println("Força: $forca")
        println("Destreza: $destreza")
        println("Inteligência: $inteligencia")
        println("Constituição: $constituicao")
        println("Carisma: $carisma")
        println("Sabedoria: $sabedoria")
        println("Pontos de Vida: $pontosDeVida")
    }
}
