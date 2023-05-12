package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCode {
@JsonProperty("post code")
    private String post_code;
    private List<Places> places;
    private String country;
    @JsonProperty("country abbreviation")
    private String country_abbreviation;
}
