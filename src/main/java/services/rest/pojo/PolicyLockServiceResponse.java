
package services.rest.pojo;

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
    "policyNumber",
    "registeredOwner"
})
public class PolicyLockServiceResponse {

    @JsonProperty("policyNumber")
    private String policyNumber;
   
    @JsonProperty("status")
    private String status;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The policyNumber
     */
    @JsonProperty("policyNumber")
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * 
     * @param policyNumber
     *     The policyNumber
     */
    @JsonProperty("policyNumber")
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    

    /**
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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
        return new HashCodeBuilder().append(policyNumber).append(status).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PolicyLockServiceResponse) == false) {
            return false;
        }
        PolicyLockServiceResponse rhs = ((PolicyLockServiceResponse) other);
        return new EqualsBuilder().append(policyNumber, rhs.policyNumber).append(status, rhs.status).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
