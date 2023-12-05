package december5

data class Almanac(
    val seeds: List<Seed>,
    val categoryMaps: List<CategoryMap>
)

data class AlmanacWithSeedRanges(
    val seedRanges: List<LongRange>,
    val categoryMaps: List<CategoryMap>
)

data class Seed(
    val value: Long
)

data class CategoryMap(
    val mappings: List<Mapping>
)

data class Mapping(
    val sourceRange: LongRange,
    val delta: Long
)