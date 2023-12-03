package december3

class SchematicMapper {
    fun mapLinesToSchematic(lines: List<String>): Schematic {
        val numbers = mutableListOf<SchematicNumber>()
        val symbols = mutableListOf<SchematicSymbol>()
        var currentNumber = ""

        lines.forEachIndexed { y, line ->
            var x = 0
            while (x < line.length) {
                val char = line[x]
                if (char.isDigit()) {
                    currentNumber += char
                } else {
                    if (currentNumber.isNotEmpty()) {
                        numbers.add(SchematicNumber(currentNumber.toInt(), x - currentNumber.length, x - 1, y))
                        currentNumber = ""
                    }

                    if (char != '.') {
                        symbols.add(SchematicSymbol(char, x, y))
                    }
                }
                x++
            }
        }

        return Schematic(numbers, symbols)
    }
}