package us.serif.jb.tsgen

import us.serif.jb.tsgen.config.TimestampGeneratorSettings
import java.time.Instant

object TimestampGenerator {

//    Instant now = Instant.now()
//    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXXXX").withLocale(Locale.US).withZone(ZoneId.systemDefault())
//    fmt.format(now)

    fun generateTimestamp(
        instant: Instant = Instant.now(),
        settings: TimestampGeneratorSettings = TimestampGeneratorSettings.instance
    ): String = settings.formatter().format(instant)
}
