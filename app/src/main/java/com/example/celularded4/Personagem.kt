import ListaRaca.*
import com.example.celularded4.ui.theme.MensagensErro


class Personagem(
    var nome: String,
    raca: Raca
) {
    var forca: Int = 8
    var destreza: Int = 8
    var inteligencia: Int = 8
    var constituicao: Int = 8
    var carisma: Int = 8
    var sabedoria: Int = 8
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
                    "forca" -> forca += pontos
                    "destreza" -> destreza += pontos
                    "inteligencia" -> inteligencia += pontos
                    "constituicao" -> {
                        constituicao += pontos
                        atualizarPontosDeVida() // Atualiza os pontos de vida se a constituição mudar
                    }
                    "carisma" -> carisma += pontos
                    "sabedoria" -> sabedoria += pontos
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
        val calculador = CalculadorPontosVida(constituicao)
        pontosDeVida = calculador.calcularPontosVida()
    }

    internal fun exibirAtributos() {
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
