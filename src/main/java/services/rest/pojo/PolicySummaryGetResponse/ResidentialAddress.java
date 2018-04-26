
package services.rest.pojo.PolicySummaryGetResponse;

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
    "addressLine1",
    "addressLine2",
    "city",
    "stateProvCd",
    "postalCode"
})
public class ResidentialAddress {

    @JsonProperty("addressLine1")
    private String addressLine1;
    @JsonProperty("addressLine2")
    private String addressLine2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("stateProvCd")
    private String stateProvCd;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The addressLine1
     */
    @JsonProperty("addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * 
     * @param addressLine1
     *     The addressLine1
     */
    @JsonProperty("addressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * 
     * @return
     *     The addressLine2
     */
    @JsonProperty("addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * 
     * @param addressLine2
     *     The addressLine2
     */
    @JsonProperty("addressLine2")
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * 
     * @return
     *     The city
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The stateProvCd
     */
    @JsonProperty("stateProvCd")
    public String getStateProvCd() {
        return stateProvCd;
    }

    /**
     * 
     * @param stateProvCd
     *     The stateProvCd
     */
    @JsonProperty("stateProvCd")
    public void setStateProvCd(String stateProvCd) {
        this.stateProvCd = stateProvCd;
    }

    /**
     * 
     * @return
     *     The postalCode
     */
    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 
     * @param postalCode
     *     The postalCode
     */
    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
        return new HashCodeBuilder().append(addressLine1).append(addressLine2).append(city).append(stateProvCd).append(postalCode).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ResidentialAddress) == false) {
            return false;
        }
        ResidentialAddress rhs = ((ResidentialAddress) other);
        return new EqualsBuilder().append(addressLine1, rhs.addressLine1).append(addressLine2, rhs.addressLine2).append(city, rhs.city).append(stateProvCd, rhs.stateProvCd).append(postalCode, rhs.postalCode).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
