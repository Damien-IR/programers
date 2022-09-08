import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution77484Test {
    val solution = Solution77484()::solution

    @ParameterizedTest
    @MethodSource("provideTestCases")
    fun `로또의 최고 순위와 최저 순위`(lottos: IntArray, win_nums: IntArray, expected: IntArray) {
        val result = solution(lottos, win_nums)
        assert(result contentEquals expected) {
            "Expected ${expected.toList()} but got ${result.toList()}"
        }
    }

    companion object {
        @JvmStatic
        fun provideTestCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    intArrayOf(44, 1, 0, 0, 31, 25),
                    intArrayOf(31, 10, 45, 1, 6, 19),
                    intArrayOf(3, 5)
                ),
                Arguments.of(
                    intArrayOf(0, 0, 0, 0, 0, 0),
                    intArrayOf(38, 19, 20, 40, 15, 25),
                    intArrayOf(1, 6)
                ),
                Arguments.of(
                    intArrayOf(45, 4, 35, 20, 3, 9),
                    intArrayOf(20, 9, 3, 45, 4, 35),
                    intArrayOf(1, 1)
                ),
            )
        }
    }


}