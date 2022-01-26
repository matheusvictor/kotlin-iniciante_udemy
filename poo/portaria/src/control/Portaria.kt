package control

import business.ConviteValidacao
import constants.Constantes

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
        if (!validacaoConvite.codigoValido(codigoConvite, tipoConvite)) {
            return Constantes.ALERTAS.CONVITEINVALIDO
        }

        return "TODO"
    }

}