
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
    "modelYear",
    "vin",
    "purchaseDate"
})
public class AddVehiclePostRequest {

    @JsonProperty("modelYear")
    private String modelYear;
    @JsonProperty("vin")
    private String vin;
    @JsonProperty("purchaseDate")
    private String purchaseDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The modelYear
     */
    @JsonProperty("modelYear")
    public String getModelYear() {
        return modelYear;
    }

    /**
     * 
     * @param modelYear
     *     The modelYear
     */
    @JsonProperty("modelYear")
    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    /**
     * 
     * @return
     *     The vin
     */
    @JsonProperty("vin")
    public String getVin() {
        return vin;
    }

    /**
     * 
     * @param vin
     *     The vin
     */
    @JsonProperty("vin")
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * 
     * @return
     *     The purchaseDate
     */
    @JsonProperty("purchaseDate")
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * 
     * @param purchaseDate
     *     The purchaseDate
     */
    @JsonProperty("purchaseDate")
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
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
        return new HashCodeBuilder().append(modelYear).append(vin).append(purchaseDate).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AddVehiclePostRequest) == false) {
            return false;
        }
        AddVehiclePostRequest rhs = ((AddVehiclePostRequest) other);
        return new EqualsBuilder().append(modelYear, rhs.modelYear).append(vin, rhs.vin).append(purchaseDate, rhs.purchaseDate).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
