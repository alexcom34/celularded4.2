import com.example.celularded4.ui.theme.MensagensErro

class CalculadorPontosVida(private val constituicao: Int) {

    fun calcularPontosVida(): Int {
        val pontosBase = 10
        val pontosAdicionais = when (constituicao) {
            in 1..5 -> -5
            in 6..7 -> -2
            in 8..9 -> -1
            in 10..11 -> 0
            in 12..13 -> 1
            in 14..15 -> 2
            in 16..17 -> 3
            in 18..19 -> 4
            in 20..21 -> 5
            in 22..23 -> 6
            in 24..25 -> 7
            in 26..27 -> 8
            in 28..29 -> 9
            30 -> 10
            else -> throw IllegalArgumentException(MensagensErro.CONSTITUICAO_INVALIDA) // Mensagem de erro atualizada
        }

        return pontosBase + pontosAdicionais
    }
}
