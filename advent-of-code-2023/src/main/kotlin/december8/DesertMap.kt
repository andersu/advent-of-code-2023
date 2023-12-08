package december8

data class DesertMap(
    val instructions: List<Direction>,
    val nodes: List<Node>
)

enum class Direction {
    LEFT,
    RIGHT;

    companion object {
        fun fromChar(char: Char): Direction =
            when (char) {
                'L' -> LEFT
                'R' -> RIGHT
                else -> throw IllegalArgumentException("Unknown direction: $char")
            }
    }
}

data class Node(
    val value: String,
    val leftIndex: Int,
    val rightIndex: Int
)