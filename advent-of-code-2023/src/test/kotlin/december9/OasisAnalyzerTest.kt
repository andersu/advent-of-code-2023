package december9

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class OasisAnalyzerTest {

    private val oasisAnalyzer = OasisAnalyzer(OasisInformationMapper())

    @Test
    fun calculateSumOfNextValues() {
        val lines = readLinesFromResourceFile("december9", "test_input.txt")
        val result = oasisAnalyzer.calculateSumOfNextValues(lines)

        result shouldBeEqualTo 114
    }

    @Test
    fun calculateSumOfPreviousValues() {
        val lines = readLinesFromResourceFile("december9", "test_input.txt")
        val result = oasisAnalyzer.calculateSumOfPreviousValues(lines)

        result shouldBeEqualTo 2
    }
}