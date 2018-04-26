
package services.rest.pojo.validateEndorsement.validateEndorsementResponse.json;

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
    "ruleSets",
    "allowedEndorsements"
})
public class ValidateEndorsementResponse {

    @JsonProperty("ruleSets")
    private List<RuleSet> ruleSets = new ArrayList<RuleSet>();
    @JsonProperty("allowedEndorsements")
    private List<String> allowedEndorsements = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ruleSets
     */
    @JsonProperty("ruleSets")
    public List<RuleSet> getRuleSets() {
        return ruleSets;
    }

    /**
     * 
     * @param ruleSets
     *     The ruleSets
     */
    @JsonProperty("ruleSets")
    public void setRuleSets(List<RuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }

    /**
     * 
     * @return
     *     The allowedEndorsements
     */
    @JsonProperty("allowedEndorsements")
    public List<String> getAllowedEndorsements() {
        return allowedEndorsements;
    }

    /**
     * 
     * @param allowedEndorsements
     *     The allowedEndorsements
     */
    @JsonProperty("allowedEndorsements")
    public void setAllowedEndorsements(List<String> allowedEndorsements) {
        this.allowedEndorsements = allowedEndorsements;
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
        return new HashCodeBuilder().append(ruleSets).append(allowedEndorsements).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ValidateEndorsementResponse) == false) {
            return false;
        }
        ValidateEndorsementResponse rhs = ((ValidateEndorsementResponse) other);
        return new EqualsBuilder().append(ruleSets, rhs.ruleSets).append(allowedEndorsements, rhs.allowedEndorsements).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
