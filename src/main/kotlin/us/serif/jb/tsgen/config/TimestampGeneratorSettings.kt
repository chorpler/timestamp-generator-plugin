package us.serif.jb.tsgen.config

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

interface TimestampFormatSettings {
    fun title(): String
    fun customFormat(): String
    fun formatter(): DateTimeFormatter
    fun truncateToSeconds(): Boolean
}

@State(name = "TimestampSettings", storages = [(Storage("timestamp_generator.xml"))])
class TimestampGeneratorSettings : PersistentStateComponent<TimestampGeneratorSettings>, TimestampFormatSettings {

    companion object {
        val instance: TimestampGeneratorSettings
//            get() = ServiceManager.getService(TimestampGeneratorSettings::class.java)
            get() = ApplicationManager.getApplication().getService(TimestampGeneratorSettings::class.java)
//        val truncateTo: ChronoUnit
//            get() = TimeS
    }

    // default value
//    var format: TimestampFormat = TimestampFormatMap.ISO_8601
    var format: TimestampFormat = TimestampFormatMap.ISO_OFFSET_DATE_TIME
//    var truncateTo: ChronoUnit = TimestampFormatMap.ISO_OFFSET_DATE_TIME

    override fun getState(): TimestampGeneratorSettings = this

    override fun loadState(state: TimestampGeneratorSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    override fun title(): String = format.title.title

    override fun customFormat(): String = format.customFormat

    override fun formatter(): DateTimeFormatter = format.format

    override fun truncateToSeconds(): Boolean = format.truncateToSeconds
}
