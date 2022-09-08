import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution92334Test {
    val solution = Solution92334()::solution

    @ParameterizedTest
    @MethodSource("testCases")
    fun `신고 결과 받기 테스트`(id_list: Array<String>, report: Array<String>, k: Int, expected: IntArray) {
        val actual = solution(id_list, report, k)
        assertArrayEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf("muzi", "frodo", "apeach", "neo"),
                    arrayOf("muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"),
                    2,
                    intArrayOf(2, 1, 1, 0)
                ),
                Arguments.of(
                    arrayOf("con", "ryan"),
                    arrayOf("ryan con", "ryan con", "ryan con", "ryan con"),
                    3,
                    intArrayOf(0, 0)
                )
            )
        }
    }
}