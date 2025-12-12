import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeListResponseDto(
    @SerialName("data") val data: List<AnimeListItemDto>
)

@Serializable
data class AnimeListItemDto(
    @SerialName("mal_id") val malId: Int,
    @SerialName("title") val title: String? = null,
    @SerialName("episodes") val episodes: Int? = null,
    @SerialName("score") val score: Double? = null,
    @SerialName("images") val images: MiniImages? = null
)

@Serializable
data class MiniImages(
    @SerialName("jpg") val jpg: MiniImageSet? = null,
    @SerialName("webp") val webp: MiniImageSet? = null
)

@Serializable
data class MiniImageSet(
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("small_image_url") val smallImageUrl: String? = null,
    @SerialName("large_image_url") val largeImageUrl: String? = null
)

data class Anime(
    val id: Int,
    val title: String,
    val episodes: Int? = null,
    val rating: Double? = null,
    val posterUrl: String? = null
)
