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
    "endorsementDate",
    "endorsementReason",
    "endorsementReasonOther"
})
public class StartEndorsementRequest {

    @JsonProperty("endorsementDate")
    private String endorsementDate;
    @JsonProperty("endorsementReason")
    private String endorsementReason;
    @JsonProperty("endorsementReasonOther")
    private String endorsementReasonOther;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The endorsementDate
     */
    @JsonProperty("endorsementDate")
    public String getEndorsementDate() {
        return endorsementDate;
    }

    /**
     * 
     * @param endorsementDate
     *     The endorsementDate
     */
    @JsonProperty("endorsementDate")
    public void setEndorsementDate(String endorsementDate) {
        this.endorsementDate = endorsementDate;
    }

    /**
     * 
     * @return
     *     The endorsementReason
     */
    @JsonProperty("endorsementReason")
    public String getEndorsementReason() {
        return endorsementReason;
    }

    /**
     * 
     * @param endorsementReason
     *     The endorsementReason
     */
    @JsonProperty("endorsementReason")
    public void setEndorsementReason(String endorsementReason) {
        this.endorsementReason = endorsementReason;
    }

    /**
     * 
     * @return
     *     The endorsementReasonOther
     */
    @JsonProperty("endorsementReasonOther")
    public String getEndorsementReasonOther() {
        return endorsementReasonOther;
    }

    /**
     * 
     * @param endorsementReasonOther
     *     The endorsementReasonOther
     */
    @JsonProperty("endorsementReasonOther")
    public void setEndorsementReasonOther(String endorsementReasonOther) {
        this.endorsementReasonOther = endorsementReasonOther;
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
        return new HashCodeBuilder().append(endorsementDate).append(endorsementReason).append(endorsementReasonOther).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StartEndorsementRequest) == false) {
            return false;
        }
        StartEndorsementRequest rhs = ((StartEndorsementRequest) other);
        return new EqualsBuilder().append(endorsementDate, rhs.endorsementDate).append(endorsementReason, rhs.endorsementReason).append(endorsementReasonOther, rhs.endorsementReasonOther).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
