import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import java.util.*

class ParameterizedTest : StringSpec({
    "프로그래머스 문제 풀이 150370"{
        forAll(
            row("2022.05.19", arrayOf("A 6", "B 12", "C 3")	, arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"), intArrayOf(1, 3)),
            row("2020.01.01",	arrayOf("Z 3", "D 5"),	arrayOf("2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"),	intArrayOf(1, 4, 5))
        ) { today, terms, privacies, expected ->
            val solution = Solution()
            val result = solution.solution(today, terms, privacies);
            result shouldBe expected
        }
    }
})