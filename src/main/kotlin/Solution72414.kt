class Solution72414 {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        fun String.toSecond(): Long {
            val (hour: String, minute: String, second: String) = this.split(":")
            return hour.toLong() * 3600 + minute.toLong() * 60 + second.toLong()
        }

        fun Long.toTime(): String {
            val (hh: Long, mm: Long, ss: Long) = Triple(
                this / 3600, (this % 3600) / 60, this % 60
            )
            return "${hh.toString().padStart(2, '0')}:${
                mm.toString().padStart(2, '0')
            }:${ss.toString().padStart(2, '0')}"
        }

        val playTime: Long = play_time.toSecond()
        val advTime: Long = adv_time.toSecond()
        val startTimeList: MutableList<Long> = mutableListOf()
        val endTimeList: MutableList<Long> = mutableListOf()
        val totalTimeArr: Array<Long> = Array(playTime.toInt() + 1) { 0 }

        for (log: String in logs) {
            val (startTime, endTime) = log.split("-").map { it.toSecond() }
            startTimeList.add(startTime)
            endTimeList.add(endTime)
        }

        for (i: Int in 0 until startTimeList.size) {
            totalTimeArr[startTimeList[i].toInt()] += 1L
            totalTimeArr[endTimeList[i].toInt()] -= 1L
        }

        for (loop: Int in 0 until 2) {
            for (i: Long in 1 until playTime) {
                totalTimeArr[i.toInt()] += totalTimeArr[(i - 1).toInt()]
            }
        }

        var answer: Long = -1L
        var maxTime: Long = 0L
        for (i: Long in advTime - 1 until playTime) {
            val tempMaxTime: Long = if (i >= advTime) {
                maxOf(maxTime, totalTimeArr[i.toInt()] - totalTimeArr[(i - advTime).toInt()])
            } else {
                maxOf(maxTime, totalTimeArr[i.toInt()])
            }
            if (tempMaxTime > maxTime) {
                maxTime = tempMaxTime
                answer = i - advTime + 1
            }
        }

        return answer.toTime()
    }
}
