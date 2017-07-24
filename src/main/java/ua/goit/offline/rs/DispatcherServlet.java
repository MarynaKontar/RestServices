package ua.goit.offline.rs;

import com.sun.net.httpserver.HttpServer;
import ua.goit.offline.rs.services.parsing.ParsingUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by User on 17.07.2017.
 */

@WebServlet(urlPatterns = "/services", loadOnStartup = 1)//загружается при старте
public class DispatcherServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//    req.getServletPath()
//    resp.getOutputStream().println(req.getContextPath());
//    resp.getOutputStream().println(req.getRequestURL().toString());
//    resp.getOutputStream().println(req.getRequestURI());
//    resp.getOutputStream().println(req.getServletContext().toString());
//    resp.getOutputStream().flush();

    String path = req.getContextPath();
    String url = path.replaceAll("/services/","");///services поменяли на пустую строку
        ParsingUtility.Invocation invocation = ServicesStore.getInstance().getService(url);

        Object[] params = new Object[invocation.getParamCount()]; //массив параметров
        for(Map.Entry<Integer, String> reqParam: invocation.getParams().entrySet()) { // прохожусь по всем параметрам и ищу на соответствие

        String param = req.getParameter(reqParam.getValue());
        params[reqParam.getKey()] = param;
        }

        Method method = invocation.getMethod();
        //TODO : method invoke()
        //invoke надо на чем то
    }

//    Object getService (Class<?> clazz){
////        throw new UnsupportedOperationException();
//
//    }

}