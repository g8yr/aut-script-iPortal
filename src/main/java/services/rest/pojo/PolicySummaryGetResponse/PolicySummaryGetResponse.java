
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
    "policyNumber",
    "policyStatus",
    "timedPolicyStatus",
    "effectiveDate",
    "expirationDate",
    "sourceOfBusiness",
    "renewalCycle",
    "eValueStatus",
    "actualAmt",
    "termPremium",
    "residentialAddress"
})
public class PolicySummaryGetResponse {

    @JsonProperty("policyNumber")
    private String policyNumber;
    @JsonProperty("policyStatus")
    private String policyStatus;
    @JsonProperty("timedPolicyStatus")
    private String timedPolicyStatus;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("expirationDate")
    private String expirationDate;
    @JsonProperty("sourceOfBusiness")
    private String sourceOfBusiness;
    @JsonProperty("renewalCycle")
    private Integer renewalCycle;
    @JsonProperty("eValueStatus")
    private String eValueStatus;
    @JsonProperty("actualAmt")
    private String actualAmt;
    @JsonProperty("termPremium")
    private String termPremium;
    @JsonProperty("residentialAddress")
    private ResidentialAddress residentialAddress;
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
     *     The policyStatus
     */
    @JsonProperty("policyStatus")
    public String getPolicyStatus() {
        return policyStatus;
    }

    /**
     * 
     * @param policyStatus
     *     The policyStatus
     */
    @JsonProperty("policyStatus")
    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

    /**
     * 
     * @return
     *     The timedPolicyStatus
     */
    @JsonProperty("timedPolicyStatus")
    public String getTimedPolicyStatus() {
        return timedPolicyStatus;
    }

    /**
     * 
     * @param timedPolicyStatus
     *     The timedPolicyStatus
     */
    @JsonProperty("timedPolicyStatus")
    public void setTimedPolicyStatus(String timedPolicyStatus) {
        this.timedPolicyStatus = timedPolicyStatus;
    }

    /**
     * 
     * @return
     *     The effectiveDate
     */
    @JsonProperty("effectiveDate")
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 
     * @param effectiveDate
     *     The effectiveDate
     */
    @JsonProperty("effectiveDate")
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * 
     * @return
     *     The expirationDate
     */
    @JsonProperty("expirationDate")
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * 
     * @param expirationDate
     *     The expirationDate
     */
    @JsonProperty("expirationDate")
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * 
     * @return
     *     The sourceOfBusiness
     */
    @JsonProperty("sourceOfBusiness")
    public String getSourceOfBusiness() {
        return sourceOfBusiness;
    }

    /**
     * 
     * @param sourceOfBusiness
     *     The sourceOfBusiness
     */
    @JsonProperty("sourceOfBusiness")
    public void setSourceOfBusiness(String sourceOfBusiness) {
        this.sourceOfBusiness = sourceOfBusiness;
    }

    /**
     * 
     * @return
     *     The renewalCycle
     */
    @JsonProperty("renewalCycle")
    public Integer getRenewalCycle() {
        return renewalCycle;
    }

    /**
     * 
     * @param renewalCycle
     *     The renewalCycle
     */
    @JsonProperty("renewalCycle")
    public void setRenewalCycle(Integer renewalCycle) {
        this.renewalCycle = renewalCycle;
    }

    /**
     * 
     * @return
     *     The eValueStatus
     */
    @JsonProperty("eValueStatus")
    public String getEValueStatus() {
        return eValueStatus;
    }

    /**
     * 
     * @param eValueStatus
     *     The eValueStatus
     */
    @JsonProperty("eValueStatus")
    public void setEValueStatus(String eValueStatus) {
        this.eValueStatus = eValueStatus;
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

    /**
     * 
     * @return
     *     The termPremium
     */
    @JsonProperty("termPremium")
    public String getTermPremium() {
        return termPremium;
    }

    /**
     * 
     * @param termPremium
     *     The termPremium
     */
    @JsonProperty("termPremium")
    public void setTermPremium(String termPremium) {
        this.termPremium = termPremium;
    }

    /**
     * 
     * @return
     *     The residentialAddress
     */
    @JsonProperty("residentialAddress")
    public ResidentialAddress getResidentialAddress() {
        return residentialAddress;
    }

    /**
     * 
     * @param residentialAddress
     *     The residentialAddress
     */
    @JsonProperty("residentialAddress")
    public void setResidentialAddress(ResidentialAddress residentialAddress) {
        this.residentialAddress = residentialAddress;
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
        return new HashCodeBuilder().append(policyNumber).append(policyStatus).append(timedPolicyStatus).append(effectiveDate).append(expirationDate).append(sourceOfBusiness).append(renewalCycle).append(eValueStatus).append(actualAmt).append(termPremium).append(residentialAddress).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PolicySummaryGetResponse) == false) {
            return false;
        }
        PolicySummaryGetResponse rhs = ((PolicySummaryGetResponse) other);
        return new EqualsBuilder().append(policyNumber, rhs.policyNumber).append(policyStatus, rhs.policyStatus).append(timedPolicyStatus, rhs.timedPolicyStatus).append(effectiveDate, rhs.effectiveDate).append(expirationDate, rhs.expirationDate).append(sourceOfBusiness, rhs.sourceOfBusiness).append(renewalCycle, rhs.renewalCycle).append(eValueStatus, rhs.eValueStatus).append(actualAmt, rhs.actualAmt).append(termPremium, rhs.termPremium).append(residentialAddress, rhs.residentialAddress).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
