package week3

class Solution_92342_programmers {
    fun solution(n: Int, info: IntArray): IntArray {
        var maxDifference = 0
        var answer = IntArray(11) { -1 }
        // 점수 계산 함수
        fun calculateScore(ryan: IntArray): Int {
            var apeachScore = 0
            var ryanScore = 0
            for (i in 0..10) {
                if (info[i] == 0 && ryan[i] == 0) continue
                if (info[i] >= ryan[i]) apeachScore += 10 - i
                else ryanScore += 10 - i
            }
            return ryanScore - apeachScore
        }
        // 백트래킹 함수
        fun backtrack(index: Int, arrowsLeft: Int, ryan: IntArray) {
            if (index == 11 || arrowsLeft == 0) {
                // 남은 화살을 0점에 추가
                if (arrowsLeft > 0) ryan[10] += arrowsLeft
                // 점수 차이 계산
                val scoreDiff = calculateScore(ryan)
                if (scoreDiff > maxDifference) {
                    maxDifference = scoreDiff
                    answer = ryan.clone() // 최적의 결과 갱신
                } else if (scoreDiff == maxDifference) {
                    // 낮은 점수를 더 많이 맞힌 경우를 우선
                    for (i in 10 downTo 0) {
                        if (ryan[i] > answer[i]) {
                            answer = ryan.clone()
                            break
                        } else if (ryan[i] < answer[i]) break
                    }
                }
                // 남은 화살 복원
                if (arrowsLeft > 0) ryan[10] -= arrowsLeft
                return
            }
            // 현재 과녁 점수에 화살을 쏘지 않는 경우
            backtrack(index + 1, arrowsLeft, ryan)
            // 현재 과녁 점수에 화살을 쏘는 경우 (어피치보다 1발 더 많이 쏨)
            if (arrowsLeft > info[index]) {
                ryan[index] = info[index] + 1
                backtrack(index + 1, arrowsLeft - ryan[index], ryan)
                ryan[index] = 0 // 백트래킹 시 원복
            }
        }
        // 백트래킹 시작
        backtrack(0, n, IntArray(11) { 0 })
        return if (maxDifference == 0) intArrayOf(-1) else answer
    }
}