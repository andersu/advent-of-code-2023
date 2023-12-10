package december10

class PipeCalculator(
    private val pipeMapMapper: PipeMapMapper
) {

    fun calculateDistanceToFarthestPoint(lines: List<String>): Int {
        val pipeMap = pipeMapMapper.mapLinesToPipeMap(lines)
        val loop = findLoop(pipeMap)
        return loop.nodes.size / 2
    }

    fun calculateAreaWithinLoop(lines: List<String>): Int {
        val pipeMap = pipeMapMapper.mapLinesToPipeMap(lines)
        val loop = findLoop(pipeMap)

        val otherNodes = pipeMap.nodes.flatten().subtract(loop.nodes.toSet())
        return otherNodes.count { loop.isPointInside(it.position) }
    }

    private fun findLoop(pipeMap: PipeMap): PipeLoop {
        val moves = findMovesToPotentiallyConnectedNeighborPositions(pipeMap)
        moves.forEach { move ->
            var currentMove = move
            val loopPipes = mutableSetOf<Node.Pipe>()

            while (true) {
                val currentPosition = currentMove.toPosition
                val currentPipe = pipeMap.nodes[currentPosition.second][currentPosition.first]
                if (currentPipe !is Node.Pipe) {
                    break
                }

                if (currentPipe.type == PipeType.UNKNOWN) {
                    loopPipes.add(currentPipe)
                    return PipeLoop(loopPipes.toList())
                }

                val directionOfNextMove = when (currentMove.direction) {
                    Direction.NORTH -> when (currentPipe.type) {
                        PipeType.SOUTH_NORTH -> Direction.NORTH
                        PipeType.SOUTH_EAST -> Direction.EAST
                        PipeType.SOUTH_WEST -> Direction.WEST
                        else -> {
                            break
                        }
                    }

                    Direction.SOUTH -> when (currentPipe.type) {
                        PipeType.SOUTH_NORTH -> Direction.SOUTH
                        PipeType.NORTH_EAST -> Direction.EAST
                        PipeType.NORTH_WEST -> Direction.WEST
                        else -> {
                            break
                        }
                    }

                    Direction.WEST -> when (currentPipe.type) {
                        PipeType.WEST_EAST -> Direction.WEST
                        PipeType.SOUTH_EAST -> Direction.SOUTH
                        PipeType.NORTH_EAST -> Direction.NORTH
                        else -> {
                            break
                        }
                    }

                    Direction.EAST -> when (currentPipe.type) {
                        PipeType.WEST_EAST -> Direction.EAST
                        PipeType.SOUTH_WEST -> Direction.SOUTH
                        PipeType.NORTH_WEST -> Direction.NORTH
                        else -> {
                            break
                        }
                    }
                }

                loopPipes.add(currentPipe)
                currentMove = Move(directionOfNextMove, getNextPosition(currentPosition, directionOfNextMove))
            }
        }

        throw IllegalStateException("No loop found")
    }

    private fun getNextPosition(
        currentPosition: Pair<Int, Int>,
        direction: Direction
    ): Pair<Int, Int> {
        return when (direction) {
            Direction.NORTH -> Pair(currentPosition.first, currentPosition.second - 1)
            Direction.SOUTH -> Pair(currentPosition.first, currentPosition.second + 1)
            Direction.WEST -> Pair(currentPosition.first - 1, currentPosition.second)
            Direction.EAST -> Pair(currentPosition.first + 1, currentPosition.second)
        }
    }

    private fun findMovesToPotentiallyConnectedNeighborPositions(pipeMap: PipeMap): List<Move> {
        val moves = mutableListOf<Move>()

        val northNeighbourPosition = Pair(pipeMap.startPosition.first, pipeMap.startPosition.second - 1)
        val northNeighbour = pipeMap.nodes[northNeighbourPosition.second][northNeighbourPosition.first]
        if (northNeighbour is Node.Pipe && northNeighbour.type in listOf(
                PipeType.SOUTH_NORTH,
                PipeType.SOUTH_WEST,
                PipeType.SOUTH_EAST
            )
        ) {
            moves.add(Move(Direction.NORTH, northNeighbourPosition))
        }

        val eastNeighbourPosition = Pair(pipeMap.startPosition.first + 1, pipeMap.startPosition.second)
        val eastNeighbor = pipeMap.nodes[eastNeighbourPosition.second][eastNeighbourPosition.first]
        if (eastNeighbor is Node.Pipe && eastNeighbor.type in listOf(
                PipeType.WEST_EAST,
                PipeType.NORTH_WEST,
                PipeType.SOUTH_WEST
            )
        ) {
            moves.add(Move(Direction.EAST, eastNeighbourPosition))
        }

        val southNeighbourPosition = Pair(pipeMap.startPosition.first, pipeMap.startPosition.second + 1)
        val southNeighbour = pipeMap.nodes[southNeighbourPosition.second][southNeighbourPosition.first]
        if (southNeighbour is Node.Pipe && southNeighbour.type in listOf(
                PipeType.NORTH_EAST,
                PipeType.NORTH_WEST,
                PipeType.SOUTH_NORTH
            )
        ) {
            moves.add(Move(Direction.SOUTH, southNeighbourPosition))
        }

        val westNeighbourPosition = Pair(pipeMap.startPosition.first - 1, pipeMap.startPosition.second)
        val westNeighbour = pipeMap.nodes[westNeighbourPosition.second][westNeighbourPosition.first]
        if (westNeighbour is Node.Pipe && westNeighbour.type in listOf(
                PipeType.WEST_EAST,
                PipeType.SOUTH_EAST,
                PipeType.NORTH_EAST
            )
        ) {
            moves.add(Move(Direction.WEST, westNeighbourPosition))
        }

        return moves
    }

    private fun PipeLoop.isPointInside(point: Pair<Int, Int>): Boolean {
        val eps = 0.00001

        var inside = false
        for (edge in edges) {
            val (first, second) = edge
            val (a, b) = listOf(first.position, second.position).map { it.toPairOfDouble() }.sortedBy { it.second }

            val (x,y) = point.toPairOfDouble().let { (x, y) ->
                when (y) {
                    a.second, b.second -> Pair(x, y + eps)
                    else -> Pair(x, y)
                }
            }

            when {
                y > b.second || y < a.second || x > maxOf(
                    a.first,
                    b.first
                ) -> continue

                x < minOf(a.first, b.first) -> inside = !inside
                else -> {
                    val mEdge = calculateSlope(a, b)
                    val mPoint = calculateSlope(a, Pair(x,y))
                    if (mPoint >= mEdge) inside = !inside
                }
            }
        }

        return inside
    }

    private fun Pair<Int, Int>.toPairOfDouble() = Pair(first.toDouble(), second.toDouble())

    private fun calculateSlope(a: Pair<Double, Double>, b: Pair<Double, Double>): Double =
        if ((b.second - a.second) / (b.first - a.first) == 0.0) Double.MAX_VALUE else (b.second - a.second) / (b.first - a.first)

}
