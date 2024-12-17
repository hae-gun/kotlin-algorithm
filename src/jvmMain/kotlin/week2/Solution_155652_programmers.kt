package week2

class Solution_155652_programmers {
    fun solution(s: String, skip: String, index: Int): String {
        var answer: String = ""

        val charRange = 'a'..'z'

        val isSkip:Map<Char,Boolean> = charRange.associateWith { word ->
            skip.contains(word)
        }

        answer = s.map { word ->
            var idx = index
            var returnWord = word
            while (idx > 0) {
                if(returnWord=='z') returnWord = 'a'
                else returnWord += 1

                if (isSkip[returnWord]!!) {
                    continue
                }
                idx -= 1
            }
            returnWord
        }.joinToString("")


        return answer
    }
}