import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import week2.Solution_155652_programmers

class Solution_155652_programmersTest: StringSpec({
    "프로그래머스 문제 풀이 155652"{
        forAll(
            //"aukks"	"wbqd"	5	"happy"
            row("aukks","wbqd",5,"happy")
        ) { s, skip, index, expected ->
            val solution = Solution_155652_programmers()
            val result = solution.solution(s, skip, index);
            result shouldBe expected
        }
    }
})