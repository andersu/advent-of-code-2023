package december11

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class GalacticMeasurerTest {
    private val galacticMeasurer = GalacticMeasurer(UniverseMapper())

    @Test
    fun `Calculates the right sum of path distances for part 1`() {
        val lines = readLinesFromResourceFile("december11", "test_input.txt")
        val result = galacticMeasurer.calculateSumOfShortestPathsBetweenGalaxies(lines)

        result shouldBeEqualTo 374
    }

    @Test
    fun `Calculates the right sum of path distances for part 2`() {
        val lines = readLinesFromResourceFile("december11", "test_input.txt")
        galacticMeasurer.calculateSumOfShortestPathsBetweenGalaxies(lines, expansionFactor = 9) shouldBeEqualTo 1030
        galacticMeasurer.calculateSumOfShortestPathsBetweenGalaxies(lines, expansionFactor = 99) shouldBeEqualTo 8410
    }
}