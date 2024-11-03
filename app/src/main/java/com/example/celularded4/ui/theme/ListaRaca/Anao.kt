package ListaRaca

import Personagem

class Anao : Raca {
    override val nome = "Anao"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.constituicao += 2
        personagem.sabedoria += 1
    }
}
