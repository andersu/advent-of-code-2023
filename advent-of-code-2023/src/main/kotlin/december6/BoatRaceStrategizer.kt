package december6

class BoatRaceStrategizer(
    private val raceMapper: RaceMapper
) {

    fun calculatePowerOfWinningStrategies(lines: List<String>): Int =
        raceMapper
            .mapLinesToRaces(lines)
            .map { it.calculateNumberOfWinningStrategiesForSingleRace() }.reduce { acc, i -> acc * i }

    fun calculateNumberOfWinningStrategiesForSingleRace(lines: List<String>): Int =
        raceMapper.mapLinesToRace(lines)
            .calculateNumberOfWinningStrategiesForSingleRace()

    private fun Race.calculateNumberOfWinningStrategiesForSingleRace(): Int =
        (1..<time).count {
            (time - it) * it > recordDistance
        }
}