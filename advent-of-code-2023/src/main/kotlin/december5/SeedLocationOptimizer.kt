package december5

class SeedLocationOptimizer(
    private val almanacMapper: AlmanacMapper
) {

    private var start: Long = 0
    private var previousTimeLogged: Long = 0

    fun calculateOptimalLocation(lines: List<String>): Long {
        val almanac = almanacMapper.mapLinesToAlmanac(lines)
        return almanac.seeds.minOf {
            it.calculateLocation(almanac.categoryMaps)
        }
    }

    fun calculateOptimalLocationWithSeedRanges(lines: List<String>): Long {
        start = System.currentTimeMillis()
        previousTimeLogged = start
        println("Starting calculation")
        val almanacWithSeedRanges = almanacMapper.mapLinesToAlmanacWithSeedRanges(lines)

        var optimalLocation = Long.MAX_VALUE
        almanacWithSeedRanges.seedRanges.forEachIndexed { index, range ->
            println("-=-*-=- Range ${index + 1} -=-*-=-")
            val optimalLocationContender = range.calculateOptimalLocation(almanacWithSeedRanges.categoryMaps)
            val currentTime = System.currentTimeMillis()
            println("Range ${index + 1} optimal location contender: $optimalLocationContender")
            println("Time elapsed: ${currentTime - start} ms")
            if (optimalLocationContender < optimalLocation) {
                println("New optimal location: $optimalLocationContender")
                optimalLocation = optimalLocationContender
            }
        }
        val end = System.currentTimeMillis()
        println("Total time: ${end - start} ms")
        return optimalLocation
    }

    private fun LongRange.calculateOptimalLocation(categoryMaps: List<CategoryMap>): Long {
        var optimalLocation = Long.MAX_VALUE
        forEach {
            val seed = Seed(it)
            val optimalLocationContender = seed.calculateLocation(categoryMaps)
            if (optimalLocationContender < optimalLocation) {
                println("New optimal location within range: $optimalLocationContender")
                optimalLocation = optimalLocationContender
            }
        }
        return optimalLocation
    }


    private fun Seed.calculateLocation(categoryMaps: List<CategoryMap>): Long {
        var currentValue = value
        categoryMaps.forEach { categoryMap ->
            val relevantMapping = categoryMap.mappings.firstOrNull { it.sourceRange.contains(currentValue) }
            val delta = relevantMapping?.delta ?: 0
            currentValue += delta
        }
        //println(currentValue)
        return currentValue
    }
}
