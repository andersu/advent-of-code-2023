package december11

import utils.readLinesFromResourceFile

fun main() {
    val galacticMeasurer = GalacticMeasurer(UniverseMapper())
    val lines = readLinesFromResourceFile("december11", "input.txt")

    val resultPart1 = galacticMeasurer.calculateSumOfShortestPathsBetweenGalaxies(lines)
    println("Result part 1: $resultPart1")

    val resultPart2 = galacticMeasurer.calculateSumOfShortestPathsBetweenGalaxies(lines, expansionFactor = 999_999)
    println("Result part 2: $resultPart2")
}