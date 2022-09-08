import java.util.Collections;
import kotlin.collections.ArrayList

class Solution72412 {
    fun <T> product(a: Collection<T>, r: Int): List<Collection<T>> {
        var result = Collections.nCopies<Collection<T>>(1, emptyList())
        for (pool in Collections.nCopies(r, LinkedHashSet(a))) {
            val temp: MutableList<Collection<T>> = arrayListOf()
            for (x in result) {
                for (y in pool) {
                    val z: MutableCollection<T> = ArrayList(x)
                    z.add(y)
                    temp.add(z)
                }
            }
            result = temp
        }
        return result
    }

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val map = hashMapOf<String, ArrayList<Int>>()
        val areaCheck = product(arrayListOf(false, true), 4)
                as List<List<Boolean>>

        for (i in info) {
            val candidate = i.split(' ')
            for (area in areaCheck) {
                val stringBuffer = StringBuffer()
                for (k in 0 until 4) {
                    if (area[k]) {
                        stringBuffer.append(candidate[k])
                    } else {
                        stringBuffer.append('-')
                    }
                }
                val key = stringBuffer.toString()
                map[key] = map.getOrDefault(key, arrayListOf())
                map[key]?.add(candidate.last().toInt())
            }
        }

        map.forEach { it.value.sort() }

        val answer = arrayListOf<Int>()
        for (q in query) {
            val qs: List<String> = q.split(' ')
            val key = "${qs[0]}${qs[2]}${qs[4]}${qs[6]}"
            val score: Int = qs.last().toInt()
            val idx = map[key]?.bisectLeft(score) ?: 0
            answer.add((map[key]?.size ?: 0) - idx)
        }

        return answer.toIntArray()
    }
}
