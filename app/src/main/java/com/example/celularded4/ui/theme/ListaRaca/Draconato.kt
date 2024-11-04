package ListaRaca

import Personagem

class Draconato : Raca {
    override val nome = "Draconato"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.pontosDistribuidosForca += 2
        personagem.pontosDistribuidosConstituicao += 1
    }
}
