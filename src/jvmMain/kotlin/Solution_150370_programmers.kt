import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.HashMap


fun main(){
    //today	terms	privacies	result
    //"2022.05.19"	["A 6", "B 12", "C 3"]	["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]	[1, 3]
    //"2020.01.01"	["Z 3", "D 5"]	["2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"]	[1, 4, 5]
    var solution = Solution()
    var today = "2024.01.01"
    var day2 = "2024.01.02"
    var diff = solution.diffDateMonth(today, day2, 1)

    var answer: IntArray = intArrayOf()
    answer.plus(1)
    println(answer.contentToString())

}

class Solution{
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        var termMap = HashMap<String, Long>()

        for (term in terms){
            var words = term.split(" ")
            termMap.put(words.get(0), words.get(1).toLong())
        }

        for ( (index,privacy) in privacies.withIndex() ){
            var words = privacy.split(" ")
            var date = words.get(0)
            var term = termMap.get(words.get(1)) as Long
            var diff = diffDateMonth(today, date, term)
            if(diff){
                println("add answer $index")
                answer = answer.plus(index+1)
            }
        }

        return answer
    }

    fun diffDateMonth(today:String, enrollDate: String, targetDiff: Long): Boolean{
        var fomatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        var todayDate = LocalDate.parse(today,fomatter)
        var monthForward = LocalDate.parse(enrollDate,fomatter).plusMonths(targetDiff)
        // println("DEBUG : enrollDate[$enrollDate] + $targetDiff month = dueDate[$monthForward] today[$today] ")
        return todayDate >= monthForward
    }

}