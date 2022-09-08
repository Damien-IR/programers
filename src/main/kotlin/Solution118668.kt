import java.util.*


class Solution118668 {
    private val inf = Int.MAX_VALUE / 4

    open class Score(open val alp: Int = 0, open val cop: Int = 0)

    data class Node(override val alp: Int = 0, override val cop: Int = 0,
                    val reqAlp: Int, val reqCop: Int,
                    val cost: Int) :
        Score(alp, cop), Comparable<Node> {
        override fun compareTo(other: Node): Int {
            return this.cost - other.cost
        }
    }

    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        val maxAlp = problems.maxByOrNull { it[0] }?.get(0) ?: 0
        val maxCop = problems.maxByOrNull { it[1] }?.get(1) ?: 0

        fun isMoveNeeded(alp: Int, cop: Int, reqAlp: Int, reqCop: Int): Boolean {
            val alpLack = maxAlp - alp
            val copLack = maxCop - cop
            return (alp >= reqAlp && cop >= reqCop) && !(alp > maxAlp && cop > maxCop) &&
                    (alpLack >= 0 && copLack >= 0)
        }

        val dp = Array(151) { IntArray(151) { inf } }
        val queue = PriorityQueue<Node>()

        dp[alp][cop] = 0
        queue.add(Node(alp, cop, 0, 0, 0))

        while (queue.isNotEmpty()) {
            val curNode = queue.poll()
            if (curNode.alp == maxAlp && curNode.cop == maxCop) {
                return curNode.cost
            }
            val nextNodes = arrayListOf(
                Node(curNode.alp + 1, curNode.cop, 0, 0, curNode.cost + 1),
                Node(curNode.alp, curNode.cop + 1, 0, 0, curNode.cost + 1)
            )
            for (prob in problems) {
                val (alpReq, copReq, alpRwd, copRwd, cost) = prob
                val nxtAlp = curNode.alp + alpRwd
                val nxtCop = curNode.cop + copRwd
                val nxtCost = curNode.cost + cost

                nextNodes.add(Node(nxtAlp, nxtCop, alpReq, copReq, nxtCost))
            }

            for (nextNode in nextNodes) {
                if (isMoveNeeded(nextNode.alp, nextNode.cop, nextNode.reqAlp, nextNode.reqCop)) {
                    if (dp[nextNode.alp][nextNode.cop] > nextNode.cost) {
                        dp[nextNode.alp][nextNode.cop] = nextNode.cost
                        queue.add(nextNode)
                        if (nextNode.alp >= maxAlp && nextNode.cop >= maxCop) {
                            dp[maxAlp][maxCop] = nextNode.cost
                        }
                    }
                }
            }
        }

        return dp[maxAlp][maxCop]
    }
}
