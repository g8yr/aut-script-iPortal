
package services.rest.pojo.ViewCoveragesGetResponse;

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
    "coverageCd",
    "coverageDescription",
    "coverageLimit",
    "coverageLimitDisplay",
    "coverageType",
    "customerDisplayed"
})
public class Coverage {

    @JsonProperty("coverageCd")
    private String coverageCd;
    @JsonProperty("coverageDescription")
    private String coverageDescription;
    @JsonProperty("coverageLimit")
    private String coverageLimit;
    @JsonProperty("coverageLimitDisplay")
    private String coverageLimitDisplay;
    @JsonProperty("coverageType")
    private String coverageType;
    @JsonProperty("customerDisplayed")
    private Boolean customerDisplayed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The coverageCd
     */
    @JsonProperty("coverageCd")
    public String getCoverageCd() {
        return coverageCd;
    }

    /**
     * 
     * @param coverageCd
     *     The coverageCd
     */
    @JsonProperty("coverageCd")
    public void setCoverageCd(String coverageCd) {
        this.coverageCd = coverageCd;
    }

    /**
     * 
     * @return
     *     The coverageDescription
     */
    @JsonProperty("coverageDescription")
    public String getCoverageDescription() {
        return coverageDescription;
    }

    /**
     * 
     * @param coverageDescription
     *     The coverageDescription
     */
    @JsonProperty("coverageDescription")
    public void setCoverageDescription(String coverageDescription) {
        this.coverageDescription = coverageDescription;
    }

    /**
     * 
     * @return
     *     The coverageLimit
     */
    @JsonProperty("coverageLimit")
    public String getCoverageLimit() {
        return coverageLimit;
    }

    /**
     * 
     * @param coverageLimit
     *     The coverageLimit
     */
    @JsonProperty("coverageLimit")
    public void setCoverageLimit(String coverageLimit) {
        this.coverageLimit = coverageLimit;
    }

    /**
     * 
     * @return
     *     The coverageLimitDisplay
     */
    @JsonProperty("coverageLimitDisplay")
    public String getCoverageLimitDisplay() {
        return coverageLimitDisplay;
    }

    /**
     * 
     * @param coverageLimitDisplay
     *     The coverageLimitDisplay
     */
    @JsonProperty("coverageLimitDisplay")
    public void setCoverageLimitDisplay(String coverageLimitDisplay) {
        this.coverageLimitDisplay = coverageLimitDisplay;
    }

    /**
     * 
     * @return
     *     The coverageType
     */
    @JsonProperty("coverageType")
    public String getCoverageType() {
        return coverageType;
    }

    /**
     * 
     * @param coverageType
     *     The coverageType
     */
    @JsonProperty("coverageType")
    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    /**
     * 
     * @return
     *     The customerDisplayed
     */
    @JsonProperty("customerDisplayed")
    public Boolean getCustomerDisplayed() {
        return customerDisplayed;
    }

    /**
     * 
     * @param customerDisplayed
     *     The customerDisplayed
     */
    @JsonProperty("customerDisplayed")
    public void setCustomerDisplayed(Boolean customerDisplayed) {
        this.customerDisplayed = customerDisplayed;
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
        return new HashCodeBuilder().append(coverageCd).append(coverageDescription).append(coverageLimit).append(coverageLimitDisplay).append(coverageType).append(customerDisplayed).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Coverage) == false) {
            return false;
        }
        Coverage rhs = ((Coverage) other);
        return new EqualsBuilder().append(coverageCd, rhs.coverageCd).append(coverageDescription, rhs.coverageDescription).append(coverageLimit, rhs.coverageLimit).append(coverageLimitDisplay, rhs.coverageLimitDisplay).append(coverageType, rhs.coverageType).append(customerDisplayed, rhs.customerDisplayed).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
