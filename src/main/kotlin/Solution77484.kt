class Solution77484 {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val ranks: IntArray = intArrayOf(6, 6, 5, 4, 3, 2, 1)
        val zeroCounts: Int = lottos.count { it == 0 }
        val dup: Set<Int> = lottos.toHashSet().intersect(win_nums.toHashSet())
        return intArrayOf(ranks[zeroCounts + dup.size], ranks[dup.size])
    }
}
