import java.time.LocalDate
import java.time.format.DateTimeFormatter


class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        val termMap = HashMap<String, Long>()
        for (term in terms) {
            val words = term.split(" ")
            termMap.put(words.get(0), words.get(1).toLong())
        }

        for ((index, privacy) in privacies.withIndex()) {
            if (checkDateTimeExpired(privacy, today, termMap)) {
                answer = answer.plus(index + 1)
            }
        }

        return answer
    }

    fun solution2(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        val termMap = HashMap<String, Long>()

        terms.forEach { term ->
            val words = term.split(" ")
            termMap[words[0]] = words[1].toLong()
        }

        privacies.forEachIndexed { index, privacy ->
            if (checkDateTimeExpired(privacy, today, termMap)) {
                answer = answer.plus(index + 1)
            }
        }
//    associate
        return answer
    }

    private fun checkDateTimeExpired(privacy: String, today: String, termMap: Map<String, Long>): Boolean {
        val word = privacy.split(" ")
        val date = word.get(0)
        val term = termMap.get(word.get(1)) as Long
        return diffDateMonth(today, date, term)
    }

    private fun diffDateMonth(today: String, enrollDate: String, targetDiff: Long): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val todayDate = LocalDate.parse(today, formatter)
        val monthForward = LocalDate.parse(enrollDate, formatter).plusMonths(targetDiff)
        // println("DEBUG : enrollDate[$enrollDate] + $targetDiff month = dueDate[$monthForward] today[$today] ")
        return todayDate >= monthForward
    }

}