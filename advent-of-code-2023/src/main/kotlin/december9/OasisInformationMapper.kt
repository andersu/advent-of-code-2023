package december9

class OasisInformationMapper {
    fun mapLinesToOasisInformation(lines: List<String>): OasisInformation {
        val valueHistories = lines.map { line ->
            val values = line.split(" ").map { it.toInt() }
            OasisValueHistory(values)
        }
        return OasisInformation(valueHistories)
    }
}