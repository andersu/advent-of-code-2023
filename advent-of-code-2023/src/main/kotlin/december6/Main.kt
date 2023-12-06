package december6

import utils.readLinesFromResourceFile

fun main() {
    val boatRaceStrategizer = BoatRaceStrategizer(RaceMapper())
    val lines = readLinesFromResourceFile("december6", "input.txt")

    val resultPart1 = boatRaceStrategizer.calculatePowerOfWinningStrategies(lines)
    println("Result part 1: $resultPart1")

    val resultPart2 = boatRaceStrategizer.calculateNumberOfWinningStrategiesForSingleRace(lines)
    println("Result part 2: $resultPart2")
}