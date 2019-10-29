package com.catchpig.compiler

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

/**
 * 创建时间:2019/10/29 0029<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/29 0029<br/>
 * 描述:
 */
abstract class BaseProcessor:AbstractProcessor() {
    protected lateinit var elementUtils: Elements
    private lateinit var messager: Messager
    protected lateinit var filer: Filer

    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        elementUtils = processingEnv.elementUtils
        messager = processingEnv.messager
        filer = processingEnv.filer
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    protected fun warning(msg: String) {
        messager.printMessage(Diagnostic.Kind.WARNING, msg)
    }

    protected fun error(msg: String) {
        messager.printMessage(Diagnostic.Kind.ERROR, msg)
    }
}