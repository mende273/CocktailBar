package com.jumrukovski.cocktailbar.data.model

import java.io.Serializable

data class Filter(val filterBy: String, val param: String, val value: String) : Serializable {

    fun prepareTypeParam(): String = value.replace(" ", "_")
}
