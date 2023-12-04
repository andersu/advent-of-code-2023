package december4

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class ScratchcardPrizeAnalyzerTest {

    private val scratchcardPrizeAnalyzer = ScratchcardPrizeAnalyzer(ScratchcardMapper())

    @Test
    fun `Calculates the right amount of points for the first part`() {
        val lines = readLinesFromResourceFile("december4", "test_input.txt")
        val points = scratchcardPrizeAnalyzer.calculatePoints(lines)

        points shouldBeEqualTo 13
    }

    @Test
    fun `Calculates the right number of scratchcards for the second part`() {
        val lines = readLinesFromResourceFile("december4", "test_input.txt")
        val points = scratchcardPrizeAnalyzer.calculateNumberOfScratchcards(lines)

        points shouldBeEqualTo 30
    }
}