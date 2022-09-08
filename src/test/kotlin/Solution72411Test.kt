import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution72411Test {
    val solution = Solution72411()::solution

    @ParameterizedTest
    @MethodSource("provideTestCases")
    fun `메뉴 리뉴얼`(orders: Array<String>, course: IntArray, expected: Array<String>) {
        val actual = solution(orders, course)
        assert(actual.contentEquals(expected)) {
            "Expected ${expected.contentToString()} but was ${actual.contentToString()}"
        }
    }

    companion object {
        @JvmStatic
        fun provideTestCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"),
                    intArrayOf(2, 3, 4),
                    arrayOf("AC", "ACDE", "BCFG", "CDE")
                ),
                Arguments.of(
                    arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"),
                    intArrayOf(2, 3, 5),
                    arrayOf("ACD", "AD", "ADE", "CD", "XYZ")
                ),
                Arguments.of(
                    arrayOf("XYZ", "XWY", "WXA"),
                    intArrayOf(2, 3, 4),
                    arrayOf("WX", "XY")
                ),
            )
        }
    }
}