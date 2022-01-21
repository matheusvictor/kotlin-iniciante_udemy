package com.example.tasks.service.listener

class ValidationListener(str: String = "") {

    private var mSuccessedStatus: Boolean = true
    private var mMessage: String = ""

    init {
        /* Ao ser chamado, os valores de ValidationListener serão trocados logo na inicialização
        se houver alguma mensagem recebida no parâmetro diferente de vazio */
        if (str != "") {
            mSuccessedStatus = false
            mMessage = str
        }
    }

    fun isSuccessed() = mSuccessedStatus

    fun getErrorMessage() = mMessage

}
