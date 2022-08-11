package com.example.myapp.data.movies

data class Movie(
    val id : Int,
    val poster_path  : String?,
    val adult : Boolean,
    val overview : String,
    val release_date:String,
    val genre_ids: Array<Int>,
    val original_title : String,
    val original_language : String,
    val title : String,
    val backdrop_path : String?,
    val popularity : Double,
    val vote_count : Int,
    val video : Boolean,
    val vote_average : Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (poster_path != other.poster_path) return false
        if (adult != other.adult) return false
        if (overview != other.overview) return false
        if (release_date != other.release_date) return false
        if (!genre_ids.contentEquals(other.genre_ids)) return false
        if (original_title != other.original_title) return false
        if (original_language != other.original_language) return false
        if (title != other.title) return false
        if (backdrop_path != other.backdrop_path) return false
        if (popularity != other.popularity) return false
        if (vote_count != other.vote_count) return false
        if (video != other.video) return false
        if (vote_average != other.vote_average) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (poster_path?.hashCode() ?: 0)
        result = 31 * result + adult.hashCode()
        result = 31 * result + overview.hashCode()
        result = 31 * result + release_date.hashCode()
        result = 31 * result + genre_ids.contentHashCode()
        result = 31 * result + original_title.hashCode()
        result = 31 * result + original_language.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (backdrop_path?.hashCode() ?: 0)
        result = 31 * result + popularity.hashCode()
        result = 31 * result + vote_count
        result = 31 * result + video.hashCode()
        result = 31 * result + vote_average.hashCode()
        return result
    }
}
