package ControleDeFluxo

/*
Imprima os números de 1 a 50 na mesma linha em ordem decrescente. Saída esperada:
“50 49 48 47 .. .. .. 2 1”
 */

fun main() {

    for (numero in 50 downTo 1) {
        print("$numero ")
    }

}