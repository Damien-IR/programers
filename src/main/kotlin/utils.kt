import java.util.Collections;
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashSet

fun <T> Iterable<T>.combinations(length: Int): Sequence<List<T>> =
    sequence {
        val pool = this@combinations as? List<T> ?: toList()
        val n = pool.size
        if (length > n) return@sequence
        val indices = IntArray(length) { it }
        while (true) {
            yield(indices.map { pool[it] })
            var i = length
            do {
                i--
                if (i == -1) return@sequence
            } while (indices[i] == i + n - length)
            indices[i]++
            for (j in i + 1 until length) indices[j] = indices[j - 1] + 1
        }
    }

fun <T> Iterable<T>.permutations(length: Int? = null): Sequence<List<T>> =
    sequence {
        val pool = this@permutations as? List<T> ?: toList()
        val n = pool.size
        val r = length ?: n
        if (r > n) return@sequence
        val indices = IntArray(n) { it }
        val cycles = IntArray(r) { n - it }
        yield(List(r) { pool[indices[it]] })
        if (n == 0) return@sequence
        cyc@ while (true) {
            for (i in r - 1 downTo 0) {
                cycles[i]--
                if (cycles[i] == 0) {
                    val temp = indices[i]
                    for (j in i until n - 1) indices[j] = indices[j + 1]
                    indices[n - 1] = temp
                    cycles[i] = n - i
                } else {
                    val j = n - cycles[i]
                    indices[i] = indices[j].also { indices[j] = indices[i] }
                    yield(List(r) { pool[indices[it]] })
                    continue@cyc
                }
            }
            return@sequence
        }
    }

fun <T> permutations(list: List<T>): Set<List<T>> {
    if (list.isEmpty()) return setOf(emptyList())

    val result: MutableSet<List<T>> = mutableSetOf()
    for (i in list.indices) {
        permutations(list - list[i]).forEach { item ->
            result.add(item + list[i])
        }
    }
    return result
}

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

operator fun Number.compareTo(x: Number): Int {
    return when {
        this < x -> -1
        this > x -> 1
        else -> 0
    }
}

fun <T : Comparable<T>> Array<T>.bisectLeft(x: T, lo: Int = 0, hi: Int = this.size): Int {
    var low = lo
    var high = hi
    if (low < 0) {
        throw IndexOutOfBoundsException("lo must be non-negative")
    }
    while (low < high) {
        val mid = (low + high) / 2
        if (this[mid] < x) low = mid + 1
        else high = mid
    }
    return low
}

fun <T : Comparable<T>> List<T>.bisectLeft(x: T, lo: Int = 0, hi: Int = this.size): Int {
    var low = lo
    var high = hi
    if (low < 0) {
        throw IndexOutOfBoundsException("lo must be non-negative")
    }
    while (low < high) {
        val mid = (low + high) / 2
        if (this[mid] < x) low = mid + 1
        else high = mid
    }
    return low
}

fun <T : Comparable<T>> Array<T>.bisectRight(x: T, lo: Int = 0, hi: Int = this.size): Int {
    var low = lo
    var high = hi
    if (low < 0) {
        throw IndexOutOfBoundsException("lo must be non-negative")
    }
    while (low < high) {
        val mid = (low + high) / 2
        if (x < this[mid]) high = mid
        else low = mid + 1
    }
    return low
}

fun <T : Comparable<T>> List<T>.bisectRight(x: T, lo: Int = 0, hi: Int = this.size): Int {
    var low = lo
    var high = hi
    if (low < 0) {
        throw IndexOutOfBoundsException("lo must be non-negative")
    }
    while (low < high) {
        val mid = (low + high) / 2
        if (x < this[mid]) high = mid
        else low = mid + 1
    }
    return low
}
