package com.gumigames.domain.util


sealed class NetworkThrowable(val code: Int, message: String) : Throwable(message) {
    // TODO 클래스 이름들 변경 필요 ( ex : AuthorizationThrowable )
    // 100번대 에러
    class Base100Throwable(code: Int, message: String) : NetworkThrowable(code, message)

    // 300번대 에러
    class Base300Throwable(code: Int, message: String) : NetworkThrowable(code, message)

    // 400번대 에러
    class Base400Throwable(code: Int, message: String) : NetworkThrowable(code, message)

    // 500번대 에러
    class Base500Throwable(code: Int, message: String) : NetworkThrowable(code, message)

    // body가 null일 때의 에러
    class IllegalStateThrowable : NetworkThrowable(ILLEGAL_STATE_THROWABLE_CODE, ILLEGAL_STATE_THROWABLE_MESSAGE)

    class NetworkErrorThrowable : NetworkThrowable(NETWORK_ERROR_CODE, NETWORK_ERROR_MESSAGE)

    // refresh Token이 만료됐을 때의 throwable
    class RefreshExpireThrowable : NetworkThrowable(REFRESH_EXPIRE_CODE, REFRESH_EXPIRE_MESSAGE)

    companion object {
        const val ILLEGAL_STATE_THROWABLE_CODE = 999
        const val ILLEGAL_STATE_THROWABLE_MESSAGE = "잘못된 값입니다."

        const val NETWORK_ERROR_CODE = 998
        const val NETWORK_ERROR_MESSAGE = "네트워크 연결을 확인해주세요."

        const val REFRESH_EXPIRE_CODE = 997
        const val REFRESH_EXPIRE_MESSAGE = "다시 로그인 해주세요"
    }
}


