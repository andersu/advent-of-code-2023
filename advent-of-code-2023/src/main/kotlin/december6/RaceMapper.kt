package december6

class RaceMapper {

    fun mapLinesToRaces(lines: List<String>): List<Race> {
        val times = mapLineToNumbers(lines[0])
        val distances = mapLineToNumbers(lines[1])

        return times.mapIndexed { i, time ->
            Race(time, distances[i])
        }
    }

    fun mapLinesToRace(lines: List<String>): Race =
        Race(
            time = mapLineToNumber(lines[0]),
            recordDistance = mapLineToNumber(lines[1])
        )

    private fun mapLineToNumbers(line: String): List<Long> =
        line
            .substringAfter(":")
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.toLong() }

    private fun mapLineToNumber(line: String): Long =
        line
            .substringAfter(":")
            .replace(" ", "")
            .toLong()

}