package december3

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class GondolaEngineSchemaParserTest {

    private val gondolaEngineSchemaParser = GondolaEngineSchemaParser(SchematicMapper())
    @Test
    fun `Right sum is calculated for part 1`() {
        val lines = readLinesFromResourceFile("december3", "test_input.txt")
        gondolaEngineSchemaParser.calculateSumOfPartNumbers(lines) shouldBeEqualTo 4361
    }

    @Test
    fun `Right sum is calculated for part 2`() {
        val lines = readLinesFromResourceFile("december3", "test_input.txt")
        gondolaEngineSchemaParser.calculateSumOfGearRatios(lines) shouldBeEqualTo 467835
    }
}