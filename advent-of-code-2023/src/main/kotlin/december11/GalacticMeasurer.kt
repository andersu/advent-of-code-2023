package december11

import kotlin.math.abs

class GalacticMeasurer(private val universeMapper: UniverseMapper) {

    fun calculateSumOfShortestPathsBetweenGalaxies(lines: List<String>, expansionFactor: Int = 1): Long {
        val universe = universeMapper.mapLinesToUniverse(lines)
        val expandedUniverse = universe.expand(expansionFactor)

        // Get all pairs of galaxies, no duplicates
        val pairs = expandedUniverse.galaxies.flatMapIndexed { index, galaxy1 ->
            expandedUniverse.galaxies.subList(index + 1, expandedUniverse.galaxies.size).map { galaxy2 ->
                Pair(galaxy1, galaxy2)
            }
        }

        return pairs.sumOf { (galaxy1, galaxy2) ->
            val distance = calculateDistanceBetweenGalaxies(galaxy1, galaxy2)
            distance
        }
    }

    private fun calculateDistanceBetweenGalaxies(galaxy1: Galaxy, galaxy2: Galaxy): Long {
        val xDistance = abs(galaxy1.coordinates.first - galaxy2.coordinates.first)
        val yDistance = abs(galaxy1.coordinates.second - galaxy2.coordinates.second)
        return xDistance + yDistance
    }

    private fun Universe.expand(expansionFactor: Int): Universe {
        val emptyRows = (0..<this.width).mapNotNull {i ->
            if (galaxies.none { it.coordinates.first == i }) {
                i
            } else null
        }

        val emptyColumns = (0..<this.height).mapNotNull {i ->
            if (galaxies.none { it.coordinates.second == i }) {
                i
            } else null
        }

        return Universe(
            width = this.width + emptyRows.size,
            height = this.height + emptyColumns.size,
            galaxies = this.galaxies.map { galaxy ->
                Galaxy(
                    id = galaxy.id,
                    coordinates = Pair(
                        galaxy.coordinates.first + expansionFactor * emptyRows.count { it < galaxy.coordinates.first },
                        galaxy.coordinates.second + expansionFactor * emptyColumns.count { it < galaxy.coordinates.second }
                    )
                )
            }
        )
    }

}