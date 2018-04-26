
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
    "name",
    "errors",
    "warnings"
})
public class RuleSet {

    @JsonProperty("name")
    private String name;
    @JsonProperty("errors")
    private List<Object> errors = new ArrayList<Object>();
    @JsonProperty("warnings")
    private List<Object> warnings = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The errors
     */
    @JsonProperty("errors")
    public List<Object> getErrors() {
        return errors;
    }

    /**
     * 
     * @param errors
     *     The errors
     */
    @JsonProperty("errors")
    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    /**
     * 
     * @return
     *     The warnings
     */
    @JsonProperty("warnings")
    public List<Object> getWarnings() {
        return warnings;
    }

    /**
     * 
     * @param warnings
     *     The warnings
     */
    @JsonProperty("warnings")
    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
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
        return new HashCodeBuilder().append(name).append(errors).append(warnings).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RuleSet) == false) {
            return false;
        }
        RuleSet rhs = ((RuleSet) other);
        return new EqualsBuilder().append(name, rhs.name).append(errors, rhs.errors).append(warnings, rhs.warnings).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
