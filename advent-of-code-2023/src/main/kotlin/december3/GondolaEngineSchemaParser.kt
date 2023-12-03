package december3

class GondolaEngineSchemaParser(private val schematicMapper: SchematicMapper) {
    fun calculateSumOfPartNumbers(lines: List<String>): Int =
        schematicMapper
            .mapLinesToSchematic(lines)
            .partNumbers()
            .sum()

    fun calculateSumOfGearRatios(lines: List<String>): Int {
        val schematic = schematicMapper.mapLinesToSchematic(lines)
        return schematic.symbols
            .filter { it.value == '*' }
            .sumOf {
                val partNumbers = it.partNumbers(schematic.numbers)
                if (partNumbers.size == 2) {
                    partNumbers[0].value * partNumbers[1].value
                } else 0
            }
    }

    private fun Schematic.partNumbers(): List<Int> =
        symbols
            .map { symbol -> symbol.partNumbers(numbers) }
            .flatten()
            .map { it.value }

    private fun SchematicSymbol.partNumbers(allNumbers: List<SchematicNumber>) =
        allNumbers
            .filter { number ->
                number.y - y in -1..1 && (number.startX - x in -1..1 || number.endX - x in -1..1)
            }

}
