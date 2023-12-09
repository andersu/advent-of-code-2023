package december9

data class OasisInformation(
    val valueHistories: List<OasisValueHistory>
)

data class OasisValueHistory(
    val values: List<Int>
)
