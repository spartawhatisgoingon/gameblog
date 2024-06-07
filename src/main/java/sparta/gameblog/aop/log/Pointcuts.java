package sparta.gameblog.aop.log;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* sparta.gameblog.web.controller.*.*(..))")
    public void methodsFromController() {}

    @Pointcut("execution(* sparta.gameblog.service.*.*(..))")
    public void methodsFromService() {}

    @Pointcut("execution(* sparta.gameblog.smtp.service.*.*(..))")
    public void methodsFromSmtpService() {}
}
