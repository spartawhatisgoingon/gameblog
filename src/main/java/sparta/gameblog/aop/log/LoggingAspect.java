package sparta.gameblog.aop.log;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final LogTrace logTrace;

    @Around("sparta.gameblog.aop.log.Pointcuts.methodsFromService()|| sparta.gameblog.aop.log.Pointcuts.methodsFromController()")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature()
                    .toShortString()
                    .replace("..", formatArguments(joinPoint.getArgs()));
            status = logTrace.begin(message);

            Object result = joinPoint.proceed();

            logTrace.end(status, result == null ? "" : result.toString());
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    private String formatArguments(Object[] args) {
        return Arrays.stream(args)
                .map(arg -> {
                    if (arg == null) {
                        return "null";
                    }
                    return arg.toString();
                })
                .collect(Collectors.joining(", "));
    }
}
