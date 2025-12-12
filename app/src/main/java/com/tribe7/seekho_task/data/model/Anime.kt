import com.google.gson.annotations.SerializedName


data class AnimeListResponseDto(
    @SerializedName("data") val data: List<AnimeListItemDto>
)

data class AnimeListItemDto(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("episodes") val episodes: Int? = null,
    @SerializedName("score") val score: Double? = null,
    @SerializedName("images") val images: MiniImages? = null
)

data class MiniImages(
    @SerializedName("jpg") val jpg: MiniImageSet? = null,
    @SerializedName("webp") val webp: MiniImageSet? = null
)

data class MiniImageSet(
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("small_image_url") val smallImageUrl: String? = null,
    @SerializedName("large_image_url") val largeImageUrl: String? = null
)

data class Anime(
    val id: Int,
    val title: String,
    val episodes: Int? = null,
    val rating: Double? = null,
    val posterUrl: String? = null
)
