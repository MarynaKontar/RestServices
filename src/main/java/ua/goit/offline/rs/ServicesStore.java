package ua.goit.offline.rs;

import ua.goit.offline.rs.services.parsing.ParsingUtility;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by User on 17.07.2017.
 */
public class ServicesStore {

    private static final ServicesStore instance = new ServicesStore();


    Map<String, ParsingUtility.Invocation> getInvocations;

     private ServicesStore(){
        this.getInvocations = new ConcurrentHashMap<>(); // потобезопасная HashMap
         //зарегестрировать сервис
    }

    public void addService(Class<?> clazz){
        for(ParsingUtility.Invocation invocation : ParsingUtility.parse(clazz)){
            getInvocations.put(invocation.getUrl(), invocation);
        }
    }

    public ParsingUtility.Invocation getService(String url){
        return getInvocations.get(url);
    }

    public static ServicesStore getInstance() {
        return instance;
    }
}
