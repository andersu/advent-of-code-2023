package december7

import utils.readLinesFromResourceFile

fun main() {
    val camelCardsGameEngine = CamelCardsGameEngine(HandSetMapper())
    val lines = readLinesFromResourceFile("december7", "input.txt")

    val resultPart1 = camelCardsGameEngine.calculateGameWinnings(lines)
    println("Result part 1: $resultPart1")

    val resultPart2 = camelCardsGameEngine.calculateGameWinningsPart2(lines)
    println("Result part 2: $resultPart2")
}