
package services.rest.pojo.ViewDriversGetResponse;

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
    "Drivers"
})
public class ViewDriversGetResponse {

    @JsonProperty("Drivers")
    private List<Driver> Drivers = new ArrayList<Driver>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Drivers
     */
    @JsonProperty("Drivers")
    public List<Driver> getDrivers() {
        return Drivers;
    }

    /**
     * 
     * @param Drivers
     *     The Drivers
     */
    @JsonProperty("Drivers")
    public void setDrivers(List<Driver> Drivers) {
        this.Drivers = Drivers;
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
        return new HashCodeBuilder().append(Drivers).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ViewDriversGetResponse) == false) {
            return false;
        }
        ViewDriversGetResponse rhs = ((ViewDriversGetResponse) other);
        return new EqualsBuilder().append(Drivers, rhs.Drivers).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
