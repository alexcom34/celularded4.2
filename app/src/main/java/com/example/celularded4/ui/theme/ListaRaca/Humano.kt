package ListaRaca

import Personagem

class Humano : Raca {
    override val nome = "Humano"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.pontosDistribuidosForca += 1
        personagem.pontosDistribuidosDestreza += 1
        personagem.pontosDistribuidosConstituicao += 1
        personagem.pontosDistribuidosInteligencia += 1
    }
}
