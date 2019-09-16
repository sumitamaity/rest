package rest.requestPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqHyderabad {

@SerializedName("City")
@Expose
private String city;
@SerializedName("Temperature")
@Expose
private String temperature;
@SerializedName("Humidity")
@Expose
private String humidity;
@SerializedName("WeatherDescription")
@Expose
private String weatherDescription;
@SerializedName("WindSpeed")
@Expose
private String windSpeed;
@SerializedName("WindDirectionDegree")
@Expose
private String windDirectionDegree;

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getTemperature() {
return temperature;
}

public void setTemperature(String temperature) {
this.temperature = temperature;
}

public String getHumidity() {
return humidity;
}

public void setHumidity(String humidity) {
this.humidity = humidity;
}

public String getWeatherDescription() {
return weatherDescription;
}

public void setWeatherDescription(String weatherDescription) {
this.weatherDescription = weatherDescription;
}

public String getWindSpeed() {
return windSpeed;
}

public void setWindSpeed(String windSpeed) {
this.windSpeed = windSpeed;
}

public String getWindDirectionDegree() {
return windDirectionDegree;
}

public void setWindDirectionDegree(String windDirectionDegree) {
this.windDirectionDegree = windDirectionDegree;
}

}