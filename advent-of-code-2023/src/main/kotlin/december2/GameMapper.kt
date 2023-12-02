package december2

class GameMapper {
    fun mapLinesToGames(lines: List<String>): List<Game> =
        lines.map { line ->
            val parts = line.split(":")
            val id = parts[0].filter { it.isDigit() }.toInt()
            val roundLines = parts[1].split(";").map { it.trim() }
            val rounds = roundLines.map { roundLine ->
                val cubeAmounts = roundLine
                    .split(",")
                    .map { it.trim() }
                    .map { cubeSpec ->
                        cubeSpec.split(" ").map { it.trim() }
                    }
                    .map {
                        val amount = it[0].toInt()
                        val color = it[1]
                        CubeAmount(CubeColor.fromString(color), amount)
                    }
                Round(cubeAmounts)
            }

            Game(id, rounds)
        }
}