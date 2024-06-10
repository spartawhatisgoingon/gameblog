package sparta.gameblog.aop.log;

public record TraceStatus(TraceId traceId, Long startTimeMs, String message) {
}
