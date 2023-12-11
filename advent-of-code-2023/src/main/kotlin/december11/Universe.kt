package december11

data class Universe(
    val width: Long,
    val height: Long,
    val galaxies : List<Galaxy>,
)

data class Galaxy(
    val id: Long,
    val coordinates: Pair<Long, Long>,
)
