package utils

import java.io.File

fun readLinesFromResourceFile(folder: String, filename: String): List<String> =
    File("src/main/resources/$folder/$filename").readLines()