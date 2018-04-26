
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
    "premiumType",
    "premiumCode",
    "actualAmt"
})
public class RateEndorsementPostResponse {

    @JsonProperty("premiumType")
    private String premiumType;
    @JsonProperty("premiumCode")
    private String premiumCode;
    @JsonProperty("actualAmt")
    private String actualAmt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The premiumType
     */
    @JsonProperty("premiumType")
    public String getPremiumType() {
        return premiumType;
    }

    /**
     * 
     * @param premiumType
     *     The premiumType
     */
    @JsonProperty("premiumType")
    public void setPremiumType(String premiumType) {
        this.premiumType = premiumType;
    }

    /**
     * 
     * @return
     *     The premiumCode
     */
    @JsonProperty("premiumCode")
    public String getPremiumCode() {
        return premiumCode;
    }

    /**
     * 
     * @param premiumCode
     *     The premiumCode
     */
    @JsonProperty("premiumCode")
    public void setPremiumCode(String premiumCode) {
        this.premiumCode = premiumCode;
    }

    /**
     * 
     * @return
     *     The actualAmt
     */
    @JsonProperty("actualAmt")
    public String getActualAmt() {
        return actualAmt;
    }

    /**
     * 
     * @param actualAmt
     *     The actualAmt
     */
    @JsonProperty("actualAmt")
    public void setActualAmt(String actualAmt) {
        this.actualAmt = actualAmt;
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
        return new HashCodeBuilder().append(premiumType).append(premiumCode).append(actualAmt).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RateEndorsementPostResponse) == false) {
            return false;
        }
        RateEndorsementPostResponse rhs = ((RateEndorsementPostResponse) other);
        return new EqualsBuilder().append(premiumType, rhs.premiumType).append(premiumCode, rhs.premiumCode).append(actualAmt, rhs.actualAmt).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
