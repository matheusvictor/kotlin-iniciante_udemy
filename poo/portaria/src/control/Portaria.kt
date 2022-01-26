package control

import business.ConviteValidacao
import constants.Constantes
import entity.Convite

class Portaria {

    private val validacaoConvite = ConviteValidacao()

    init {
        println("Portaria inicializada!")
        println(controle())
    }

    private fun controle(): String {
        val idade = Console.readInt(Constantes.PERGUNTAS.IDADE)
        if (idade < 18) {
            return Constantes.ALERTAS.NAOPERMITIDO
        }

        val tipoConvite = Console.readString(Constantes.PERGUNTAS.TIPOCONVITE)
        if (!validacaoConvite.tipoValido(tipoConvite)) {
            return Constantes.ALERTAS.CONVITEINVALIDO
        }

        val codigoConvite = Console.readString(Constantes.PERGUNTAS.CODIGOCONVITE)
        // cria instância de Convite com tipo e código recebidos do usuário
        val convite = Convite(tipoConvite, codigoConvite)

        return if (!validacaoConvite.codigoValido(convite)) {
            Constantes.ALERTAS.CONVITEINVALIDO
        } else {
            Constantes.MSG_SUCESSO.BOAS_VINDAS
        }

    }

}