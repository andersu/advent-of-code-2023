package december10

data class PipeMap(
    val nodes: List<List<Node>>,
    val startPosition: Pair<Int, Int>
)

data class PipeLoop(
    val nodes: List<Node>
)  {
    val edges: List<Pair<Node, Node>> get() =
        nodes.mapIndexed { i, node ->
            Pair(node, nodes[(i+1) % nodes.size])
        }
}

sealed interface Node {

    val position: Pair<Int, Int>

    data class Ground(
        override val position: Pair<Int, Int>
    ) : Node

    data class Pipe(
        val type: PipeType,
        override val position: Pair<Int, Int>
    ) : Node
}

enum class PipeType {
    WEST_EAST,
    SOUTH_NORTH,
    SOUTH_EAST,
    SOUTH_WEST,
    NORTH_EAST,
    NORTH_WEST,
    UNKNOWN
}

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

data class Move(
    val direction: Direction,
    val toPosition: Pair<Int, Int>
)