import kotlin.math.ceil

class Solution92341 {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        return records.asSequence()
            .map { it.split(" ") }
            .groupBy { it[1] }
            .map {
                var sum: Int = 0
                val q = ArrayDeque(it.value)
                while (q.isNotEmpty()) {
                    val (time, carId, action) = q.removeFirst()
                    sum += if (q.isEmpty()) {
                        timeDiff(time, "23:59")
                    } else {
                        val (nextTime, nextCarId, nextAction) = q.removeFirst()
                        timeDiff(time, nextTime)
                    }
                }
                it.key to calFees(fees, sum)
            }
            .toSortedSet(compareBy { it.first })
            .map { it.second }
            .toIntArray()
    }

    fun timeDiff(hhmm1: String, hhmm2: String): Int {
        fun hhmmToInt(hhmm: String): Int {
            val (hh, mm) = hhmm.split(":").map { it.toInt() }
            return hh * 60 + mm
        }
        return hhmmToInt(hhmm2) - hhmmToInt(hhmm1)
    }

    fun calFees(fees: IntArray, time: Int): Int {
        val (defTime, defFee, overTime, overFee) = fees
        return if (time <= defTime) {
            defFee
        } else {
            ceil((time - defTime) / overTime.toDouble()).toInt() * overFee + defFee
        }
    }
}
