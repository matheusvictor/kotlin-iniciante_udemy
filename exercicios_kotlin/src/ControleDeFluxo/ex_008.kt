package ControleDeFluxo

/*
Considere uma caixa d’água de 2 mil litros. Rômulo gostaria de encher a caixa d’água com balões de água
de 7 litros. Quantos balões cabem na caixa d’água sem que o volume seja excedido?
*/

fun main() {

    val capacidadeCaixa = 2000
    val capacidadeBalao = 7
    var numeroBaloes = 0

    while ((capacidadeBalao * numeroBaloes) + capacidadeBalao < capacidadeCaixa)
        numeroBaloes++

    println("Cabem $numeroBaloes balões na caixa d'água.")
}