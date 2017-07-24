package ua.goit.offline.rs.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by User on 17.07.2017.
 */
//@javax.ws.rs.
public class WeatherService {
@GET
@Path("/weather")
public String getWeather(@QueryParam("city") String city){
    return "sunny";
}


}
