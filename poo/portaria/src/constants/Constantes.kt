package constants

class Constantes {

    object PERGUNTAS {
        val IDADE = "Qual é sua idade? "
        val TIPOCONVITE = "Qual é o tipo de convite? "
        val CODIGOCONVITE = "Qual é o código do convite? "
    }

    object ALERTAS {
        val NAOPERMITIDO = "Acesso negado! Menores de idade não são permitidos."
        val VALORINVALIDO = "Valor inválido!"
        val CONVITEINVALIDO = "Negado! entity.Convite inválido."
    }

    object TIPOS_CONVITE {
        val COMUM = "comum"
        val PREMIUM = "premium"
        val LUXO = "luxo"
    }

    object CODIGOS_CONVITE {
        val COD_COMUM = "xt"
        val COD_PREMIUM_LUXO = "xl"
    }

}
