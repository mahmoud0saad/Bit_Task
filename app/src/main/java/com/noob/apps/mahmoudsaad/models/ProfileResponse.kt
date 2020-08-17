package com.noob.apps.mahmoudsaad.models

data class ProfileResponse(
    val `data`: Data,
    val status: Boolean
) {
    data class Data(
        val bio: String,
        val counts: Counts,
        val full_name: String,
        val id: Int,
        val location: String,
        val profile_picture: String
    ) {
        data class Counts(
            val followers: Int,
            val following: Int,
            val posts: Int
        )
    }
}