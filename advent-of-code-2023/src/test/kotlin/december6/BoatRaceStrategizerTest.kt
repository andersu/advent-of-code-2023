package december6

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class BoatRaceStrategizerTest {

    private val boatRaceStrategizer = BoatRaceStrategizer(RaceMapper())

    @Test
    fun `Correct power of winning strategies is found for part 1`() {
        val lines = readLinesFromResourceFile("december6", "test_input.txt")
        val numberOfWinningStrategies = boatRaceStrategizer.calculatePowerOfWinningStrategies(lines)

        numberOfWinningStrategies shouldBeEqualTo 288
    }

    @Test
    fun `Correct number of winning strategies is found for part 2`() {
        val lines = readLinesFromResourceFile("december6", "test_input.txt")
        val numberOfWinningStrategies = boatRaceStrategizer.calculateNumberOfWinningStrategiesForSingleRace(lines)

        numberOfWinningStrategies shouldBeEqualTo 71503
    }
}