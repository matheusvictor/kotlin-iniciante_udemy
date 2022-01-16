data class Ingredientes(val nome: String, val quantidade: Int)
data class Receita(val nome: String, val calorias: Double, val ingredientes: List<Ingredientes> = listOf())

fun temIngrediente(lista: List<Ingredientes>, nomeIgrediente: String): Boolean {
    return lista.filter { it.nome.equals(nomeIgrediente) }.any()
}

fun main() {

    val livroReceitas = listOf(
        Receita(
            "Lasanha", 1200.0,
            listOf(
                Ingredientes("Farinha", 1),
                Ingredientes("Presunto", 5),
                Ingredientes("Queijo", 10),
                Ingredientes("Molho de tomate", 2),
                Ingredientes(
                    "Manjerição", 3
                )
            ),
            // as receitas sem um listOf como parâmetro é por causa do valor definido como default
            // (uma lista vazia) no construtor da Classe
        ),
        Receita("Panqueca", 500.0),
        Receita("Omelete", 200.0),
        Receita("Parmegiana", 700.0),
        Receita("Sopa de feijão", 300.0),
        Receita(
            "Hamburguer", 2000.0,
            listOf(
                Ingredientes("Pão", 1),
                Ingredientes("Carne", 2),
                Ingredientes("Queijo", 1),
                Ingredientes("Catupiry", 1),
                Ingredientes("Bacon", 3),
                Ingredientes("Alface", 1),
                Ingredientes("Tomate", 1)
            )
        )
    )

    println("Tenho receitas na lista? ${if (livroReceitas.any()) "Sim" else "Não"}")

    println("Quantas receitas tenho na coleção? Tenho ${livroReceitas.count()} receita(s).")

    print("Tenho alguma receita de Lasanha? ")
    if (livroReceitas.any { it.nome.equals("Lasanha") }) {
        println("Sim. Tenho ${livroReceitas.count { it.nome.equals("Lasanha") }} receita(s) de Lasanha.")
    } else {
        println("Não.")
    }

    println("A primeira receita é: ${livroReceitas.first().nome}") //pega apenas o atributo nome do Objeto Receita
    println("A última receita é: ${livroReceitas.last().nome}")

    println("Qual a soma de calorias? ${livroReceitas.sumOf { it.calorias }} Kcal.")

    println("As duas primeiras receitas são: ")
    val duasPrimeiras = livroReceitas.take(2) // pega os dois primeiros elementos da lista
    for (receita in duasPrimeiras.withIndex()) { // pega, além do valor, o index (posição) dele
        println("${receita.index + 1}ª receita --> ${receita.value.nome}") // value.nome para considerar o atributo nome deste objeto
    }

    print("Sei fazer panquecas? ")
    val facoPanqueca =
        livroReceitas.filter { it.nome.equals("Panqueca") }
            .any() //filter retorna uma lista. any retorna um boolean se houver conteúdo nessa lista

    if (facoPanqueca) {
        println("Sim!")
    } else {
        println("Infelizmente não sei fazer panquecas.")
    }

    print("Sei fazer sushi? ")
    val facoSushi =
        livroReceitas.filter { it.nome.equals("Sushi") }.any()

    if (facoSushi) {
        println("Sim!")
    } else {
        println("Não.")
    }

    print("Quais são as comidas com mais de 500 calorias? ")

    // val maisQue500cal = livroReceitas.filter { it.calorias > 500 }.forEach { print("${it.nome} | ") }

    // Par (chave, valor) de comidas com mais de 500 calorias:
    livroReceitas.filter { it.calorias > 500 }
        .map { Pair(it.nome, it.calorias) }
        .forEach { print("${it.first} > ${it.second} | ") } // first e seconde fazem referência às chaves-valores do map

    print("\nQual a(s) receita(s) mais calórica(s)? ")
    val maiorCaloria = livroReceitas.maxOf { it.calorias }
    val maisCaloricas = livroReceitas.filter { it.calorias == maiorCaloria }.forEach { print("${it.nome} | ") }

    print("\nQual a(s) receita(s) menos calórica(s)? ")
    val menorCaloria = livroReceitas.minOf { it.calorias }
    val menosCaloricas = livroReceitas.filter { it.calorias == menorCaloria }.forEach { print("${it.nome} | ") }

    println("\nLista de pratos disponíveis: ${livroReceitas.map { it.nome }}")

    println(
        "Qual a média de calorias de todas as receitas? ${
            livroReceitas.map { it.calorias }
                .average() // cria uma lista de calorias com o map, depois tira a média desses valores
        }"
    )

    println("Lista de receitas, excluindo as de mesmo nome: ${livroReceitas.map { it.nome }.distinct()}")

    println("Lista de receitas ordenadas por nome: ${livroReceitas.map { it.nome }.sorted()}")

    println(
        "Lista de receitas ordenadas por nome, em ordem inversa: ${
            livroReceitas.map { it.nome }.sorted().reversed()
        }"
    )

    print("Receitas que possuem farinha como ingredientes: ")
    val temFarinha = livroReceitas
        .filter { temIngrediente(it.ingredientes, "Farinha") }
        .forEach { print("${it.nome} | ") }

}
