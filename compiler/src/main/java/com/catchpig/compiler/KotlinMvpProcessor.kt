package com.catchpig.compiler

import com.catchpig.annotation.*
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

@AutoService(Processor::class)
class KotlinMvpProcessor: AbstractProcessor() {
    companion object{
        private val CLASS_NAME_TITLE_PARAM = ClassName("com.catchpig.mvp.entity","TitleParam")
        private val CLASS_NAME_STATUS_BAR_PARAM = ClassName("com.catchpig.mvp.entity","StatusBarParam")
        private val CLASS_NAME_MVP_COMPILER = ClassName("com.catchpig.mvp.apt","MvpCompiler")
        private val CLASS_NAME_BASE_ACTIVITY = ClassName("com.catchpig.mvp.base.activity","BaseActivity")
        private val TYPE_VIEW_STUB = Class.forName("android.view.ViewStub")
        private val TYPE_TITLE_BAR_CONTROLLER = Class.forName("com.catchpig.mvp.controller.TitleBarController")
        private val TYPE_STATUS_BAR_CONTROLLER = Class.forName("com.catchpig.mvp.controller.StatusBarController")
        private val TYPE_TEXT_VIEW = Class.forName("android.widget.TextView")
        private val TYPE_IMAGE_VIEW = Class.forName("android.widget.ImageView")
        private val TYPE_VIEW = Class.forName("android.view.View")

    }
    private lateinit var elementUtils:Elements
    private lateinit var messager: Messager
    private lateinit var filer: Filer

    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        elementUtils = processingEnv.elementUtils
        messager = processingEnv.messager
        filer = processingEnv.filer
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        var set = HashSet<String>()
        set.add(Title::class.java.canonicalName)
        set.add(StatusBar::class.java.canonicalName)
        set.add(OnClickFirstDrawable::class.java.canonicalName)
        set.add(OnClickFirstText::class.java.canonicalName)
        set.add(OnClickSecondDrawable::class.java.canonicalName)
        set.add(OnClickSecondText::class.java.canonicalName)
        return set
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }
    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val allElements = roundEnv.rootElements
        val elements = allElements.filter {
            if (it is TypeElement) {
                if (it.getAnnotation(Title::class.java) != null
                        || it.getAnnotation(StatusBar::class.java)!= null) {
                    return@filter true
                }
            }
            return@filter false
        }
        elements.forEach {
            if (it is TypeElement) {
                val title = it.getAnnotation(Title::class.java)
                val statusBar = it.getAnnotation(StatusBar::class.java)

                val className = it.simpleName.toString()
                val fullPackageName = elementUtils.getPackageOf(it).qualifiedName.toString()
                val typeSpec = TypeSpec
                        .classBuilder(className+"_MvpCompiler")
                        .addModifiers(KModifier.FINAL,KModifier.PUBLIC)
                        .addSuperinterface(CLASS_NAME_MVP_COMPILER)
                        .addProperty(initTitleProperty(title))
                        .addProperty(initStatusBarProperty(statusBar))
                        .addFunction(injectFun(className))
                        .addFunction(initTitleMenuOnClick(it,title,className,fullPackageName))
                        .build()
                FileSpec
                        .builder(fullPackageName,typeSpec.name!!)
                        .addType(typeSpec)
                        .addImport("com.catchpig.kotlin_mvp","R")
                        .build()
                        .writeTo(filer)
            }else{
                error("Title注解必须在类上面")
                return false
            }
        }
        return true
    }

    private fun initTitleMenuOnClick(element: TypeElement,title: Title?,className:String,packageName:String):FunSpec{
        val elements = elementUtils.getAllMembers(element)
        var builder = FunSpec
                .builder("initTitleMenuOnClick")
                .addParameter("activity",ClassName(packageName,className))
                .addModifiers(KModifier.PRIVATE)
        title?.let {

            //第一个文字按钮
            elements.find {
                return@find it.getAnnotation(OnClickFirstText::class.java) != null
            }?.run {
                val onClickFirstText = getAnnotation(OnClickFirstText::class.java)
                if (onClickFirstText!=null) {
                    builder = builder
                            .addStatement("//第一个文字按钮")
                            .addStatement("var rightFirstText = activity.findViewById<%T>(R.id.rightFirstText)", TYPE_TEXT_VIEW)
                            .addStatement("rightFirstText.setText(%L)",onClickFirstText.value)
                            .addStatement("rightFirstText.visibility = %T.VISIBLE", TYPE_VIEW)
                            .addStatement("rightFirstText.setOnClickListener {")
                            .addStatement("  activity.${simpleName}(it)")
                            .addStatement("}")
                }
            }
            //第一个图片按钮
            elements.find {
                return@find it.getAnnotation(OnClickFirstDrawable::class.java) != null
            }?.run {
                val onClickFirstDrawable = getAnnotation(OnClickFirstDrawable::class.java)
                if (onClickFirstDrawable!=null) {
                    builder = builder
                            .addStatement("//第一个图片按钮")
                            .addStatement("var rightFirstDrawable = activity.findViewById<%T>(R.id.rightFirstDrawable)", TYPE_IMAGE_VIEW)
                            .addStatement("rightFirstDrawable.setImageResource(%L)", onClickFirstDrawable.value)
                            .addStatement("rightFirstDrawable.visibility = %T.VISIBLE", TYPE_VIEW)
                            .addStatement("rightFirstDrawable.setOnClickListener {")
                            .addStatement("  activity.${simpleName}(it)")
                            .addStatement("}")
                }
            }
            //第二个文字按钮
            elements.find {
                return@find it.getAnnotation(OnClickSecondText::class.java) != null
            }?.run {
                val onClickSecondText = getAnnotation(OnClickSecondText::class.java)
                if (onClickSecondText!=null) {
                    builder = builder
                            .addStatement("//第二个文字按钮")
                            .addStatement("var rightSecondText = activity.findViewById<%T>(R.id.rightSecondText)", TYPE_TEXT_VIEW)
                            .addStatement("rightSecondText.setText(%L)",onClickSecondText.value)
                            .addStatement("rightSecondText.visibility = %T.VISIBLE", TYPE_VIEW)
                            .addStatement("rightSecondText.setOnClickListener {")
                            .addStatement("  activity.${simpleName}(it)")
                            .addStatement("}")
                }
            }
            //第二个图片按钮
            elements.find {
                return@find it.getAnnotation(OnClickSecondDrawable::class.java) != null
            }?.run {
                val onClickSecondDrawable = getAnnotation(OnClickSecondDrawable::class.java)
                if (onClickSecondDrawable!=null) {
                    builder = builder
                            .addStatement("//第二个图片按钮")
                            .addStatement("var rightSecondDrawable = activity.findViewById<%T>(R.id.rightSecondDrawable)", TYPE_IMAGE_VIEW)
                            .addStatement("rightSecondDrawable.setImageResource(%L)", onClickSecondDrawable.value)
                            .addStatement("rightSecondDrawable.visibility = %T.VISIBLE", TYPE_VIEW)
                            .addStatement("rightSecondDrawable.setOnClickListener {")
                            .addStatement("  activity.${simpleName}(it)")
                            .addStatement("}")
                }
            }

        }
        return builder.build()
    }
    private fun injectFun(className:String):FunSpec{
        return FunSpec
                .builder("inject")
                .addModifiers(KModifier.PUBLIC,KModifier.OVERRIDE)
                .addParameter("activity",CLASS_NAME_BASE_ACTIVITY)
                .addStatement("//加载标题栏")
                .addStatement("title?.let{")
                .addStatement("  val titleBarViewStub = activity.findViewById<%T>(R.id.title_bar_view_stub)", TYPE_VIEW_STUB)
                .addStatement("  titleBarViewStub.setOnInflateListener { _, _ ->")
                .addStatement("    val titleBarController = %T(activity,it)",TYPE_TITLE_BAR_CONTROLLER)
                .addStatement("    titleBarController.initTitleBar()")
                .addStatement("    initTitleMenuOnClick(activity as $className)")
                .addStatement("  }")
                .addStatement("  titleBarViewStub.inflate()")
                .addStatement("}")
                .addStatement("//加载状态栏")
                .addStatement("val statusBarController = %T(activity,title,statusBar)", TYPE_STATUS_BAR_CONTROLLER)
                .addStatement("statusBarController.checkStatusBar()")
                .build()
    }
    /**
     * 添加StatusBar属性
     */
    private fun initStatusBarProperty(statusBar: StatusBar?):PropertySpec{
        var builder = PropertySpec
                .builder("statusBar", CLASS_NAME_STATUS_BAR_PARAM.copy(nullable = true))
                .addModifiers(KModifier.PRIVATE)
        return if (null == statusBar) {
            warning("StatusBar注解没有使用")
            builder
                    .initializer("null")
                    .build()
        }else {
            builder
                    .initializer("StatusBarParam(${statusBar.hide},${statusBar.enabled},${statusBar.transparent})")
                    .build()
        }
    }
    /**
     * 添加title属性
     */
    private fun initTitleProperty(title: Title?):PropertySpec{
        var builder = PropertySpec
                .builder("title", CLASS_NAME_TITLE_PARAM.copy(nullable = true))
                .addModifiers(KModifier.PRIVATE)
        return if (null == title) {
            warning("Title注解没有使用")
            builder
                    .initializer("null")
                    .build()
        }else {
            builder
                    .initializer("TitleParam(%L,%L,%L,%L)",title.value,title.backgroundColor,title.textColor,title.backIcon)
                    .build()
        }
    }


    private fun warning(msg:String){
        messager.printMessage(Diagnostic.Kind.WARNING,msg)
    }

    private fun error(msg:String){
        messager.printMessage(Diagnostic.Kind.ERROR,msg)
    }
}
