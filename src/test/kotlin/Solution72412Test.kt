import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution72412Test {
    val solution = Solution72412()::solution

    @ParameterizedTest
    @MethodSource("testCases")
    fun `순위 검색`(info: Array<String>, query: Array<String>, expected: IntArray) {
        val actual = solution(info, query)
        assert(actual.contentEquals(expected)) { "Expected: $expected, Actual: $actual" }
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(
                        "java backend junior pizza 150",
                        "python frontend senior chicken 210",
                        "python frontend senior chicken 150",
                        "cpp backend senior pizza 260",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"
                    ),
                    arrayOf(
                        "java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250",
                        "- and backend and senior and - 150",
                        "- and - and - and chicken 100",
                        "- and - and - and - 150"
                    ),
                    intArrayOf(1, 1, 1, 1, 2, 4)
                )
            )
        }
    }
}