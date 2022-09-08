import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution92341Test {
    val solution = Solution92341()::solution

    @ParameterizedTest
    @MethodSource("testCases")
    fun `주차 어쩌고 저쩌고 문제`(fees: IntArray, records: Array<String>, answer: IntArray) {
        val result: IntArray = solution(fees, records)
        assert(result.contentEquals(answer)) { "Expected: $answer, Actual: $result" }
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    intArrayOf(180, 5000, 10, 600),
                    arrayOf(
                        "05:34 5961 IN",
                        "06:00 0000 IN",
                        "06:34 0000 OUT",
                        "07:59 5961 OUT",
                        "07:59 0148 IN",
                        "18:59 0000 IN",
                        "19:09 0148 OUT",
                        "22:59 5961 IN",
                        "23:00 5961 OUT",
                    ),
                    intArrayOf(14600, 34400, 5000)
                ),
                Arguments.of(
                    intArrayOf(1, 461, 1, 10),
                    arrayOf("00:00 1234 IN"),
                    intArrayOf(14841)
                )
            )
        }
    }


}