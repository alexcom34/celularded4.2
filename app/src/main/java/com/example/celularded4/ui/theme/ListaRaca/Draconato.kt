package ListaRaca

import Personagem

class Draconato : Raca {
    override val nome = "Draconato"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.forca += 2
        personagem.constituicao += 1
    }
}
