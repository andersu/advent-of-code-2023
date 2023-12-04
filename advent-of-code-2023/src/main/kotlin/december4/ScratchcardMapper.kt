package december4

class ScratchcardMapper {

    fun mapLineScratchcards(lines: List<String>): List<Scratchcard> =
        lines.map { mapLineToScratchcard(it) }

    private fun mapLineToScratchcard(line: String): Scratchcard {
        val parts = line.split(": ")
        val cardNumber = parts[0].filter { it.isDigit() }.toInt()
        val numberParts = parts[1].split(" | ")

        val winningNumbers = numberParts[0].chunked(3).map { it.filter { it.isDigit() } }.map { it.toInt() }.toSet()

        val numbers = numberParts[1].chunked(3).map { it.filter { it.isDigit() } }.map { it.toInt() }.toSet()
        return Scratchcard(cardNumber, winningNumbers, numbers)
    }
}