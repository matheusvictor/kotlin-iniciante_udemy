package business

import constants.Constantes
import entity.Convite

class ConviteValidacao {

    fun tipoValido(tipoConvite: String) =
        (tipoConvite == Constantes.TIPOS_CONVITE.COMUM ||
                tipoConvite == Constantes.TIPOS_CONVITE.LUXO ||
                tipoConvite == Constantes.TIPOS_CONVITE.PREMIUM)


    fun codigoValido(convite: Convite): Boolean = when (convite.tipo) {
        Constantes.TIPOS_CONVITE.COMUM -> convite.codigo.startsWith(Constantes.CODIGOS_CONVITE.COD_COMUM)
        Constantes.TIPOS_CONVITE.PREMIUM, Constantes.TIPOS_CONVITE.LUXO -> convite.codigo.startsWith(Constantes.CODIGOS_CONVITE.COD_PREMIUM_LUXO)
        else -> false
    }

}
