package com.catchpig.utils.ext

import android.text.Editable
import android.widget.EditText

/**
 * 创建时间:2019/8/28 0028<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/8/28 0028<br/>
 * 描述:输入框扩展类
 */

/**
 * 给输入框赋值
 */
fun EditText.setTextValue(text:String?){
    this.text = Editable.Factory.getInstance().newEditable(text)
}