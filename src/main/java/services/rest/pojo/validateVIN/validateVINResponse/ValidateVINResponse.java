
package services.rest.pojo.validateVIN.validateVINResponse;

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
    "vehicles",
    "validationMessage"
})
public class ValidateVINResponse {

    @JsonProperty("vehicles")
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();
    @JsonProperty("validationMessage")
    private String validationMessage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The vehicles
     */
    @JsonProperty("vehicles")
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * 
     * @param vehicles
     *     The vehicles
     */
    @JsonProperty("vehicles")
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * 
     * @return
     *     The validationMessage
     */
    @JsonProperty("validationMessage")
    public String getValidationMessage() {
        return validationMessage;
    }

    /**
     * 
     * @param validationMessage
     *     The validationMessage
     */
    @JsonProperty("validationMessage")
    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
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
        return new HashCodeBuilder().append(vehicles).append(validationMessage).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ValidateVINResponse) == false) {
            return false;
        }
        ValidateVINResponse rhs = ((ValidateVINResponse) other);
        return new EqualsBuilder().append(vehicles, rhs.vehicles).append(validationMessage, rhs.validationMessage).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
