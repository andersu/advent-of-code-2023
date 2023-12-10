package december10

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class PipeCalculatorTest {

    private val pipeCalculator = PipeCalculator(PipeMapMapper())

    @Test
    fun `Calculates the correct distance for the first input in part 1`() {
        val lines = readLinesFromResourceFile("december10", "test_input_1.txt")
        val result = pipeCalculator.calculateDistanceToFarthestPoint(lines)

        result shouldBeEqualTo 4
    }

    @Test
    fun `Calculates the correct area for the first input in part 2`() {
        val lines = readLinesFromResourceFile("december10", "test_input_2.txt")
        val result = pipeCalculator.calculateAreaWithinLoop(lines)

        result shouldBeEqualTo 4
    }

    @Test
    fun `Calculates the correct area when squeezing is allowed for the second input in part 2`() {
        val lines = readLinesFromResourceFile("december10", "test_input_3.txt")
        val result = pipeCalculator.calculateAreaWithinLoop(lines)

        result shouldBeEqualTo 4
    }

    @Test
    fun `Calculates the correct area when squeezing is allowed for the third input in part 2`() {
        val lines = readLinesFromResourceFile("december10", "test_input_4.txt")
        val result = pipeCalculator.calculateAreaWithinLoop(lines)

        result shouldBeEqualTo 8
    }

    @Test
    fun `Calculates the correct area when squeezing is allowed for the fourth input in part 2`() {
        val lines = readLinesFromResourceFile("december10", "test_input_5.txt")
        val result = pipeCalculator.calculateAreaWithinLoop(lines)

        result shouldBeEqualTo 10
    }
}