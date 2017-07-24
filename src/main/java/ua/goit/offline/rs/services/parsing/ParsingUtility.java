package ua.goit.offline.rs.services.parsing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 17.07.2017.
 */
public class ParsingUtility {
    public static List<Invocation> parse(Class<?> clazz) {
        List<Invocation> invocations = new ArrayList<>(); //много методов - лист
        for (Method method : clazz.getMethods()) {
            String name = method.getName();
            if (method.isAnnotationPresent(GET.class)) {
                String url = method.getName();//по умолчанию url - имя метода
                if (method.isAnnotationPresent(Path.class)) {
                    Path path = method.getAnnotation(Path.class);// если аннотация Path существует, то переопределяем url как method.getAnnotation(Path.class)
                    url = path.value();
                }
                Map<Integer, String> params = new HashMap();
                for (int i = 0; i < method.getParameterCount(); i++) {
                    TypeVariable<?> variable = method.getTypeParameters()[i];
                    if (variable.isAnnotationPresent(QueryParam.class)) { //проверяем есть ли QueryParam
                        //дефолтное дописать QueryParam
                        QueryParam param = variable.getAnnotation(QueryParam.class);
                        String paramName = param.value();
                        params.put(i, paramName);
                    }
                }
//                method.invoke()//1 ый параметр - объект на который вызываем, 2- переменный, с которыми вызываем метод; если null передать 1 параметром, то вызываем статические методы
                invocations.add(new Invocation(url, method, params, method.getParameterCount()));
            }
        }
        return invocations;
    }

    public static class Invocation{
       private String url;
       private Method method;
       private Map<Integer, String> params;
       private int paramCount;


        public Invocation(String url, Method method, Map<Integer, String> params, int paramCount) {
            this.url = url;
            this.method = method;
            this.params = params;
            this.paramCount = paramCount;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Map<Integer, String> getParams() {
            return params;
        }

        public void setParams(Map<Integer, String> params) {
            this.params = params;
        }

        public int getParamCount() {
            return paramCount;
        }

        public void setParamCount(int paramCount) {
            this.paramCount = paramCount;
        }
    }
}
