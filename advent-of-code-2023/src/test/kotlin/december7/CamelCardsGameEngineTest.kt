package december7

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class CamelCardsGameEngineTest {

    private val camelCardsGameEngine = CamelCardsGameEngine(HandSetMapper())

    @Test
    fun `Calculates correct winnings for part 1`() {
        val lines = readLinesFromResourceFile("december7", "test_input.txt")
        val result = camelCardsGameEngine.calculateGameWinnings(lines)

        result shouldBeEqualTo 6440
    }

    @Test
    fun `Calculates correct winnings for part 2`() {
        val lines = readLinesFromResourceFile("december7", "test_input.txt")
        val result = camelCardsGameEngine.calculateGameWinningsPart2(lines)

        result shouldBeEqualTo 5905
    }
}