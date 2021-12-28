package com.angle.lib_net

import java.lang.RuntimeException

class ApiException(errorCode: Int?, errorMsg: String?) : RuntimeException()