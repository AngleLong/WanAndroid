package com.angle.lib_net

import java.lang.RuntimeException

class ApiException(val errorCode: Int?, val errorMsg: String?) : RuntimeException()