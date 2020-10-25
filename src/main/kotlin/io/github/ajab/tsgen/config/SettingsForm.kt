package io.github.ajab.tsgen.config

import com.intellij.openapi.ui.ComboBox
import io.github.ajab.tsgen.TimestampGenerator
import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class SettingsForm {

    private val settings = TimestampGeneratorSettings.instance

    private var panel: JPanel? = null
    private var previewLabel: JLabel? = null
    private var format: JComboBox<TimestampFormat>? = null

    init {
        loadSettings()
    }

    val isModified: Boolean
        get() = getSelectedFormat() != settings.format

    fun loadSettings() {
        format?.selectedItem = settings.format

        sequenceOf(
            format
        ).filterNotNull().forEach {
            it.addItemListener {
                updatePreviewLabel()
            }
        }

        updatePreviewLabel()
    }

    fun applyToConfigForm(settings: TimestampGeneratorSettings) {
        settings.format = getSelectedFormat()
    }

    fun component(): JComponent? = panel

    private fun getSelectedFormat(): TimestampFormat =
        format?.selectedItem as? TimestampFormat ?: TimestampFormat.ISO_8601

    private fun updatePreviewLabel() {
        val previewSettings = TimestampGeneratorSettings()
        applyToConfigForm(previewSettings)

        previewLabel?.text = TimestampGenerator.generateTimestamp(settings = settings)
    }

    private fun createUIComponents() {
        format = ComboBox(DefaultComboBoxModel<TimestampFormat>(TimestampFormat.values()))
    }
}
