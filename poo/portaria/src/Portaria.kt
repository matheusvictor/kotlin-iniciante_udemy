class Portaria {

    fun controle() {
        val idade = Console.readInt(Constantes.PERGUNTAS.IDADE)
        while (idade < 18) {
            println(Constantes.ALERTAS.NAOPERMITIDO)
            return
        }

        val tipoConvite = Console.readString(Constantes.PERGUNTAS.TIPOCONVITE)
        if (
            tipoConvite != Constantes.TIPOS_CONVITE.COMUM &&
            tipoConvite != Constantes.TIPOS_CONVITE.PREMIUM &&
            tipoConvite != Constantes.TIPOS_CONVITE.LUXO
        ) {
            println(Constantes.ALERTAS.CONVITEINVALIDO)
            return
        }
    }

}