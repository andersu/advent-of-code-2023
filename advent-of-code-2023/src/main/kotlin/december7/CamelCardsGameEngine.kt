package december7

class CamelCardsGameEngine(
    private val handSetMapper: HandSetMapper
) {

    fun calculateGameWinnings(lines: List<String>): Int {
        val handSet = handSetMapper.mapLinesToHandSet(lines)
        val sortedHands = handSet.hands.sorted()
        return sortedHands.mapIndexed { index, hand -> (index + 1) * hand.bid  }.sum()
    }

    fun calculateGameWinningsPart2(lines: List<String>): Int {
        val handSet = handSetMapper.mapLinesToHandSetPart2(lines)
        val sortedHands = handSet.hands.sorted()
        return sortedHands.mapIndexed { index, hand -> (index + 1) * hand.bid  }.sum()
    }
}