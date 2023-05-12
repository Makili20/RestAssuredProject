package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class Region2 {
@JsonProperty("region_id")
    private int regionId;
@JsonProperty("region_name")
    private String regionName;
    private List<Link> links;

}
