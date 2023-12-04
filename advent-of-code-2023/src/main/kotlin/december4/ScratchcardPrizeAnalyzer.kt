package december4

import kotlin.math.pow

class ScratchcardPrizeAnalyzer(
    private val scratchcardMapper: ScratchcardMapper
) {
    fun calculatePoints(lines: List<String>): Int =
        scratchcardMapper
            .mapLineScratchcards(lines)
            .sumOf { it.calculatePoints() }

    fun calculateNumberOfScratchcards(lines: List<String>): Int {
        val scratchcards = scratchcardMapper.mapLineScratchcards(lines)
        var totalScratchcards = scratchcards.size
        var cardNumbers = scratchcards.map { it.cardNumber }
        val cardNumberToWinningScratchcardsMap = scratchcards.groupBy { it.cardNumber }.map {
            val scratchcard = it.value.first()
            it.key to scratchcard.winningNumbers.intersect(scratchcard.numbers).count()
        }.toMap()
        
        while (true) {
            cardNumbers = getWonCopies(cardNumbers, cardNumberToWinningScratchcardsMap)
            if (cardNumbers.isEmpty()) break
            totalScratchcards += cardNumbers.size
        }

        return totalScratchcards
    }

    private fun getWonCopies(cardNumbers: List<Int>, cardNumberToWinningScratchcardsMap: Map<Int, Int>): List<Int> {
        return cardNumbers.map {
            val amountWon = cardNumberToWinningScratchcardsMap[it] ?: 0
            ((it + 1)..(it + amountWon)).toList()
        }.flatten()
    }

    private fun Scratchcard.calculatePoints(): Int {
        val matchingNumbers = winningNumbers.intersect(numbers)
        if (matchingNumbers.isEmpty()) return 0

        return 2.0.pow((matchingNumbers.size - 1).toDouble()).toInt()
    }
}