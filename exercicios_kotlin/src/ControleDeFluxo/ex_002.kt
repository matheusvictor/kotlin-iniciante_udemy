package ControleDeFluxo

/*
Modifique o primeiro exercício para considerar o tempo de experiência no cálculo de bônus.
a. Gerentes com menos de 2 anos de experiência recebem R$ 2.000,00, caso contrário recebem R$
3.000,00
b. Coordenadores com menos de 1 ano de experiência recebem R$ 1.500,00, caso contrário recebem R$
1.800,00
c. Engenheiros de software recebem R$ 1.000,00
d. Estagiários recebem R$ 500,00
 */

fun calculaBonus(cargo: String, tempoServico: Int): Float {
    var bonus = 0f
    if (cargo == CARGOS.GERENTE) {
        bonus = if (tempoServico < 2) {
            2000f
        } else {
            3000f
        }
    } else if (cargo == CARGOS.COORDENADOR) {
        bonus = if (tempoServico < 1) {
            1500f
        } else {
            1800f
        }
    } else if (cargo == CARGOS.ENG_SOFT) {
        bonus = 1000f
    } else if (cargo == CARGOS.ESTAGIARIO) {
        bonus = 500f
    }
    return bonus
}

fun calculaBonusWhen(cargo: String, tempoServico: Int): Float {
    val bonus = when (cargo) {
        CARGOS.GERENTE -> {
            if (tempoServico < 2) {
                2000f
            } else {
                3000f
            }
        }
        CARGOS.COORDENADOR -> {
            if (tempoServico < 1) {
                1500f
            } else {
                1800f
            }
        }
        CARGOS.ENG_SOFT -> {
            1000f
        }
        CARGOS.ESTAGIARIO -> {
            500f
        }
        else -> 0f
    }
    return bonus
}

fun main() {

    val bonus = calculaBonus("Estagiário", 1)
    println(bonus)

    val bonusWhen = calculaBonusWhen("Gerente", 1)
    println(bonusWhen)

}
