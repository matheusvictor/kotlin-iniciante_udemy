class Portaria {

    val console = Console()

    fun controle() {
        val idade = console.readInt(Constantes.PERGUNTAS.IDADE)
        while (idade < 18) {
            println(Constantes.ALERTAS.NAOPERMITIDO)
            return
        }

        val tipoConvite = console.readString(Constantes.PERGUNTAS.TIPOCONVITE)
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