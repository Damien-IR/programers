import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution118668Test {
    private val solution = Solution118668()

    @ParameterizedTest
    @MethodSource("solutionStream")
    fun `코딩 테스트 공부 문제 테스트`(alp: Int, cop: Int, problems: Array<IntArray>, expected: Int) {
        solution.solution(alp, cop, problems).let {
            assert(it == expected) { "expected: $expected, actual: $it" }
        }
    }

    companion object {
        @JvmStatic
        fun solutionStream(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    10, 10, arrayOf(
                        intArrayOf(10, 15, 2, 1, 2),
                        intArrayOf(20, 20, 3, 3, 4)
                    ), 15
                ),
                Arguments.of(
                    0, 0, arrayOf(
                        intArrayOf(0,0,2,1,2),
                        intArrayOf(4,5,3,1,2),
                        intArrayOf(4,11,4,0,2),
                        intArrayOf(10,4,0,4,2),
                    ), 13
                )
            )
        }
    }
}