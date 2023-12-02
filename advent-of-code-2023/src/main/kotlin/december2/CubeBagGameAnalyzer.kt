package december2

class CubeBagGameAnalyzer(
    private val gameMapper: GameMapper
) {
    fun calculateSumOfPossibleGames(lines: List<String>, gameRules: GameRules): Int =
        gameMapper.mapLinesToGames(lines)
            .filter { it.isPossible(gameRules) }
            .sumOf { it.id }

    fun calculateSumOfPowerOfMinimumSetOfCubes(lines: List<String>): Int =
        gameMapper.mapLinesToGames(lines)
            .sumOf { it.minimumSetOfCubes().reduce { acc, i -> acc * i } }

    private fun Game.isPossible(gameRules: GameRules) = rounds.all { round ->
        round.cubeAmount(CubeColor.RED) <= gameRules.maxRedCubes &&
                round.cubeAmount(CubeColor.GREEN) <= gameRules.maxGreenCubes &&
                round.cubeAmount(CubeColor.BLUE) <= gameRules.maxBlueCubes
    }

    private fun Game.minimumSetOfCubes(): List<Int> =
        listOf(
            maxCubeAmount(CubeColor.RED),
            maxCubeAmount(CubeColor.GREEN),
            maxCubeAmount(CubeColor.BLUE)
        )

    private fun Game.maxCubeAmount(cubeColor: CubeColor) =
        rounds.maxOfOrNull { it.cubeAmount(cubeColor) } ?: 0

    private fun Round.cubeAmount(cubeColor: CubeColor) =
        cubeAmounts.find { it.color == cubeColor }?.amount ?: 0
}



