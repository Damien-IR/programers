class Solution72411 {
    data class Order(val foods: String) {
        fun comb(count: Int): Set<String> {
            return foods.toList()
                .combinations(count)
                .map { it.sorted().joinToString("") }
                .toHashSet()
        }

        fun combList(countList: IntArray): Set<String> {
            return countList.map { comb(it) }
                .reduceOrNull { acc, set -> acc.union(set) } ?: hashSetOf()
        }
    }

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val countMap = hashMapOf<String, Int>()
        val personMap = hashMapOf<String, List<Order>>()

        orders.asSequence()
            .map { Order(it) }
            .map { it to it.combList(course) }
            .forEach { (order, comb) ->
                comb.forEach {
                    countMap[it] = countMap.getOrDefault(it, 0) + 1
                    personMap[it] = personMap.getOrDefault(it, arrayListOf()) + order
                }
            }

        return countMap.asSequence()
            .groupBy { it.key.length }
            .map {
                val maxVal: Int = it.value.maxByOrNull { (k, v) -> v }?.value ?: 0
                it.key to it.value.filter { (k, v) ->
                    v == maxVal && (personMap[k]?.size ?: 0) > 1
                }
            }
            .map { it.second.map { innerIt -> innerIt.key } }
            .flatten()
            .toList()
            .sorted()
            .toTypedArray()
    }
}
