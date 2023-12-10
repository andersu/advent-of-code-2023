package december10

class PipeMapMapper {

    fun mapLinesToPipeMap(lines: List<String>): PipeMap {
        var startPosition = Pair(0, 0)
        val nodes = lines.mapIndexed { y, line ->
            line.mapIndexed { x, char ->
                val position = Pair(x, y)
                when (char) {
                    '.' -> Node.Ground(position)
                    '|' -> Node.Pipe(PipeType.SOUTH_NORTH, position)
                    '-' -> Node.Pipe(PipeType.WEST_EAST, position)
                    'F' -> Node.Pipe(PipeType.SOUTH_EAST, position)
                    '7' -> Node.Pipe(PipeType.SOUTH_WEST, position)
                    'L' -> Node.Pipe(PipeType.NORTH_EAST, position)
                    'J' -> Node.Pipe(PipeType.NORTH_WEST, position)
                    'S' -> {
                        startPosition = position
                        Node.Pipe(PipeType.UNKNOWN, position)
                    }

                    else -> throw IllegalArgumentException("Unexpected char in map: $char")
                }
            }
        }
        return PipeMap(nodes, startPosition)
    }
}