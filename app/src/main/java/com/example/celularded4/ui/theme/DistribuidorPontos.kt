class DistribuidorPontos {
    private val basePoints = 8
    private val maxPoints = 15
    private val totalPoints = 27

    var forca: Int = basePoints
        private set
    var destreza: Int = basePoints
        private set
    var inteligencia: Int = basePoints
        private set
    var constituicao: Int = basePoints
        private set
    var carisma: Int = basePoints
        private set
    var sabedoria: Int = basePoints
        private set

    private var pontosDistribuidos = 0

    fun adicionarPontos(atributo: String, pontos: Int): Boolean {
        val novoValor = when (atributo.toLowerCase()) {
            "forca" -> forca + pontos
            "destreza" -> destreza + pontos
            "inteligencia" -> inteligencia + pontos
            "constituicao" -> constituicao + pontos
            "carisma" -> carisma + pontos
            "sabedoria" -> sabedoria + pontos
            else -> return false
        }

        if (novoValor > maxPoints || pontosDistribuidos + pontos > totalPoints) {
            return false
        }

        when (atributo.toLowerCase()) {
            "forca" -> forca += pontos
            "destreza" -> destreza += pontos
            "inteligencia" -> inteligencia += pontos
            "constituicao" -> constituicao += pontos
            "carisma" -> carisma += pontos
            "sabedoria" -> sabedoria += pontos
        }

        pontosDistribuidos += pontos
        return true
    }

    fun pontosRestantes(): Int {
        return totalPoints - pontosDistribuidos
    }

    fun resetarPontos() {
        forca = basePoints
        destreza = basePoints
        inteligencia = basePoints
        constituicao = basePoints
        carisma = basePoints
        sabedoria = basePoints
        pontosDistribuidos = 0
    }
}
