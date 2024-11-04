package ListaRaca

import Personagem

class Orc : Raca {
    override val nome = "Orc"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.pontosDistribuidosForca += 2
        personagem.pontosDistribuidosConstituicao += 1
    }
}
