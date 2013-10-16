package com.acmebank.util;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Audited
public class AuditInterceptor {

    private static final Logger logger
            = Logger.getLogger(AuditInterceptor.class.getName());

    @AroundInvoke
    public Object audit(InvocationContext context) throws Exception {
        logger.log(Level.INFO, "Executing: {0} with args: {1}",
                new Object[]{context.getMethod().getName(),
                    Arrays.toString(context.getParameters())});

        return context.proceed();
    }
}
