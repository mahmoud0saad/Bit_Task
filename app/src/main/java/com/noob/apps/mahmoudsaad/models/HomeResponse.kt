package com.noob.apps.mahmoudsaad.models

data class HomeResponse(
    val `data`: List<Data>,
    val status: Boolean
) {
    data class Data(
        val id: Int,
        val image: String,
        val title: String
    )
}