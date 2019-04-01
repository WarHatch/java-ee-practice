package interceptors;

import lombok.NoArgsConstructor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@NoArgsConstructor
@Logged
@Interceptor
public class LoggedImpl {
    @AroundInvoke
    public Object logEvent(InvocationContext context) throws Exception {
        //Pre method fire


        //Fire method
        Object result = context.proceed();

        //Post method fire
        System.out.println(context.getTarget().toString() + " CALLED " + context.getMethod().toString());

        return result;
    }

}
