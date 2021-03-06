
package services.rest.pojo.validateVehiclesList.validateVehiclesResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "Vehicles"
})
public class ValidateVehiclesResponse {

    @JsonProperty("Vehicles")
    private List<Vehicle> Vehicles = new ArrayList<Vehicle>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Vehicles
     */
    @JsonProperty("Vehicles")
    public List<Vehicle> getVehicles() {
        return Vehicles;
    }

    /**
     * 
     * @param Vehicles
     *     The Vehicles
     */
    @JsonProperty("Vehicles")
    public void setVehicles(List<Vehicle> Vehicles) {
        this.Vehicles = Vehicles;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Vehicles).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ValidateVehiclesResponse) == false) {
            return false;
        }
        ValidateVehiclesResponse rhs = ((ValidateVehiclesResponse) other);
        return new EqualsBuilder().append(Vehicles, rhs.Vehicles).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
