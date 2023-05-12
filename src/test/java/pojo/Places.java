package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Places {
    @JsonProperty("place name")
    private String place_name;
    @JsonProperty("post code")
    private String post_code;

}
