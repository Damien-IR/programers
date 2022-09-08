class Solution92334 {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val mailMap = hashMapOf<String, MutableSet<String>>()
        val reportMap = hashMapOf<String, MutableSet<String>>()
        for (id in id_list) {
            mailMap[id] = hashSetOf()
        }

        for (repStr in report) {
            val (reporter, troll) = repStr.split(" ")
            mailMap[reporter]?.add(troll) ?: run { mailMap[reporter] = hashSetOf(troll) }
            reportMap[troll]?.add(reporter) ?: run { reportMap[troll] = hashSetOf(reporter) }
        }

        val result = IntArray(id_list.size)
        for (id in id_list) {
            val mailSet = mailMap.getValue(id)
            var count = 0
            for (troll in mailSet) {
                if ((reportMap[troll]?.count() ?: 0) >= k) {
                    count += 1
                }
            }
            result[id_list.indexOf(id)] = count
        }
        return result
    }
}
