package december9

class OasisAnalyzer(
    private val oasisInformationMapper: OasisInformationMapper
) {
    fun calculateSumOfNextValues(lines: List<String>): Int {
        val oasisInformation = oasisInformationMapper.mapLinesToOasisInformation(lines)
        return oasisInformation.valueHistories
            .map { it.generateValueLists() }
            .sumOf { it.findNextValueForFirstList() }
    }

    fun calculateSumOfPreviousValues(lines: List<String>): Int {
        val oasisInformation = oasisInformationMapper.mapLinesToOasisInformation(lines)
        return oasisInformation.valueHistories
            .map { it.generateValueLists() }
            .sumOf { it.findPreviousValueForFirstList() }
    }

    private fun OasisValueHistory.generateValueLists(): List<List<Int>> {
        val valueLists = mutableListOf(this.values)
        while (true) {
            val currentValues = valueLists.last()
            val newValues = currentValues.mapIndexedNotNull { i, value ->
                if (i < currentValues.size - 1) {
                    currentValues[i + 1] - value
                } else {
                    null
                }
            }

            if (newValues.all { it == 0 }) {
                break
            }

            valueLists.add(newValues)
        }
        return valueLists
    }

    private fun List<List<Int>>.findNextValueForFirstList(): Int {
        val valueLists = this.reversed()
        var nextValue = 0
        valueLists.forEach {
            nextValue += it.last()
        }

        return nextValue
    }

    private fun List<List<Int>>.findPreviousValueForFirstList(): Int {
        val valueLists = this.reversed()
        var previousValue = 0
        valueLists.forEach {
            previousValue = it.first() - previousValue
        }

        return previousValue
    }
}