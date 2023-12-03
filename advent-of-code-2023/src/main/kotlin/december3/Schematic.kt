package december3

data class Schematic(
    val numbers: List<SchematicNumber>,
    val symbols: List<SchematicSymbol>
)

data class SchematicNumber(
    val value: Int,
    val startX: Int,
    val endX: Int,
    val y: Int
)

data class SchematicSymbol(
    val value: Char,
    val x: Int,
    val y: Int
)