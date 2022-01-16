class Carro(val modelo: String, val velocidadeMaxima: Int) {

    var velocidadeAtual: Int = 0
        set(value) {
            if (value > velocidadeMaxima) {
                throw Exception("Velocidade acima da permitida!")
            } else {
                field = value
            }
        }
}

fun main(args: Array<String>) {

    val carro = Carro("Fiat Uno", 220)
    carro.velocidadeAtual = 0

    println(carro.velocidadeAtual)

}