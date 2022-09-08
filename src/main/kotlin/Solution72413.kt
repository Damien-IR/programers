import java.util.*


class Solution72413 {
    data class Node(val pos: Int, val cost: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int {
            return this.cost - other.cost
        }
    }

    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val inf = 20000000
        val nodes = Array(n + 1) { ArrayList<Node>() }
        val queue = PriorityQueue<Node>()

        for ((start, end, cost) in fares) {
            nodes[start].add(Node(end, cost))
            nodes[end].add(Node(start, cost))
        }

        fun dijkstra(start: Int): IntArray {
            queue.clear()
            val dist = IntArray(n + 1) { inf }
            dist[start] = 0
            queue.add(Node(start, 0))
            while (queue.isNotEmpty()) {
                val cur = queue.poll()
                if (dist[cur.pos] < cur.cost) continue
                for (next in nodes[cur.pos]) {
                    if (dist[next.pos] > dist[cur.pos] + next.cost) {
                        dist[next.pos] = dist[cur.pos] + next.cost
                        queue.add(Node(next.pos, dist[next.pos]))
                    }
                }
            }
            return dist
        }

        val cache = arrayListOf(IntArray(n + 1) { inf })

        for (i in 1..n) {
            cache.add(dijkstra(i))
        }

        var answer = inf
        for (i in 1..n) {
            answer = minOf(answer, cache[s][i] + cache[i][a] + cache[i][b])
        }
        return answer
    }
}
