package ControleDeFluxo

/*
Escreva um programa capaz de receber um número inteiro N e imprimir uma escada de tamanho N
usando o caracter #
*/

fun main() {

    val numero = readLine()?.toInt()

    for(i in 1..numero!!) {
        for(j in 1..i){
            print("#")
        }
        println()
    }
}
