package ListaRaca

import Personagem

class Orc : Raca {
    override val nome = "Orc"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.forca += 2
        personagem.constituicao += 1
    }
}
