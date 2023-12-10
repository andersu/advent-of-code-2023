package december10

import utils.readLinesFromResourceFile

fun main() {
    val pipeCalculator = PipeCalculator(PipeMapMapper())
    val lines = readLinesFromResourceFile("december10", "input.txt")

    val resultPart1 = pipeCalculator.calculateDistanceToFarthestPoint(lines)
    println("Result part 1: $resultPart1")

    val start = System.currentTimeMillis()
    val resultPart2 = pipeCalculator.calculateAreaWithinLoop(lines)
    println("Result part 2: $resultPart2")
    val end = System.currentTimeMillis()
    println("Time part 2: ${end - start} ms")
}