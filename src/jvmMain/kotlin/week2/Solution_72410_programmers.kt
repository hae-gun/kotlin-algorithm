package week2

class Solution_72410_programmers {
    fun solution(newId: String): String {
        return newId
            .lowercase() // 1단계: 소문자로 변환
            .replace(Regex("[^-_.a-z0-9]"), "") // 2단계: 허용되지 않은 문자 제거
            .replace(Regex("\\.+"), ".") // 3단계: 연속된 점을 하나의 점으로 치환
            .trim('.') // 4단계: 시작과 끝의 점 제거
            .let { it.ifEmpty { "a" } } // 5단계: 빈 문자열 처리
            .let { if (it.length >= 16) it.take(15).trim('.') else it } // 6단계: 길이 조정 및 끝의 점 제거
            .let { if (it.length < 3) it.padEnd(3, it.last()) else it } // 7단계: 길이가 2 이하인 경우 마지막 문자를 반복해 3자까지 확장
    }


    class Solution {

        companion object {
            val regexAllowed = Regex("[^-_.a-z0-9]") // 허용되지 않은 문자 제거
            val regexDots = Regex("[.]{2,}")         // 연속된 마침표 제거
            val regexEdgeDots = Regex("^[.]|[.]$")  // 처음과 끝의 마침표 제거
            val trailingDotRegex = Regex("[.]$")    // 마지막 마침표 제거
        }

        fun solution(new_id: String): String {
            var answer = new_id.lowercase()

            answer = answer.replace(regexAllowed, "") // 2~4단계: 정규식을 캐싱한 객체로 처리
                .replace(regexDots, ".")
                .replace(regexEdgeDots, "")

            if (answer.isEmpty()) { // 5단계: 빈 문자열 처리
                answer = "a"
            }

            if (answer.length >= 16) { // 6단계: 길이 제한 및 끝 마침표 제거
                answer = answer.substring(0, 15).replace(trailingDotRegex, "")
            }

            while (answer.length <= 2) { // 7단계: 최소 길이 확장
                answer += answer.last()
            }

            return answer
        }

    }
}