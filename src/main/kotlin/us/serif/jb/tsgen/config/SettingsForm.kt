package us.serif.jb.tsgen.config

import com.intellij.openapi.ui.ComboBox
import us.serif.jb.tsgen.TimestampGenerator
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.swing.*

class SettingsForm {

    private val previewTimestamp = Instant.now()
    private val settings = TimestampGeneratorSettings.instance

    private var panel: JPanel? = null
    private var previewLabel: JLabel? = null
    private var formatComboBox: JComboBox<TimestampFormatTitle>? = null
    private var customFormatField: JTextField? = null
    private var truncateToSecondsCheckBox: JCheckBox? = null

    init {
        loadSettings()
    }

    fun loadSettings() {
        formatComboBox?.selectedItem = settings.format.title
        customFormatField?.text = settings.customFormat()
        truncateToSecondsCheckBox?.isSelected = settings.format.truncateToSeconds

        sequenceOf(
            formatComboBox
        ).filterNotNull().forEach {
            it.addActionListener {
                updatePreviewLabel()
            }
        }

        updatePreviewLabel()
    }

    private fun updatePreviewLabel() {
        val previewSettings = TimestampGeneratorSettings()
        applyConfigFormToSettings(previewSettings)
        previewLabel?.text =
            TimestampGenerator.generateTimestamp(instant = previewTimestamp, settings = previewSettings, truncateTo = ChronoUnit.NANOS)
    }

    fun applyConfigFormToSettings(settings: TimestampGeneratorSettings) {
        settings.format = getSelectedFormat()
    }

    fun component(): JComponent? = panel

    private fun createUIComponents() {
        formatComboBox = ComboBox(DefaultComboBoxModel(TimestampFormatTitle.values()))
    }

    private fun getSelectedFormat(): TimestampFormat =
        TimestampFormatMap.getFormat(
            formatComboBox?.selectedItem as? TimestampFormatTitle ?: TimestampFormatTitle.ISO_8601
        )

    val isModified: Boolean
        get() = getSelectedFormat() != settings.format
}
