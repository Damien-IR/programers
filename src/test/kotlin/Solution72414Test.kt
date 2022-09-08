import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Solution72414Test {
    private val solution = Solution72414()

    @ParameterizedTest
    @MethodSource("solutionStream")
    fun `광고 삽입 테스트`(play_time: String, adv_time: String, logs: Array<String>, result: String) {
        solution.solution(play_time, adv_time, logs).let {
            assert(it == result) { "expect: $result, actual: $it" }
        }
    }

    companion object {
        @JvmStatic
        fun solutionStream(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "02:03:55", "00:14:15", arrayOf(
                        "01:20:15-01:45:14",
                        "00:40:31-01:00:00",
                        "00:25:50-00:48:29",
                        "01:30:59-01:53:29",
                        "01:37:44-02:02:30"
                    ), "01:30:59"
                ),
                Arguments.of(
                    "99:59:59", "25:00:00", arrayOf(
                        "69:59:59-89:59:59",
                        "01:00:00-21:00:00",
                        "79:59:59-99:59:59",
                        "11:00:00-31:00:00"
                    ), "01:00:00"
                ),
                Arguments.of(
                    "50:00:00", "50:00:00", arrayOf(
                        "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"
                    ), "00:00:00"
                ),
            )
        }
    }
}