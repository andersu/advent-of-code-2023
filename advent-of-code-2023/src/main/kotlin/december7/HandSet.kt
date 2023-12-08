package december7

data class HandSet(
    val hands: List<Hand>
)

data class Hand(
    val cards: List<Card>,
    val bid: Int,
    val type: HandType
) : Comparable<Hand> {
    override fun compareTo(other: Hand): Int {
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

enum class HandType(val strength: Int) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1)
}

enum class Card(val value: Int) {
    ACE(14),
    KING(13),
    QUEEN(12),
    JACK(11),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2);

    companion object {
        fun fromChar(char: Char): Card =
            when (char) {
                'A' -> ACE
                'K' -> KING
                'Q' -> QUEEN
                'J' -> JACK
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