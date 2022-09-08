import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution72410Test {
    val solution = Solution72410()::solution

    @ParameterizedTest
    @MethodSource("testCases")
    fun `신규 아이디 추천`(new_id: String, expected: String) {
        val actual = solution(new_id)
        assert(actual == expected) { "Expected $expected, but got $actual" }
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("...!@BaT#*..y.abcdefghijklm", "bat.y.abcdefghi"),
                Arguments.of("z-+.^.", "z--"),
                Arguments.of("=.=", "aaa"),
                Arguments.of("123_.def", "123_.def"),
                Arguments.of("abcdefghijklmn.p", "abcdefghijklmn"),
            )
        }
    }
}