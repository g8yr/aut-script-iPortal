
package services.rest.pojo.ViewCoveragesGetResponse;

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
    "policyCoverages",
    "vehicleLevelCoverages"
})
public class ViewCoveragesGetResponse {

    @JsonProperty("policyCoverages")
    private List<PolicyCoverage> policyCoverages = new ArrayList<PolicyCoverage>();
    @JsonProperty("vehicleLevelCoverages")
    private List<VehicleLevelCoverage> vehicleLevelCoverages = new ArrayList<VehicleLevelCoverage>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The policyCoverages
     */
    @JsonProperty("policyCoverages")
    public List<PolicyCoverage> getPolicyCoverages() {
        return policyCoverages;
    }

    /**
     * 
     * @param policyCoverages
     *     The policyCoverages
     */
    @JsonProperty("policyCoverages")
    public void setPolicyCoverages(List<PolicyCoverage> policyCoverages) {
        this.policyCoverages = policyCoverages;
    }

    /**
     * 
     * @return
     *     The vehicleLevelCoverages
     */
    @JsonProperty("vehicleLevelCoverages")
    public List<VehicleLevelCoverage> getVehicleLevelCoverages() {
        return vehicleLevelCoverages;
    }

    /**
     * 
     * @param vehicleLevelCoverages
     *     The vehicleLevelCoverages
     */
    @JsonProperty("vehicleLevelCoverages")
    public void setVehicleLevelCoverages(List<VehicleLevelCoverage> vehicleLevelCoverages) {
        this.vehicleLevelCoverages = vehicleLevelCoverages;
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
        return new HashCodeBuilder().append(policyCoverages).append(vehicleLevelCoverages).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ViewCoveragesGetResponse) == false) {
            return false;
        }
        ViewCoveragesGetResponse rhs = ((ViewCoveragesGetResponse) other);
        return new EqualsBuilder().append(policyCoverages, rhs.policyCoverages).append(vehicleLevelCoverages, rhs.vehicleLevelCoverages).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
