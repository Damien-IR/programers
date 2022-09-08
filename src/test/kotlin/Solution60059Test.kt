import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution60059Test {
    private val solution = Solution60059()::solution

    @ParameterizedTest
    @MethodSource("provider")
    fun test(key: Array<IntArray>, lock: Array<IntArray>, expected: Boolean) {
        val result = solution(key, lock)
        assert(result == expected) { "expected: $expected, result: $result" }
    }

    companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(
                        intArrayOf(0, 0, 0),
                        intArrayOf(1, 0, 0),
                        intArrayOf(0, 1, 1)
                    ),
                    arrayOf(
                        intArrayOf(1, 1, 1),
                        intArrayOf(1, 1, 0),
                        intArrayOf(1, 0, 1)
                    ),
                    true,
                ),
                Arguments.of(
                    arrayOf(
                        intArrayOf(0, 0),
                        intArrayOf(0, 0),
                    ),
                    arrayOf(
                        intArrayOf(1, 0, 0),
                        intArrayOf(1, 0, 0),
                        intArrayOf(1, 1, 1)
                    ),
                    false
                )
            )
        }
    }


}