package com.catchpig.compiler

import com.catchpig.annotation.Prefs
import com.catchpig.annotation.PrefsField
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeKind

/**
 * 创建时间:2019/10/29 0029<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2019/10/29 0029<br/>
 * 描述:Prefs注解生成器
 */
@AutoService(Processor::class)
class PrefsProcessor : BaseProcessor() {
    companion object {
        private val CLASS_NAME_SHARED_PREFERENCES_EDITOR = ClassName("android.content.SharedPreferences", "Editor")
        private val TYPE_KOTLIN_MVP_CONTENT_PROVIDER = Class.forName("com.catchpig.mvp.provider.KotlinMvpContentProvider")

    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        var set = HashSet<String>()
        set.add(Prefs::class.java.canonicalName)
        return set
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val elements = roundEnv.getElementsAnnotatedWith(Prefs::class.java)
        elements.map {
            it as TypeElement
        }.forEach {
            val prefs = it.getAnnotation(Prefs::class.java)
            val className = it.simpleName.toString()

            val fullPackageName = elementUtils.getPackageOf(it).qualifiedName.toString()

            val typeSpec = TypeSpec
                    .objectBuilder("${className}Prefs")
                    .addModifiers(KModifier.FINAL, KModifier.PUBLIC)
                    .addProperty(addEditorProperty())
                    .addInitializerBlock(addCodeBlock(prefs, className))
                    .addFunctions(addFuns(it))
                    .build()
            FileSpec
                    .builder(fullPackageName, typeSpec.name!!)
                    .addType(typeSpec)
                    .build()
                    .writeTo(filer)
        }
        return true
    }

    private fun addFuns(element: TypeElement): MutableList<FunSpec> {
        var funSpecs = ArrayList<FunSpec>()
        val fieldElements = elementUtils.getAllMembers(element)
        fieldElements.forEach {
            it.getAnnotation(PrefsField::class.java)?.let { prefsField ->
                val fieldName = it.simpleName.toString()
                val prefsKey = if (prefsField.value.isEmpty()) {
                    fieldName
                }else{
                    prefsField.value
                }
                val funName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)
                var setFunSpecBuilder = FunSpec
                        .builder("set${funName}")
                        .addParameter(fieldName, STRING)
                when (it.asType().kind) {
                    TypeKind.BOOLEAN -> {
                        setFunSpecBuilder.addStatement("sharedEditor.putString(%S,$fieldName).apply()",prefsKey)
                    }
                    else -> {
                        setFunSpecBuilder.addStatement("sharedEditor.putString(%S,$fieldName).apply()",prefsKey)
                    }
                }
                val setFunSpec = setFunSpecBuilder.build()


                val getFunSpec = FunSpec
                        .builder("get${funName}")
                        .returns(it.asType().asTypeName())
                        .build()

                funSpecs.add(setFunSpec)
                funSpecs.add(getFunSpec)
            }
        }
        return funSpecs
    }

    private fun addCodeBlock(prefs: Prefs, className: String): CodeBlock {
        var sharedPrefsName = if (prefs.value.isEmpty()) {
            className
        } else {
            prefs.value
        }
        return CodeBlock
                .builder()
                .addStatement("val sharedPrefs = %T.application.getSharedPreferences(\"SharedPrefs_$sharedPrefsName\",%L)", TYPE_KOTLIN_MVP_CONTENT_PROVIDER, prefs.mode.value)
                .addStatement("sharedEditor = sharedPrefs.edit()")
                .build()
    }

    private fun addEditorProperty(): PropertySpec {
        return PropertySpec
                .builder("sharedEditor", CLASS_NAME_SHARED_PREFERENCES_EDITOR)
                .addModifiers(KModifier.PRIVATE)
                .build()
    }
}