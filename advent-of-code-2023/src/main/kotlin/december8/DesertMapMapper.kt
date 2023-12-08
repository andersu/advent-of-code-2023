package december8

class DesertMapMapper {

    fun mapLinesToDesertMap(lines: List<String>): DesertMap {
        val directions = lines[0].map { Direction.fromChar(it) }
        val nodeLines = lines.subList(2, lines.size)
        val nodeValues = nodeLines.map { it.split(" ")[0] }
        val nodes = nodeLines.mapIndexed { i, line ->
            val parts = line.substringAfter("(").replace(")", "").split(", ")
            Node(
                value = nodeValues[i],
                leftIndex = nodeValues.indexOf(parts[0]),
                rightIndex = nodeValues.indexOf(parts[1]),
            )
        }

        return DesertMap(
            instructions = directions,
            nodes = nodes
        )
    }
}