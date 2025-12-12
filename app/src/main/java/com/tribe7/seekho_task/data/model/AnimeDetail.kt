import com.google.gson.annotations.SerializedName
import com.tribe7.seekho_task.data.local.entity.AnimeDetailEntity

data class AnimeDetailResponseDto(
    @SerializedName("data") val data: AnimeDetailDto
)

data class AnimeDetailDto(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("score") val score: Double?,
    @SerializedName("images") val images: AnimeImagesDto?,
    @SerializedName("trailer") val trailer: AnimeTrailerDto?,
    @SerializedName("genres") val genres: List<GenreDto>?,
    @SerializedName("studios") val studios: List<StudioDto>?,
    @SerializedName("producers") val producers: List<ProducerDto>?,
)

data class AnimeImagesDto(
    @SerializedName("jpg") val jpg: AnimeImageJpgDto?
)

data class AnimeImageJpgDto(
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("large_image_url") val largeImageUrl: String?
)

data class AnimeTrailerDto(
    @SerializedName("youtube_id") val youtubeId: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("embed_url") val embedUrl: String?
)

data class GenreDto(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("name") val name: String
)

data class StudioDto(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("name") val name: String
)

data class ProducerDto(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("name") val name: String
)

data class AnimeDetail(
    val id: Int,
    val title: String?,
    val synopsis: String?,
    val episodes: Int?,
    val rating: Double?,
    val posterUrl: String?,
    val trailerUrl: String?,
    val genres: List<String>,
    val studios: List<String>
)


fun AnimeDetailDto.toEntity() = AnimeDetailEntity(
    id = malId,
    title = title,
    synopsis = synopsis,
    episodes = episodes,
    rating = score,
    posterUrl = images?.jpg?.largeImageUrl ?: images?.jpg?.imageUrl,
    trailerUrl = trailer?.embedUrl ?: trailer?.url,
    genres = genres?.joinToString(",") { it.name },
    studios = studios?.joinToString(",") { it.name }
)