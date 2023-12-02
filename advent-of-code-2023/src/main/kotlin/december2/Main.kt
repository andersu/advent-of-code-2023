package december2

import utils.readLinesFromResourceFile

fun main() {
    val cubeBagGameAnalyzer = CubeBagGameAnalyzer(GameMapper())
    val lines = readLinesFromResourceFile("december2", "input.txt")

    val resultPart1 = cubeBagGameAnalyzer.calculateSumOfPossibleGames(
        lines = lines,
        gameRules = GameRules(12, 13, 14)
    )
    println("Result part 1: $resultPart1")

    val resultPart2 = cubeBagGameAnalyzer.calculateSumOfPowerOfMinimumSetOfCubes(lines)
    println("Result part 2: $resultPart2")
}