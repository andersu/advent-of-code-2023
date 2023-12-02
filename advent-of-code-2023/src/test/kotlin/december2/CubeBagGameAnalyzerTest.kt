package december2

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class CubeBagGameAnalyzerTest {

    private val cubeBagGameAnalyzer = CubeBagGameAnalyzer(GameMapper())

    @Test
    fun `Should calculate correct sum for test input in part 1`() {
        val lines = readLinesFromResourceFile("december2", "test_input.txt")

        val result = cubeBagGameAnalyzer.calculateSumOfPossibleGames(
            lines = lines,
            gameRules = GameRules(10, 10, 10)
        )

        result shouldBeEqualTo 8
    }

    @Test
    fun `Should calculate correct sum for test input in part 2`() {
        val lines = readLinesFromResourceFile("december2", "test_input.txt")

        val result = cubeBagGameAnalyzer.calculateSumOfPowerOfMinimumSetOfCubes(lines)

        result shouldBeEqualTo 2286
    }
}