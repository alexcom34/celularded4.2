import ListaRaca.Raca

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

    // Getters
    fun getForca(): Int = forcaFinal()
    fun getDestreza(): Int = destrezaFinal()
    fun getInteligencia(): Int = inteligenciaFinal()
    fun getConstituicao(): Int = constituicaoFinal()
    fun getCarisma(): Int = carismaFinal()
    fun getSabedoria(): Int = sabedoriaFinal()

    // Setters
    fun setForca(forca: Int) {
        pontosDistribuidosForca = forca - 8
    }

    fun setDestreza(destreza: Int) {
        pontosDistribuidosDestreza = destreza - 8
    }

    fun setInteligencia(inteligencia: Int) {
        pontosDistribuidosInteligencia = inteligencia - 8
    }

    fun setConstituicao(constituicao: Int) {
        pontosDistribuidosConstituicao = constituicao - 8
    }

    fun setCarisma(carisma: Int) {
        pontosDistribuidosCarisma = carisma - 8
    }

    fun setSabedoria(sabedoria: Int) {
        pontosDistribuidosSabedoria = sabedoria - 8
    }

    fun distribuirPontos() {
        // (Seu código existente para distribuir pontos)
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
