import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution92335Test {
    val solution = Solution92335()::solution

    @ParameterizedTest
    @MethodSource("provideTestCases")
    fun `k진수에서 소수 개수 구하기 테스트`(n: Int, k : Int, answer: Int) {
        val result: Int = solution(n, k)
        assert(result == answer) { "Expected $answer, but got $result" }
    }

    companion object {
        @JvmStatic
        fun provideTestCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(437674, 3, 3),
                Arguments.of(110011, 10, 2)
            )
        }
    }
}