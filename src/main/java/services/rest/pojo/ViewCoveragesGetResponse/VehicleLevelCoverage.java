
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
    "oid",
    "coverages"
})
public class VehicleLevelCoverage {

    @JsonProperty("oid")
    private String oid;
    @JsonProperty("coverages")
    private List<Coverage> coverages = new ArrayList<Coverage>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The oid
     */
    @JsonProperty("oid")
    public String getOid() {
        return oid;
    }

    /**
     * 
     * @param oid
     *     The oid
     */
    @JsonProperty("oid")
    public void setOid(String oid) {
        this.oid = oid;
    }

    /**
     * 
     * @return
     *     The coverages
     */
    @JsonProperty("coverages")
    public List<Coverage> getCoverages() {
        return coverages;
    }

    /**
     * 
     * @param coverages
     *     The coverages
     */
    @JsonProperty("coverages")
    public void setCoverages(List<Coverage> coverages) {
        this.coverages = coverages;
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
        return new HashCodeBuilder().append(oid).append(coverages).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VehicleLevelCoverage) == false) {
            return false;
        }
        VehicleLevelCoverage rhs = ((VehicleLevelCoverage) other);
        return new EqualsBuilder().append(oid, rhs.oid).append(coverages, rhs.coverages).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
