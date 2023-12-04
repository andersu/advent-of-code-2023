package december4

import utils.readLinesFromResourceFile

fun main() {
    val scratchcardWinnerFinder = ScratchcardPrizeAnalyzer(ScratchcardMapper())
    val lines = readLinesFromResourceFile("december4", "input.txt")

    val resultPart1 = scratchcardWinnerFinder.calculatePoints(lines)
    println("Result part 1: $resultPart1")

    val resultPart2 = scratchcardWinnerFinder.calculateNumberOfScratchcards(lines)
    println("Result part 2: $resultPart2")
}