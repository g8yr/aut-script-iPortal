
package services.rest.pojo.validateVehiclesList.validateVehiclesResponse;

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
    "manufacturer",
    "series",
    "model",
    "bodyStyle",
    "oid",
    "vehIdentificationNo",
    "vehicleStatus",
    "ownership",
    "usage",
    "salvaged",
    "garagingDifferent",
    "antiTheft",
    "registeredOwner"
})
public class Vehicle {

    @JsonProperty("modelYear")
    private String modelYear;
    @JsonProperty("manufacturer")
    private String manufacturer;
    @JsonProperty("series")
    private String series;
    @JsonProperty("model")
    private String model;
    @JsonProperty("bodyStyle")
    private String bodyStyle;
    @JsonProperty("oid")
    private String oid;
    @JsonProperty("vehIdentificationNo")
    private String vehIdentificationNo;
    @JsonProperty("vehicleStatus")
    private String vehicleStatus;
    @JsonProperty("ownership")
    private String ownership;
    @JsonProperty("usage")
    private String usage;
    @JsonProperty("salvaged")
    private String salvaged;
    @JsonProperty("garagingDifferent")
    private String garagingDifferent;
    @JsonProperty("antiTheft")
    private String antiTheft;
    @JsonProperty("registeredOwner")
    private String registeredOwner;
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
     *     The manufacturer
     */
    @JsonProperty("manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 
     * @param manufacturer
     *     The manufacturer
     */
    @JsonProperty("manufacturer")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * 
     * @return
     *     The series
     */
    @JsonProperty("series")
    public String getSeries() {
        return series;
    }

    /**
     * 
     * @param series
     *     The series
     */
    @JsonProperty("series")
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * 
     * @return
     *     The model
     */
    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *     The model
     */
    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 
     * @return
     *     The bodyStyle
     */
    @JsonProperty("bodyStyle")
    public String getBodyStyle() {
        return bodyStyle;
    }

    /**
     * 
     * @param bodyStyle
     *     The bodyStyle
     */
    @JsonProperty("bodyStyle")
    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    /**
     * 
     * @return
     *     The oid
     */
    @JsonProperty("oid")
    public String getOid() {
        return oid;
    }

    /**
     * 
     * @param oid
     *     The oid
     */
    @JsonProperty("oid")
    public void setOid(String oid) {
        this.oid = oid;
    }

    /**
     * 
     * @return
     *     The vehIdentificationNo
     */
    @JsonProperty("vehIdentificationNo")
    public String getVehIdentificationNo() {
        return vehIdentificationNo;
    }

    /**
     * 
     * @param vehIdentificationNo
     *     The vehIdentificationNo
     */
    @JsonProperty("vehIdentificationNo")
    public void setVehIdentificationNo(String vehIdentificationNo) {
        this.vehIdentificationNo = vehIdentificationNo;
    }

    /**
     * 
     * @return
     *     The vehicleStatus
     */
    @JsonProperty("vehicleStatus")
    public String getVehicleStatus() {
        return vehicleStatus;
    }

    /**
     * 
     * @param vehicleStatus
     *     The vehicleStatus
     */
    @JsonProperty("vehicleStatus")
    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    /**
	 * @return the ownership
	 */
    @JsonProperty("ownership")
	public String getOwnership() {
		return ownership;
	}

	/**
	 * @param ownership the ownership to set
	 */
    @JsonProperty("ownership")
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	/**
	 * @return the usage
	 */
    @JsonProperty("usage")
	public String getUsage() {
		return usage;
	}

	/**
	 * @param usage the usage to set
	 */
    @JsonProperty("usage")
	public void setUsage(String usage) {
		this.usage = usage;
	}

	/**
	 * @return the salvaged
	 */
    @JsonProperty("salvaged")
	public String getSalvaged() {
		return salvaged;
	}

	/**
	 * @param salvaged the salvaged to set
	 */
    @JsonProperty("salvaged")
	public void setSalvaged(String salvaged) {
		this.salvaged = salvaged;
	}

	/**
	 * @return the garagingDifferent
	 */
    @JsonProperty("garagingDifferent")
	public String getGaragingDifferent() {
		return garagingDifferent;
	}

	/**
	 * @param garagingDifferent the garagingDifferent to set
	 */
    @JsonProperty("garagingDifferent")
	public void setGaragingDifferent(String garagingDifferent) {
		this.garagingDifferent = garagingDifferent;
	}

	/**
	 * @return the antiTheft
	 */
    @JsonProperty("antiTheft")
	public String getAntiTheft() {
		return antiTheft;
	}

	/**
	 * @param antiTheft the antiTheft to set
	 */
    @JsonProperty("antiTheft")
	public void setAntiTheft(String antiTheft) {
		this.antiTheft = antiTheft;
	}

	/**
	 * @return the registeredOwner
	 */
    @JsonProperty("registeredOwner")
	public String getRegisteredOwner() {
		return registeredOwner;
	}

	/**
	 * @param registeredOwner the registeredOwner to set
	 */
    @JsonProperty("registeredOwner")
	public void setRegisteredOwner(String registeredOwner) {
		this.registeredOwner = registeredOwner;
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
        return new HashCodeBuilder().append(modelYear)
        		                    .append(manufacturer)
        		                    .append(series)
        		                    .append(model)
        		                    .append(bodyStyle)
        		                    .append(oid)
        		                    .append(vehIdentificationNo)
        		                    .append(vehicleStatus)
        		                    .append(ownership)
        		                    .append(usage)
        		                    .append(salvaged)
        		                    .append(garagingDifferent)
        		                    .append(antiTheft)
        		                    .append(registeredOwner)
        		                    .append(additionalProperties)
        		                    .toHashCode();
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
        return new EqualsBuilder().append(modelYear, rhs.modelYear)
        		                  .append(manufacturer, rhs.manufacturer)
        		                  .append(series, rhs.series)
        		                  .append(model, rhs.model)
        		                  .append(bodyStyle, rhs.bodyStyle)
        		                  .append(oid, rhs.oid)
        		                  .append(vehIdentificationNo, rhs.vehIdentificationNo)
        		                  .append(vehicleStatus, rhs.vehicleStatus)
        		                  .append(ownership, rhs.ownership)
        		                  .append(usage, rhs.usage)
        		                  .append(salvaged, rhs.salvaged)
        		                  .append(garagingDifferent, rhs.garagingDifferent)
        		                  .append(antiTheft, rhs.antiTheft)
        		                  .append(registeredOwner, rhs.registeredOwner)
        		                  .append(additionalProperties, rhs.additionalProperties)
        		                  .isEquals();
    }

}
