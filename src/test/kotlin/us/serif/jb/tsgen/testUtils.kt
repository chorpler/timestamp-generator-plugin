package us.serif.jb.tsgen

import us.serif.jb.tsgen.config.TimestampFormatMap
import us.serif.jb.tsgen.config.TimestampFormatTitle
import us.serif.jb.tsgen.config.TimestampGeneratorSettings
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.test.TestCaseOrder

fun settings(
    title: TimestampFormatTitle = TimestampFormatTitle.ISO_8601
) = TimestampGeneratorSettings().apply { this.format = TimestampFormatMap.getFormat(title) }

abstract class BaseSpec(body: ShouldSpec.() -> Unit = {}) : ShouldSpec(body) {
    override fun testCaseOrder() = TestCaseOrder.Random
    override fun isolationMode() = IsolationMode.InstancePerTest
}
