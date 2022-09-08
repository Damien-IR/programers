import java.util.*

class Solution72410 {
    private val correctPtrn = "[^a-z0-9-_.]".toRegex()
    private val twoMoreDotPtrn = "[.]{2,}".toRegex()
    private val locale = Locale.getDefault()

    fun solution(new_id: String): String {
        return new_id.lowercase(locale)
            .replace(correctPtrn, "")
            .replace(twoMoreDotPtrn, ".")
            .removePrefix(".")
            .removeSuffix(".")
            .let { it.ifEmpty { "a" } }
            .let {
                if (it.length >= 16) {
                    it.substring(0, 15).removeSuffix(".")
                } else it
            }.let {
                if (it.length <= 2) {
                    it + it.last().toString().repeat(3 - it.length)
                } else it
            }
    }
}
