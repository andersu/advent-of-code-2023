package december3

import utils.readLinesFromResourceFile

fun main() {
    val gondolaEngineSchemaParser = GondolaEngineSchemaParser(SchematicMapper())
    val lines = readLinesFromResourceFile("december3", "input.txt")

    val resultPart1 = gondolaEngineSchemaParser.calculateSumOfPartNumbers(lines)
    println("Result part 1: $resultPart1")

    val resultPart2 = gondolaEngineSchemaParser.calculateSumOfGearRatios(lines)
    println("Result part 2: $resultPart2")
}