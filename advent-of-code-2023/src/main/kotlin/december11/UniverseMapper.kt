package december11

class UniverseMapper {

    fun mapLinesToUniverse(lines: List<String>): Universe {
        var c = 1L
        val galaxies = lines.mapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                val position = Pair(x.toLong(), y.toLong())
                when (char) {
                    '.' -> null
                    '#' -> Galaxy(c++, position)
                    else -> throw IllegalArgumentException("Unexpected char in map: $char")
                }
            }
        }.flatten()
        return Universe(width = lines[0].length.toLong(), height = lines.size.toLong(), galaxies = galaxies)
    }
}