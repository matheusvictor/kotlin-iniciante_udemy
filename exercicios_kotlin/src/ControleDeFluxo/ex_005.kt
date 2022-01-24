package ControleDeFluxo

/*
Usando a resolução do exercício 003, não imprima os números múltiplos de 5.
*/

fun main() {

    for (numero in 1..50) {
        if (numero % 5 != 0) print("$numero ")
    }

}