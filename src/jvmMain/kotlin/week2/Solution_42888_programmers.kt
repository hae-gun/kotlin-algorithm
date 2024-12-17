package week2

class Solution_42888_programmers {
    fun solution(record: Array<String>): Array<String> {
        val idMap = mutableMapOf<String, String>() // ID와 이름을 매핑

        // 로그 데이터를 처리하여 ID와 이름 매핑 및 이벤트 로그 생성
        val enterLog = record.mapNotNull { log ->
            val (action,id,nickName) = log.split(" ").let {
                Triple(it[0], it[1], it.getOrNull(2) ?: "")
            }

            if (action != "Leave") {
                idMap[id] = nickName // ID에 해당하는 이름 저장
            }

            when (action) {
                "Enter" -> "Enter" to id
                "Leave" -> "Leave" to id
                else -> null // Change는 로그에 추가하지 않음
            }
        }

        // 로그 데이터를 메시지로 변환
        return enterLog.map { (action, id) ->
            val name = idMap[id] ?: "Unknown"
            if (action == "Enter") {
                "${name}님이 들어왔습니다."
            } else {
                "${name}님이 나갔습니다."
            }
        }.toTypedArray()
    }
}