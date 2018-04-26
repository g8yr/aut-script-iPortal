
package services.rest.pojo.validateVIN.validateVINResponse;

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
    "vin",
    "year",
    "make",
    "modelText",
    "seriesText",
    "bodyStyleCd",
    "restraintsCode",
    "restraintsCodeText",
    "antiLockCodeText",
    "antiTheftCode",
    "antiTheftCodeText",
    "altFuel"
})
public class Vehicle {

    @JsonProperty("vin")
    private String vin;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("make")
    private String make;
    @JsonProperty("modelText")
    private String modelText;
    @JsonProperty("seriesText")
    private String seriesText;
    @JsonProperty("bodyStyleCd")
    private String bodyStyleCd;
    @JsonProperty("restraintsCode")
    private String restraintsCode;
    @JsonProperty("restraintsCodeText")
    private String restraintsCodeText;
    @JsonProperty("antiLockCodeText")
    private String antiLockCodeText;
    @JsonProperty("antiTheftCode")
    private String antiTheftCode;
    @JsonProperty("antiTheftCodeText")
    private String antiTheftCodeText;
    @JsonProperty("altFuel")
    private Boolean altFuel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The year
     */
    @JsonProperty("year")
    public Integer getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    @JsonProperty("year")
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The make
     */
    @JsonProperty("make")
    public String getMake() {
        return make;
    }

    /**
     * 
     * @param make
     *     The make
     */
    @JsonProperty("make")
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * 
     * @return
     *     The modelText
     */
    @JsonProperty("modelText")
    public String getModelText() {
        return modelText;
    }

    /**
     * 
     * @param modelText
     *     The modelText
     */
    @JsonProperty("modelText")
    public void setModelText(String modelText) {
        this.modelText = modelText;
    }

    /**
     * 
     * @return
     *     The seriesText
     */
    @JsonProperty("seriesText")
    public String getSeriesText() {
        return seriesText;
    }

    /**
     * 
     * @param seriesText
     *     The seriesText
     */
    @JsonProperty("seriesText")
    public void setSeriesText(String seriesText) {
        this.seriesText = seriesText;
    }

    /**
     * 
     * @return
     *     The bodyStyleCd
     */
    @JsonProperty("bodyStyleCd")
    public String getBodyStyleCd() {
        return bodyStyleCd;
    }

    /**
     * 
     * @param bodyStyleCd
     *     The bodyStyleCd
     */
    @JsonProperty("bodyStyleCd")
    public void setBodyStyleCd(String bodyStyleCd) {
        this.bodyStyleCd = bodyStyleCd;
    }

    /**
     * 
     * @return
     *     The restraintsCode
     */
    @JsonProperty("restraintsCode")
    public String getRestraintsCode() {
        return restraintsCode;
    }

    /**
     * 
     * @param restraintsCode
     *     The restraintsCode
     */
    @JsonProperty("restraintsCode")
    public void setRestraintsCode(String restraintsCode) {
        this.restraintsCode = restraintsCode;
    }

    /**
     * 
     * @return
     *     The restraintsCodeText
     */
    @JsonProperty("restraintsCodeText")
    public String getRestraintsCodeText() {
        return restraintsCodeText;
    }

    /**
     * 
     * @param restraintsCodeText
     *     The restraintsCodeText
     */
    @JsonProperty("restraintsCodeText")
    public void setRestraintsCodeText(String restraintsCodeText) {
        this.restraintsCodeText = restraintsCodeText;
    }

    /**
     * 
     * @return
     *     The antiLockCodeText
     */
    @JsonProperty("antiLockCodeText")
    public String getAntiLockCodeText() {
        return antiLockCodeText;
    }

    /**
     * 
     * @param antiLockCodeText
     *     The antiLockCodeText
     */
    @JsonProperty("antiLockCodeText")
    public void setAntiLockCodeText(String antiLockCodeText) {
        this.antiLockCodeText = antiLockCodeText;
    }

    /**
     * 
     * @return
     *     The antiTheftCode
     */
    @JsonProperty("antiTheftCode")
    public String getAntiTheftCode() {
        return antiTheftCode;
    }

    /**
     * 
     * @param antiTheftCode
     *     The antiTheftCode
     */
    @JsonProperty("antiTheftCode")
    public void setAntiTheftCode(String antiTheftCode) {
        this.antiTheftCode = antiTheftCode;
    }

    /**
     * 
     * @return
     *     The antiTheftCodeText
     */
    @JsonProperty("antiTheftCodeText")
    public String getAntiTheftCodeText() {
        return antiTheftCodeText;
    }

    /**
     * 
     * @param antiTheftCodeText
     *     The antiTheftCodeText
     */
    @JsonProperty("antiTheftCodeText")
    public void setAntiTheftCodeText(String antiTheftCodeText) {
        this.antiTheftCodeText = antiTheftCodeText;
    }

    /**
     * 
     * @return
     *     The altFuel
     */
    @JsonProperty("altFuel")
    public Boolean getAltFuel() {
        return altFuel;
    }

    /**
     * 
     * @param altFuel
     *     The altFuel
     */
    @JsonProperty("altFuel")
    public void setAltFuel(Boolean altFuel) {
        this.altFuel = altFuel;
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
        return new HashCodeBuilder().append(vin).append(year).append(make).append(modelText).append(seriesText).append(bodyStyleCd).append(restraintsCode).append(restraintsCodeText).append(antiLockCodeText).append(antiTheftCode).append(antiTheftCodeText).append(altFuel).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vehicle) == false) {
            return false;
        }
        Vehicle rhs = ((Vehicle) other);
        return new EqualsBuilder().append(vin, rhs.vin).append(year, rhs.year).append(make, rhs.make).append(modelText, rhs.modelText).append(seriesText, rhs.seriesText).append(bodyStyleCd, rhs.bodyStyleCd).append(restraintsCode, rhs.restraintsCode).append(restraintsCodeText, rhs.restraintsCodeText).append(antiLockCodeText, rhs.antiLockCodeText).append(antiTheftCode, rhs.antiTheftCode).append(antiTheftCodeText, rhs.antiTheftCodeText).append(altFuel, rhs.altFuel).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
