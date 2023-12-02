package december2

data class Game(
    val id: Int,
    val rounds: List<Round>
)

data class Round(
    val cubeAmounts: List<CubeAmount>
)

data class CubeAmount(
    val color: CubeColor,
    val amount: Int
)

enum class CubeColor(val color: String) {
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    companion object {
        fun fromString(color: String): CubeColor =
            entries.find { it.color == color } ?: throw IllegalArgumentException("Unknown color: $color")
    }
}
