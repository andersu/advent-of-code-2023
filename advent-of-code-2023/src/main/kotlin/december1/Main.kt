package december1

import utils.readLinesFromResourceFile

fun main() {
    val trebuchetCalibrator = TrebuchetCalibrator()
    val lines = readLinesFromResourceFile("december1", "input.txt")

    val resultPart1 = trebuchetCalibrator.calibrate(lines)
    println("Calibration value part 1: $resultPart1")

    val resultPart2 = trebuchetCalibrator.calibrateSupportingDigitsAsText(lines)
    println("Calibration value part 2: $resultPart2")
}