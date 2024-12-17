import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.ints.shouldBeExactly
import week2.Solution_258712_programmers

class Solution_258712_programmersTest: StringSpec({
    "프로그래머스 문제 풀이 258712"{
        forAll(
            row(arrayOf("muzi", "ryan", "frodo", "neo"), arrayOf("muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"),2)
            ,row(arrayOf("joy", "brad", "alessandro", "conan", "david"), arrayOf("alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"),4)
            ,row(arrayOf("a", "b", "c"), arrayOf("a b", "b a", "c a", "a c", "a c", "c a"),0)
        ) { friends,gifts,expected ->
            val solution = Solution_258712_programmers()
            val result = solution.solution(friends, gifts)
            result shouldBeExactly expected
        }
    }
})