class DistribuidorPontos {
    private val basePoints = 8
    private val maxPoints = 15
    private val totalPoints = 27

    private var pontosDistribuidos = 0

    private val atributos = mutableMapOf(
        "forca" to 0,
        "destreza" to 0,
        "inteligencia" to 0,
        "constituicao" to 0,
        "carisma" to 0,
        "sabedoria" to 0
    )

    fun adicionarPontos(atributo: String, pontos: Int): Boolean {
        val atributoLower = atributo.toLowerCase()
        if (!atributos.containsKey(atributoLower)) return false

        val novoValor = atributos[atributoLower]!! + pontos

        // Verifica se a soma não ultrapassa os limites
        if ((novoValor + basePoints) > maxPoints || (pontosDistribuidos + pontos) > totalPoints) {
            return false
        }

        // Adiciona os pontos ao atributo
        atributos[atributoLower] = novoValor
        pontosDistribuidos += pontos
        return true
    }

    fun pontosRestantes(): Int {
        return totalPoints - pontosDistribuidos
    }

    fun resetarPontos() {
        for (key in atributos.keys) {
            atributos[key] = 0 // Zera os pontos adicionais
        }
        pontosDistribuidos = 0
    }

    fun getAtributoTotal(atributo: String): Int {
        // Retorna o valor total de um atributo (base + pontos adicionais)
        return basePoints + (atributos[atributo.toLowerCase()] ?: 0)
    }

    fun exibirAtributos(): String {
        // Exibe todos os atributos com base e pontos adicionais somados
        return atributos.map { "${it.key.capitalize()}: ${getAtributoTotal(it.key)}" }.joinToString("\n")
    }
}