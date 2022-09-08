import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution92342Test {
    val solution = Solution92342()::solution

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun solution92342Test(n: Int, intArr: IntArray, answer: IntArray) {
        val result = solution(n, intArr)
        assert(result.contentEquals(answer)) {
            "Expected ${answer.contentToString()} but got ${result.contentToString()}"
        }
    }

    companion object {
        @JvmStatic
        fun provideTestData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    5,
                    intArrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0),
                    intArrayOf(0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0)
                ),
                Arguments.of(
                    1,
                    intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                    intArrayOf(-1)
                ),
                Arguments.of(
                    9,
                    intArrayOf(0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1),
                    intArrayOf(1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0)
                ),
                Arguments.of(
                    10,
                    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3),
                    intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2)
                )
            )
        }
    }
}
