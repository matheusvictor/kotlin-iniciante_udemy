package com.example.tasks.service.model

import com.google.gson.annotations.SerializedName

class HeaderModel {

    /*
    SerializedName define qual o nome da prioridade vinda do Json deverá ser referenciada
    no HeaderModel
     */

    @SerializedName("token")
    var token: String = ""

    @SerializedName("personKey")
    var personKey: String = ""

    @SerializedName("name")
    var name: String = ""

}
