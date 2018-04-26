
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
    "application",
    "sourceApplication",
    "address",
    "userId",
    "correlationId",
    "sessionId"
})
public class XApplicationContext {

    @JsonProperty("application")
    private String application;
    @JsonProperty("sourceApplication")
    private String sourceApplication;
    @JsonProperty("address")
    private String address;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("correlationId")
    private String correlationId;
    @JsonProperty("sessionId")
    private String sessionId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The application
     */
    @JsonProperty("application")
    public String getApplication() {
        return application;
    }

    /**
     * 
     * @param application
     *     The application
     */
    @JsonProperty("application")
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * 
     * @return
     *     The sourceApplication
     */
    @JsonProperty("sourceApplication")
    public String getSourceApplication() {
        return sourceApplication;
    }

    /**
     * 
     * @param sourceApplication
     *     The sourceApplication
     */
    @JsonProperty("sourceApplication")
    public void setSourceApplication(String sourceApplication) {
        this.sourceApplication = sourceApplication;
    }

    /**
     * 
     * @return
     *     The address
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The userId
     */
    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The userId
     */
    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     *     The correlationId
     */
    @JsonProperty("correlationId")
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * 
     * @param correlationId
     *     The correlationId
     */
    @JsonProperty("correlationId")
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    /**
     * 
     * @return
     *     The sessionId
     */
    @JsonProperty("sessionId")
    public String getSessionId() {
        return sessionId;
    }

    /**
     * 
     * @param sessionId
     *     The sessionId
     */
    @JsonProperty("sessionId")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
        return new HashCodeBuilder().append(application).append(sourceApplication).append(address).append(userId).append(correlationId).append(sessionId).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof XApplicationContext) == false) {
            return false;
        }
        XApplicationContext rhs = ((XApplicationContext) other);
        return new EqualsBuilder().append(application, rhs.application).append(sourceApplication, rhs.sourceApplication).append(address, rhs.address).append(userId, rhs.userId).append(correlationId, rhs.correlationId).append(sessionId, rhs.sessionId).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
