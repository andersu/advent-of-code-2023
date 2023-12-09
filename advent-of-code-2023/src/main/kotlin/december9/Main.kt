package december9

import utils.readLinesFromResourceFile

fun main() {
    val oasisAnalyzer = OasisAnalyzer(OasisInformationMapper())
    val lines = readLinesFromResourceFile("december9", "input.txt")

    val resultPart1 = oasisAnalyzer.calculateSumOfNextValues(lines)
    println("Result part 1: $resultPart1")

    val resultPart2 = oasisAnalyzer.calculateSumOfPreviousValues(lines)
    println("Result part 2: $resultPart2")
}