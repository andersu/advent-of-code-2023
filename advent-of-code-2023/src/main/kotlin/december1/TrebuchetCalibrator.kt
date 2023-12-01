package december1

class TrebuchetCalibrator {
    private val wordToDigitMap = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun calibrate(lines: List<String>): Int =
        lines.sumOf {
            val digits = it.filter { char -> char.isDigit() }
            "${digits.first()}${digits.last()}".toInt()
        }

    fun calibrateSupportingDigitsAsText(lines: List<String>): Int =
        lines.map { line ->
            val firstDigitOrDigitWord = line.findAnyOf(wordToDigitMap.keys + wordToDigitMap.values)!!.second
            val lastDigitOrDigitWord = line.findLastAnyOf(wordToDigitMap.keys + wordToDigitMap.values)!!.second
            firstDigitOrDigitWord.toDigit() + lastDigitOrDigitWord.toDigit()
        }.sumOf { it.toInt() }

    private fun String.toDigit() = if (this.first().isDigit()) this else wordToDigitMap[this]!!
}

