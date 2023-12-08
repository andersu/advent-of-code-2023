package december7

class HandSetMapper {

    fun mapLinesToHandSet(lines: List<String>): HandSet {
        val hands = lines.map { line ->
            val parts = line.trim().split(" ")
            val cards = parts[0].map { Card.fromChar(it) }
            Hand(
                cards = cards,
                bid = parts[1].toInt(),
                type = findType(cards)
            )
        }
        val handSet = HandSet(hands)
        println(handSet)
        return handSet
    }

    fun mapLinesToHandSetPart2(lines: List<String>): HandSetPart2 {
        val hands = lines.map { line ->
            val parts = line.trim().split(" ")
            val cards = parts[0].map { CardPart2.fromChar(it) }
            HandPart2(
                cards = cards,
                bid = parts[1].toInt(),
                type = findTypePart2(cards)
            )
        }

        return HandSetPart2(hands)
    }

    private fun findType(cards: List<Card>): HandType {
        val cardsByValue = cards.groupBy { it.value }
        val mostCardsOfOneValue = cardsByValue.values.maxOf { it.size }

        return when {
            mostCardsOfOneValue == 5 -> HandType.FIVE_OF_A_KIND
            mostCardsOfOneValue == 4 -> HandType.FOUR_OF_A_KIND
            mostCardsOfOneValue == 3 && cardsByValue.values.any { it.size == 2 } -> HandType.FULL_HOUSE
            mostCardsOfOneValue == 3 -> HandType.THREE_OF_A_KIND
            cardsByValue.values.count { it.size == 2 } == 2 -> HandType.TWO_PAIR
            mostCardsOfOneValue == 2 -> HandType.ONE_PAIR
            else -> HandType.HIGH_CARD
        }
    }

    private fun findTypePart2(cards: List<CardPart2>): HandType {
        val otherCardsByValue = cards.filter { it != CardPart2.JOKER }.groupBy { it.value }
        val mostCardsOfOneValue = otherCardsByValue.values.maxOfOrNull { it.size } ?: 0
        val numberOfJokers = cards.count { it == CardPart2.JOKER }

        val handType = when {
            numberOfJokers + mostCardsOfOneValue == 5 -> HandType.FIVE_OF_A_KIND
            numberOfJokers + mostCardsOfOneValue == 4 -> HandType.FOUR_OF_A_KIND
            (otherCardsByValue.values.count { it.size == 2 } == 2 && numberOfJokers == 1) ||
                    mostCardsOfOneValue == 3 && otherCardsByValue.values.any { it.size == 2 } -> HandType.FULL_HOUSE

            numberOfJokers + mostCardsOfOneValue == 3 -> HandType.THREE_OF_A_KIND
            otherCardsByValue.values.count { it.size == 2 } == 2 -> HandType.TWO_PAIR
            numberOfJokers + mostCardsOfOneValue == 2 -> HandType.ONE_PAIR
            else -> HandType.HIGH_CARD
        }
        return handType
    }
}