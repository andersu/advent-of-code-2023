package december4

data class Scratchcard(
    val cardNumber: Int,
    val winningNumbers: Set<Int>,
    val numbers: Set<Int>
)
