package com.route.chat_app.ui.addRoom

import com.route.chat_app.R

data class RoomCategory(
    val id: String,
    val name: Int,
    val imageId: Int
) {
    companion object {
        fun getCategoryById(id: String?): RoomCategory {
            return when (id) {
                "sports" -> {
                    RoomCategory("sports", R.string.sports, R.drawable.sports)
                }

                "movies" -> {
                    RoomCategory("movies", R.string.movies, R.drawable.movies)
                }

                "music" -> {
                    RoomCategory("music", R.string.Music, R.drawable.music)
                }

                else -> {
                    RoomCategory("music", R.string.Music, R.drawable.music)
                }
            }


        }

        fun getCategories() =
            listOf(
                RoomCategory("sports", R.string.sports, R.drawable.sports),
                RoomCategory("music", R.string.Music, R.drawable.music),
                RoomCategory("movies", R.string.movies, R.drawable.movies)
            )
    }
}
