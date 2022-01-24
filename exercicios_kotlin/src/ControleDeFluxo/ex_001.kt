package ControleDeFluxo

/*
Escreva um programa para ajudar a empresa XPTO calcular o bônus que os funcionários receberão no
final do ano. Os bônus são classificados por cargo.
a. Gerentes recebem R$ 2.000,00
b. Coordenadores recebem R$ 1.500,00
c. Engenheiros de software recebem R$ 1.000,00
d. Estagiários recebem R$ 500,00
 */

object CARGOS {
    const val GERENTE = "Gerente"
    const val COORDENADOR = "Coordenador"
    const val ENG_SOFT = "Engenheiro de software"
    const val ESTAGIARIO = "Estagiário"
}

fun calculaBonus(cargo: String): Float {

    return if (cargo == CARGOS.GERENTE) {
        2000f
    } else if (cargo == CARGOS.COORDENADOR) {
        1500f
    } else if (cargo == CARGOS.ENG_SOFT) {
        1000f
    } else if (cargo == CARGOS.ESTAGIARIO) {
        500f
    } else {
        0f
    }
}

fun main() {

    val bonus = calculaBonus("Gerente")
    println(bonus)

}