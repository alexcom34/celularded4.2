package ListaRaca

import Personagem

class Humano : Raca {
    override val nome = "Humano"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.forca += 1
        personagem.destreza += 1
        personagem.constituicao += 1
        personagem.inteligencia += 1
    }
}
