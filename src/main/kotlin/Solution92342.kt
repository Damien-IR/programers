class Solution92342 { // 양궁 대회
    fun solution(n: Int, info: IntArray): IntArray {
        val cacheMap = hashMapOf<ArrayList<Int>, Pair<Int, Int>>()
        val deque = ArrayDeque<Pair<ArrayList<Int>, Int>>()
        deque.add(Pair(arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), n))
        while (deque.isNotEmpty()) {
            val (arr, count) = deque.removeFirst()
            if (count == 0) {
                continue
            }
            for ((idx, ryanHit) in arr.withIndex()) {
                val nextHitArr: ArrayList<Int> = arr.clone() as ArrayList<Int>
                val peachHit = info[idx]
                val ryanNextHit = ryanHit + 1
                nextHitArr[idx] = ryanNextHit
                if (!cacheMap.containsKey(nextHitArr) && ryanHit <= peachHit) {
                    val scorePair: Pair<Int, Int> = calTotalScore(info, nextHitArr)
                    cacheMap[nextHitArr] = scorePair
                    deque.add(Pair(nextHitArr, count - 1))
                }

            }
        }
        return cacheMap.mapValues { it.value.second - it.value.first }
            .asSequence()
            .groupBy { it.value }
            .maxByOrNull { it.key }
            ?.let {
                if (it.key <= 0) return intArrayOf(-1)
                else it.value
            }
            ?.map { it.key }
            ?.filter { it.sum() == n }
            ?.sortedWith(
                compareBy(
                    { it[10] }, { it[9] }, { it[8] }, { it[7] }, { it[6] },
                    { it[5] }, { it[4] }, { it[3] }, { it[2] }, { it[1] },
                    { it[0] }
                )
            )
            ?.last()
            ?.toList()
            ?.toIntArray()
            ?: intArrayOf(-1)
    }

    fun calTotalScore(info: IntArray, arr: ArrayList<Int>): Pair<Int, Int> {
        var peachScore = 0
        var ryanScore = 0
        for ((idx, pair) in (info zip arr).withIndex()) {
            val targetScore = 10 - idx
            val (peachHit, ryanHit) = pair
            val calResult = calTargetPair(targetScore, peachHit, ryanHit)
            peachScore += calResult.first
            ryanScore += calResult.second
        }
        return Pair(peachScore, ryanScore)
    }

    // peach, ryan
    fun calTargetPair(score: Int, peachHit: Int, ryanHit: Int): Pair<Int, Int> {
        // info content must be 0 <= x <= n
        return if (ryanHit > peachHit) {
            Pair(0, score)
        } else if (ryanHit == peachHit) {
            if (peachHit == 0) Pair(0, 0) else Pair(score, 0)
        } else {
            Pair(score, 0)
        }
    }
}
