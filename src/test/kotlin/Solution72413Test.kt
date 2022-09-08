import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class Solution72413Test {
    private val solution = Solution72413()

    @ParameterizedTest
    @MethodSource("solutionStream")
    fun test(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>, answer: Int): Unit {
        val result = solution.solution(n, s, a, b, fares)
        assert(result == answer) { "result=$result, answer=$answer" }
    }

    companion object {
        @JvmStatic
        private fun solutionStream(): ArrayList<Arguments> {
            return arrayListOf(
                Arguments.of(
                    6, 4, 6, 2, arrayOf(
                        intArrayOf(4, 1, 10),
                        intArrayOf(3, 5, 24),
                        intArrayOf(5, 6, 2),
                        intArrayOf(3, 1, 41),
                        intArrayOf(5, 1, 24),
                        intArrayOf(4, 6, 50),
                        intArrayOf(2, 4, 66),
                        intArrayOf(2, 3, 22),
                        intArrayOf(1, 6, 25)
                    ), 82
                ), Arguments.of(
                    7, 3, 4, 1, arrayOf(
                        intArrayOf(5, 7, 9),
                        intArrayOf(4, 6, 4),
                        intArrayOf(3, 6, 1),
                        intArrayOf(3, 2, 3),
                        intArrayOf(2, 1, 6)
                    ), 14
                ), Arguments.of(
                    6, 4, 5, 6, arrayOf(
                        intArrayOf(2, 6, 6),
                        intArrayOf(6, 3, 7),
                        intArrayOf(4, 6, 7),
                        intArrayOf(6, 5, 11),
                        intArrayOf(2, 5, 12),
                        intArrayOf(5, 3, 20),
                        intArrayOf(2, 4, 8),
                        intArrayOf(4, 3, 9),
                    ), 18
                )
            )
        }
    }
}
