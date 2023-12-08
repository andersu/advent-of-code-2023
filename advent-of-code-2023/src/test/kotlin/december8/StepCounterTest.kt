package december8

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class StepCounterTest {
    private val stepCounter = StepCounter(DesertMapMapper())

    @Test
    fun `Counts the right number of steps for part 1 first input`() {
        val lines = readLinesFromResourceFile("december8", "test_input_1.txt")
        val numberOfSteps = stepCounter.countSteps(lines)

        numberOfSteps shouldBeEqualTo 2
    }

    @Test
    fun `Counts the right number of steps for part 1 second input`() {
        val lines = readLinesFromResourceFile("december8", "test_input_2.txt")
        val numberOfSteps = stepCounter.countSteps(lines)

        numberOfSteps shouldBeEqualTo 6
    }

    @Test
    fun `Counts the right number of steps for part 2`() {
        val lines = readLinesFromResourceFile("december8", "test_input_3.txt")
        val numberOfSteps = stepCounter.countStepsForMultiplePaths(lines)

        numberOfSteps shouldBeEqualTo 6
    }
}