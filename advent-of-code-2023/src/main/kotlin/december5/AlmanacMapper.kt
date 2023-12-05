package december5

class AlmanacMapper {

    fun mapLinesToAlmanac(lines: List<String>): Almanac {
        val seeds = lines[0].substringAfter("seeds: ").split(" ").map { Seed(it.toLong()) }

        val categoryMaps = mapCategoryMaps(lines)

        return Almanac(
            seeds = seeds,
            categoryMaps = categoryMaps
        )
    }

    fun mapLinesToAlmanacWithSeedRanges(lines: List<String>): AlmanacWithSeedRanges {
        val startTime = System.currentTimeMillis()
        val seedRanges = lines[0]
            .substringAfter("seeds: ")
            .split(" ")
            .chunked(2)
            .map {
                val start = it[0].toLong()
                val rangeLength = it[1].toLong()
                LongRange(start, start + rangeLength)
            }
        val categoryMaps = mapCategoryMaps(lines)

        val end = System.currentTimeMillis()
        println("Mapping time: ${end - startTime} ms")
        return AlmanacWithSeedRanges(
            seedRanges = seedRanges,
            categoryMaps = categoryMaps
        )
    }

    private fun mapCategoryMaps(lines: List<String>): MutableList<CategoryMap> {
        val currentMappingLines = mutableListOf<String>()
        val categoryMaps = mutableListOf<CategoryMap>()

        var i = 1
        while (i < lines.size) {
            val line = lines[i]
            when {
                line.isBlank() -> {
                    val categoryMap = createCategoryMap(currentMappingLines)
                    categoryMaps.add(categoryMap)
                    currentMappingLines.clear()
                }

                line.any { it.isDigit() } -> {
                    currentMappingLines.add(line)
                }

                else -> Unit
            }

            i++
        }

        // Add the last map
        val categoryMap = createCategoryMap(currentMappingLines)
        categoryMaps.add(categoryMap)
        return categoryMaps
    }

    private fun createCategoryMap(
        currentMappingLines: MutableList<String>
    ): CategoryMap {
        val mappings = currentMappingLines.map {
            val parts = it.split(" ")
            val destinationRangeStart = parts[0].toLong()
            val sourceRangeStart = parts[1].toLong()
            val rangeLength = parts[2].toLong()
            Mapping(
                sourceRange = sourceRangeStart..<(sourceRangeStart + rangeLength),
                delta = destinationRangeStart - sourceRangeStart
            )
        }
        return CategoryMap(
            mappings = mappings
        )
    }
}

