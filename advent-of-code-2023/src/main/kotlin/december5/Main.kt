package december5

import utils.readLinesFromResourceFile

fun main() {
    val seedLocationOptimizer = SeedLocationOptimizer(AlmanacMapper())
    val lines = readLinesFromResourceFile("december5", "input.txt")

    val resultPart1 = seedLocationOptimizer.calculateOptimalLocation(lines)
    println("Result part 1: $resultPart1")

    val resultPart2 = seedLocationOptimizer.calculateOptimalLocationWithSeedRanges(lines)
    println("Result part 2: $resultPart2")
}