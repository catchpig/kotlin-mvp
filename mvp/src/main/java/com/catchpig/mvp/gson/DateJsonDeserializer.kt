package com.catchpig.mvp.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*

/**
 * 创建时间:2019/9/27 0027<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/9/27 0027<br/>
 * 描述:时间错转化时间类型
 */
class DateJsonDeserializer:JsonDeserializer<Date> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date {
        return if (json==null) {
            Date()
        }else{
            Date(json.asJsonPrimitive.asLong)
        }
    }
}