
package services.rest.pojo.ViewAssignmentsGetResponse;

import java.util.HashMap;
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
    "vehicleDisplayValue",
    "vehicleOid",
    "driverDisplayValue",
    "driverOid",
    "relationshipType"
})
public class Assignment {

    @JsonProperty("vehicleDisplayValue")
    private String vehicleDisplayValue;
    @JsonProperty("vehicleOid")
    private String vehicleOid;
    @JsonProperty("driverDisplayValue")
    private String driverDisplayValue;
    @JsonProperty("driverOid")
    private String driverOid;
    @JsonProperty("relationshipType")
    private String relationshipType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The vehicleDisplayValue
     */
    @JsonProperty("vehicleDisplayValue")
    public String getVehicleDisplayValue() {
        return vehicleDisplayValue;
    }

    /**
     * 
     * @param vehicleDisplayValue
     *     The vehicleDisplayValue
     */
    @JsonProperty("vehicleDisplayValue")
    public void setVehicleDisplayValue(String vehicleDisplayValue) {
        this.vehicleDisplayValue = vehicleDisplayValue;
    }

    /**
     * 
     * @return
     *     The vehicleOid
     */
    @JsonProperty("vehicleOid")
    public String getVehicleOid() {
        return vehicleOid;
    }

    /**
     * 
     * @param vehicleOid
     *     The vehicleOid
     */
    @JsonProperty("vehicleOid")
    public void setVehicleOid(String vehicleOid) {
        this.vehicleOid = vehicleOid;
    }

    /**
     * 
     * @return
     *     The driverDisplayValue
     */
    @JsonProperty("driverDisplayValue")
    public String getDriverDisplayValue() {
        return driverDisplayValue;
    }

    /**
     * 
     * @param driverDisplayValue
     *     The driverDisplayValue
     */
    @JsonProperty("driverDisplayValue")
    public void setDriverDisplayValue(String driverDisplayValue) {
        this.driverDisplayValue = driverDisplayValue;
    }

    /**
     * 
     * @return
     *     The driverOid
     */
    @JsonProperty("driverOid")
    public String getDriverOid() {
        return driverOid;
    }

    /**
     * 
     * @param driverOid
     *     The driverOid
     */
    @JsonProperty("driverOid")
    public void setDriverOid(String driverOid) {
        this.driverOid = driverOid;
    }

    /**
     * 
     * @return
     *     The relationshipType
     */
    @JsonProperty("relationshipType")
    public String getRelationshipType() {
        return relationshipType;
    }

    /**
     * 
     * @param relationshipType
     *     The relationshipType
     */
    @JsonProperty("relationshipType")
    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
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
        return new HashCodeBuilder().append(vehicleDisplayValue).append(vehicleOid).append(driverDisplayValue).append(driverOid).append(relationshipType).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Assignment) == false) {
            return false;
        }
        Assignment rhs = ((Assignment) other);
        return new EqualsBuilder().append(vehicleDisplayValue, rhs.vehicleDisplayValue).append(vehicleOid, rhs.vehicleOid).append(driverDisplayValue, rhs.driverDisplayValue).append(driverOid, rhs.driverOid).append(relationshipType, rhs.relationshipType).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
