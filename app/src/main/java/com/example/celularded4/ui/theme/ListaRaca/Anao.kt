package ListaRaca

import Personagem

class Anao : Raca {
    override val nome = "Anao"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.pontosDistribuidosConstituicao += 2
        personagem.pontosDistribuidosSabedoria += 1
    }
}
