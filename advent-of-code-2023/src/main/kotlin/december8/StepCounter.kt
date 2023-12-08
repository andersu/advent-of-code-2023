package december8

class StepCounter(
    private val desertMapMapper: DesertMapMapper
) {

    fun countSteps(lines: List<String>): Int {
        val desertMap = desertMapMapper.mapLinesToDesertMap(lines)
        var i = 0
        var currentNode = desertMap.nodes.first { it.value == "AAA" }
        val goalNode = desertMap.nodes.first { it.value == "ZZZ" }
        var steps = 0

        while (true) {
            val direction = desertMap.instructions[i]
            currentNode = currentNode.next(direction, desertMap.nodes)
            steps++

            when {
                currentNode == goalNode -> break
            }
            if (i < desertMap.instructions.size - 1) {
                i++
            } else {
                i = 0
            }
        }

        return steps
    }

    fun countStepsForMultiplePaths(lines: List<String>): Long {
        val desertMap = desertMapMapper.mapLinesToDesertMap(lines)
        val startNodes = desertMap.nodes.filter { it.value.endsWith("A") }
        val stepsToGoalNodes = startNodes.map { it.findNumberOfStepsToGoalNodes(desertMap.instructions, desertMap.nodes) }.flatten()
        println(stepsToGoalNodes)
        return stepsToGoalNodes.lcm()
    }

    private fun Node.next(direction: Direction, nodes: List<Node>): Node {
        return when (direction) {
            Direction.LEFT -> nodes[leftIndex]
            Direction.RIGHT -> nodes[rightIndex]
        }
    }

    private fun Node.findNumberOfStepsToGoalNodes(directions: List<Direction>, nodes: List<Node>): List<Long> {
        val goalNodes = nodes.filter { it.value.endsWith("Z") }
        val directionsSize = directions.size
        val stepsToGoalNodes = mutableListOf<Long>()

        goalNodes.forEach { goalNode ->
            val visitedNodesAtEndOfDirections = mutableSetOf<Node>()
            var steps = 0L
            var i = 0
            var currentNode = this

            while (true) {
                currentNode = currentNode.next(directions[i], nodes)
                steps++

                if (currentNode == goalNode) {
                    stepsToGoalNodes.add(steps)
                    break
                }

                if (i < directionsSize - 1) {
                    i++
                } else {
                    if (currentNode in visitedNodesAtEndOfDirections) {
                        println("Loop detected")
                        break
                    }
                    visitedNodesAtEndOfDirections.add(currentNode)
                    i = 0
                }
            }
        }

        return stepsToGoalNodes
    }

    private fun List<Long>.lcm(): Long {
        fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
        fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

        return this.reduce { a, b -> lcm(a, b) }
    }
}