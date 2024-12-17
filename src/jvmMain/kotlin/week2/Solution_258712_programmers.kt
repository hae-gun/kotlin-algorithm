package week2

class Solution_258712_programmers {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        // 각 친구가 준 선물과 받은 선물을 저장할 맵
        val sentGifts = mutableMapOf<String, Int>().apply { friends.forEach { this[it] = 0 } }
        val receivedGifts = mutableMapOf<String, Int>().apply { friends.forEach { this[it] = 0 } }

        // 선물 기록 처리
        gifts.forEach { gift ->
            val (giver, receiver) = gift.split(" ")
            sentGifts[giver] = sentGifts[giver]!! + 1
            receivedGifts[receiver] = receivedGifts[receiver]!! + 1
        }

        // 선물 지수 계산
        // associateWith -> 해당 키값을 갖는 키로 값을 계산하여 저장
        val giftIndex = friends.associateWith { friend ->
            sentGifts[friend]!! - receivedGifts[friend]!!
        }


        // 다음 달에 받을 선물 계산
        val nextMonthGifts = mutableMapOf<String, Int>()
            .apply {
                friends.forEach { this[it] = 0 }
            }
        for (i in friends.indices) {
            for (j in i + 1 until friends.size) {
                val friend1 = friends[i]
                val friend2 = friends[j]
                val sentToFriend1 = gifts.count { it == "$friend2 $friend1" }
                val sentToFriend2 = gifts.count { it == "$friend1 $friend2" }

                when {
                    sentToFriend1 > sentToFriend2 -> nextMonthGifts[friend2] = nextMonthGifts[friend2]!! + 1
                    sentToFriend2 > sentToFriend1 -> nextMonthGifts[friend1] = nextMonthGifts[friend1]!! + 1
                    giftIndex[friend1]!! > giftIndex[friend2]!! -> nextMonthGifts[friend1] = nextMonthGifts[friend1]!! + 1
                    giftIndex[friend1]!! < giftIndex[friend2]!! -> nextMonthGifts[friend2] = nextMonthGifts[friend2]!! + 1
                    else -> {}
                }
            }
        }
        // 가장 많이 받을 선물 수 반환
        return nextMonthGifts.values.maxOrNull() ?: 0
    }
}