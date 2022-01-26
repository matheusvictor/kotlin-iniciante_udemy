class Console private constructor() {

    companion object {
        fun readInt(msg: String): Int {
            var retorno: Int? = null
            do {
                print(msg)
                val valor = readLine()

                if (valor != null && valor != "") {
                    retorno = valor.toIntOrNull()

                    if (retorno == null || retorno <= 0) {
                        println(Constantes.ALERTAS.VALORINVALIDO)
                    } else {
                        println(Constantes.ALERTAS.VALORINVALIDO)
                    }
                }

            } while (retorno == null || retorno <= 0)

            return retorno
        }

        fun readString(msg: String): String {
            var retorno: String? = null

            do {
                print(msg)
                val valor = readLine()

                if (valor != null && valor != "") {
                    retorno = valor.lowercase()
                } else {
                    println(Constantes.ALERTAS.VALORINVALIDO)
                }

            } while (retorno == null)

            return retorno
        }
    }

}
