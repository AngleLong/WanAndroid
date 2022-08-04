package com.angle.lib_netlocal

import java.lang.RuntimeException

class ApiException(val errorCode: Int?, val errorMsg: String?) : RuntimeException()