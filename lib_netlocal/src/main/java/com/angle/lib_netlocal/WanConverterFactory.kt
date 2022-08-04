package com.angle.lib_netlocal

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonResponseBodyConverter
import java.lang.reflect.Type


class WanConverterFactory(private val gson: Gson) : Converter.Factory() {


    companion object {
        fun create(): WanConverterFactory {
            return create(Gson())
        }

        fun create(gson: Gson): WanConverterFactory {
            return WanConverterFactory(gson)
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): Converter<ResponseBody, *> {
//        val adapter = gson.getAdapter(TypeToken.get(type))
//        return WanGsonResponseBodyConverter(gson, type)
        val adapter = gson.getAdapter(TypeToken.get(type))
        return WanGsonResponseBodyConverter<Any>(gson, type)

//        return if (type == String::class.java) {
//            val adapter = gson.getAdapter(TypeToken.get(type))
//            WanGsonResponseBodyConverter<String>(gson,type)
//        } else {
//            null
//        }
    }
}