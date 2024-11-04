
package ListaRaca

import Personagem

class Elfo : Raca {
    override val nome = "Elfo"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.pontosDistribuidosDestreza += 2
        personagem.pontosDistribuidosInteligencia += 1
    }
}
