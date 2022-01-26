package business

import entity.Pessoa

class PessoaValidacao {

    fun maiorDeIdade(convidado: Pessoa): Boolean = convidado.idade >= 18

}