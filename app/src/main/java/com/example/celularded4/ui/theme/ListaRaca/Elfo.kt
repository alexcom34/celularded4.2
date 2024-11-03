package ListaRaca

import Personagem

class Elfo : Raca {
    override val nome = "Elfo"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
        personagem.inteligencia += 1
    }
}
