import ListaRaca.*
import com.example.celularded4.ui.theme.MensagensErro

class Personagem(
    var nome: String,
    raca: Raca
) {
    // Atributos com 8 de base
    var pontosDistribuidosForca: Int = 0
    var pontosDistribuidosDestreza: Int = 0
    var pontosDistribuidosInteligencia: Int = 0
    var pontosDistribuidosConstituicao: Int = 0
    var pontosDistribuidosCarisma: Int = 0
    var pontosDistribuidosSabedoria: Int = 0

    var pontosDeVida: Int = 0
        private set

    var raca: Raca = raca

    private val distribuidorPontos = DistribuidorPontos()

    init {
        // Aplica os bônus da raça no momento da criação
        raca.aplicarBonus(this)
        atualizarPontosDeVida()
    }

    fun distribuirPontos() {
        var pontosRestantes = 27
        val atributos = listOf("forca", "destreza", "inteligencia", "constituicao", "carisma", "sabedoria")

        while (pontosRestantes > 0) {
            exibirAtributos()
            println("Pontos restantes: $pontosRestantes")
            print("Escolha um atributo para aumentar (${atributos.joinToString(", ")}): ")
            val atributo = readLine() ?: ""
            if (atributo !in atributos) {
                println(MensagensErro.ATRIBUTO_INVALIDO) // Mensagem de erro atualizada
                continue
            }
            print("Quantos pontos deseja adicionar a $atributo? ")
            val pontos = readLine()?.toIntOrNull() ?: 0

            if (pontos <= 0 || pontos > pontosRestantes) {
                println(MensagensErro.NUMERO_INVALIDO_DE_PONTOS) // Mensagem de erro atualizada
                continue
            }

            val sucesso = distribuidorPontos.adicionarPontos(atributo, pontos)

            if (sucesso) {
                when (atributo) {
                    "forca" -> pontosDistribuidosForca += pontos
                    "destreza" -> pontosDistribuidosDestreza += pontos
                    "inteligencia" -> pontosDistribuidosInteligencia += pontos
                    "constituicao" -> {
                        pontosDistribuidosConstituicao += pontos
                        atualizarPontosDeVida() // Atualiza os pontos de vida se a constituição mudar
                    }
                    "carisma" -> pontosDistribuidosCarisma += pontos
                    "sabedoria" -> pontosDistribuidosSabedoria += pontos
                }
                pontosRestantes -= pontos
            } else {
                println(MensagensErro.DISTRIBUICAO_INVALIDA) // Mensagem de erro atualizada
            }
        }

        println("Distribuição de pontos concluída!")
        exibirAtributos()
    }

    fun atualizarPontosDeVida() {
        val calculador = CalculadorPontosVida(constituicaoFinal())
        pontosDeVida = calculador.calcularPontosVida()
    }

    private fun forcaFinal(): Int = 8 + pontosDistribuidosForca
    private fun destrezaFinal(): Int = 8 + pontosDistribuidosDestreza
    private fun inteligenciaFinal(): Int = 8 + pontosDistribuidosInteligencia
    private fun constituicaoFinal(): Int = 8 + pontosDistribuidosConstituicao
    private fun carismaFinal(): Int = 8 + pontosDistribuidosCarisma
    private fun sabedoriaFinal(): Int = 8 + pontosDistribuidosSabedoria

    internal fun exibirAtributos(): String {
        return "Atributos de $nome:\n" +
                "Raça: ${raca.nome}\n" +
                "Força: ${forcaFinal()}\n" +
                "Destreza: ${destrezaFinal()}\n" +
                "Inteligência: ${inteligenciaFinal()}\n" +
                "Constituição: ${constituicaoFinal()}\n" +
                "Carisma: ${carismaFinal()}\n" +
                "Sabedoria: ${sabedoriaFinal()}\n" +
                "Pontos de Vida: $pontosDeVida"
    }
}
