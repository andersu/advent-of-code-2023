package december1

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import utils.readLinesFromResourceFile

class TrebuchetCalibratorTest {
    private val trebuchetCalibrator = TrebuchetCalibrator()

    @Test
    fun `Calculation is correct for test input`() {
        val lines = readLinesFromResourceFile("december1", "test_input_1.txt")
        val result = trebuchetCalibrator.calibrate(lines)

        result shouldBeEqualTo 142
    }

    @Test
    fun `Calculation supporting digits as text is correct for test input`() {
        val lines = readLinesFromResourceFile("december1", "test_input_2.txt")
        val result = trebuchetCalibrator.calibrateSupportingDigitsAsText(lines)

        result shouldBeEqualTo 281
    }
}