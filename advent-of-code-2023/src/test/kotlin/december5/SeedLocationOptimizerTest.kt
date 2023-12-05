package december5

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class SeedLocationOptimizerTest {

    private val seedLocationOptimizer = SeedLocationOptimizer(AlmanacMapper())

    @Test
    fun `Correct location is found for part 1`() {
        val lines = readLinesFromResourceFile("december5", "test_input.txt")

        val optimalLocationValue = seedLocationOptimizer.calculateOptimalLocation(lines)

        optimalLocationValue shouldBeEqualTo 35
    }

    @Test
    fun `Correct location is found for part 2`() {
        val lines = readLinesFromResourceFile("december5", "test_input.txt")

        val optimalLocationValue = seedLocationOptimizer.calculateOptimalLocationWithSeedRanges(lines)

        optimalLocationValue shouldBeEqualTo 46
    }
}