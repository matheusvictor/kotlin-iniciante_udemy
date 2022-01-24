package ControleDeFluxo

/*
Faça a soma de todos os números no intervalo de 1 a 500
*/

fun main() {
    var total = 0

    for (numero in 1..500) {
        total += numero
    }

    print("A soma de todos os número entre 1 e 500 é igual a $total")

}