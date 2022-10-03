package us.serif.jb.tsgen

import us.serif.jb.tsgen.config.TimestampGeneratorSettings
import java.time.Instant
import java.time.temporal.ChronoUnit

object TimestampGenerator {

//    Instant now = Instant.now()
//    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXXXX").withLocale(Locale.US).withZone(ZoneId.systemDefault())
//    fmt.format(now)

    fun generateTimestamp(
        instant: Instant = Instant.now(),
        settings: TimestampGeneratorSettings = TimestampGeneratorSettings.instance,
        truncateTo: ChronoUnit = ChronoUnit.NANOS
    ): String = settings.formatter().format(instant.truncatedTo(truncateTo))
//    fun generateTimestamp(
//        instant: Instant = Instant.now(),
//        settings: TimestampGeneratorSettings = TimestampGeneratorSettings.instance,
//        truncateTo: TimestampGeneratorSettings.truncateTo
//    ): String = settings.formatter().format(instant)
}
