package com.angle.lib_net

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter
import java.lang.reflect.Type

class WanGsonResponseBodyConverter<T>(
    private val gson: Gson,
    val type: Type,/*, private val adapter: TypeAdapter<T>*/
) :
    Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T? {

//        val jsonReader = gson.newJsonReader(value.charStream())
//        try {
//            val baseBean: BaseBean<T> = adapter.read(jsonReader)
//        } catch (e: Exception) {
//
//        }


//        val baseBean = gson.fromJson(value.string(), BaseBean::class.java)

//        val type = object : TypeToken<BaseBean>>() {}.type

//        val jsonObject = JSONObject(value.string())


//        return gson.fromJson(baseBean.data, type)

        val jsonObject = JSONObject(value.string())
        if (jsonObject.optInt("errorCode") == 0) {
//            val data = jsonObject.optString("data")
            return gson.fromJson(jsonObject.optString("data"), type)
        } else {
            throw ApiException(jsonObject.optInt("errorCode"),
                jsonObject.optString("errorMsg"))
        }


    }
}