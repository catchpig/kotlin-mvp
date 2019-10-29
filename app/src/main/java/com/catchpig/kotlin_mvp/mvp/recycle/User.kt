package com.catchpig.kotlin_mvp.mvp.recycle

import com.catchpig.annotation.Prefs
import com.catchpig.annotation.PrefsField

/**
 *
 * @author TLi2
 **/
@Prefs
data class User (
        @PrefsField
        val name:String
)