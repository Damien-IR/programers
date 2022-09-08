import kotlin.math.sqrt

class Solution92335 {

    fun solution(n: Int, k: Int): Int {
        val result = convertNotation(n, k)
        return result.count { isPrimeNumber(it) }
    }

    fun convertNotation(n: Int, k: Int): List<Int> {
        val stringBuffer = StringBuffer()
        var n = n
        while (n > 0) {
            stringBuffer.append(n % k)
            n /= k
        }
        return stringBuffer
            .reverse()
            .toString()
            .split("0")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }
    }

    fun isPrimeNumber(n: Int): Boolean {
        if (n == 1) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }
}
