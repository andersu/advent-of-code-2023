package december8

import utils.readLinesFromResourceFile

fun main() {
    val stepCounter = StepCounter(DesertMapMapper())
    val lines = readLinesFromResourceFile("december8", "input.txt")

    val resultPart1 = stepCounter.countSteps(lines)
    println("Result part 1: $resultPart1")

    val resultPart2 = stepCounter.countStepsForMultiplePaths(lines)
    println("Result part 2: $resultPart2")
}