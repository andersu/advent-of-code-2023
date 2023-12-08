package december7

data class HandSetPart2(
    val hands: List<HandPart2>
)

data class HandPart2(
    val cards: List<CardPart2>,
    val bid: Int,
    val type: HandType
) : Comparable<HandPart2> {
    override fun compareTo(other: HandPart2): Int {
        if (this.type.strength != other.type.strength) {
            return this.type.strength - other.type.strength
        }
        for (i in 0..<this.cards.size) {
            if (this.cards[i].value != other.cards[i].value) {
                return this.cards[i].value - other.cards[i].value
            }
        }
        return 0
    }
}

enum class CardPart2(val value: Int) {
    ACE(14),
    KING(13),
    QUEEN(12),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    JOKER(1);

    companion object {
        fun fromChar(char: Char): CardPart2 =
            when (char) {
                'A' -> ACE
                'K' -> KING
                'Q' -> QUEEN
                'J' -> JOKER
                'T' -> TEN
                '9' -> NINE
                '8' -> EIGHT
                '7' -> SEVEN
                '6' -> SIX
                '5' -> FIVE
                '4' -> FOUR
                '3' -> THREE
                '2' -> TWO
                else -> throw IllegalArgumentException("Unknown card: $char")
            }

    }
}