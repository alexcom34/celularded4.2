package ListaRaca

import Personagem


interface Raca {
    val nome: String
    fun aplicarBonus(personagem: Personagem)
}
