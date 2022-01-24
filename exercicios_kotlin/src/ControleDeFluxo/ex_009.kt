package ControleDeFluxo

/*
FizzBuzz. Imprima os números de 1 a 50 em ordem crescente de acordo com a regra abaixo:
a. Quando um número for divisível por 3, imprimir Buzz.
b. Quando um número for divisível por 5, imprimir Fizz.
c. Quando um número for divisível por 3 e 5, imprimir FizzBuzz.
*/

fun main() {

    for (numero in 1..50) {
        if (numero % 3 == 0 && numero % 5 == 0) {
            print("FizzBuzz ")
        } else if (numero % 3 == 0) {
            print("Buzz ")
        } else if (numero % 5 == 0) {
            print("Fizz ")
        } else {
            print("$numero ")
        }
    }

}