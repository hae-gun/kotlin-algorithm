package week3

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import week2.Solution_155652_programmers

class Solution_92342_programmersTest: StringSpec({
    "프로그래머스 문제 풀이 92342"{
        forAll(
            row(5, intArrayOf(2,1,1,1,0,0,0,0,0,0,0),arrayOf(0,2,2,0,1,0,0,0,0,0,0)),
            row(1, intArrayOf(1,0,0,0,0,0,0,0,0,0,0),arrayOf(-1)),
            row(9, intArrayOf(0,0,1,2,0,1,1,1,1,1,1),arrayOf(1,1,2,0,1,2,2,0,0,0,0)),
            row(10, intArrayOf(0,0,0,0,0,0,0,0,3,4,3),arrayOf(1,1,1,1,1,1,1,1,0,0,2)),
        ) { n, info, expected ->
            val solution = Solution_92342_programmers()
            val result = solution.solution(n, info);
            result shouldBe expected
        }
    }
})
