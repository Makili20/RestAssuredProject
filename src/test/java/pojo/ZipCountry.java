package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCountry {
    @JsonProperty("country abbreviation")
    private String country_abbreviation;
    private String country;
   List<Places> places;
}
