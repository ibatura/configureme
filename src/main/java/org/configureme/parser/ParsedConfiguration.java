package org.configureme.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The parsed configuration is flattened content of a configuration file which contains all contained attribute name, value, environment combinations.
 *
 * @author lrosenberg
 * @version $Id: $Id
 */
public class ParsedConfiguration {
    /**
     * The list of contained attributes.
     */
    private List<ParsedAttribute<?>> attributes;

    /**
     * Timestamp of the parse processFields.
     */
    private final long parseTimestamp;

    /**
     * The name of the configuration.
     */
    private final String name;

    /**
     * External configurations that was included in current configuration.
     * This field use for reconfiguration of the current configuration.
     */
    private Collection<String> externalConfigurations;

    /**
     * Set External configuration.
     *
     * @param externalConfigurations
     *         external configuration
     */
    public void setExternalConfigurations(Collection<String> externalConfigurations) {
        if (externalConfigurations == null)
            return;
        this.externalConfigurations = externalConfigurations;
    }

    /**
     * Get external configuration.
     *
     * @return external configuration
     */
    public Collection<String> getExternalConfigurations() {
        return externalConfigurations;
    }

    /**
     * Creates a new parsed configuration object.
     *
     * @param aName
     *         the name of the configuration to which the parsed version belongs.
     */
    public ParsedConfiguration(String aName) {
        name = aName;
        parseTimestamp = System.currentTimeMillis();
        attributes = new ArrayList<>();
        externalConfigurations = new ArrayList<>();
    }


    /**
     * Adds an attribute to the internal attribute list.
     *
     * @param anAttribute
     *         the attribute to add
     */
    public void addAttribute(ParsedAttribute<?> anAttribute) {
        attributes.add(anAttribute);
    }

    /**
     * Returns the internal list of attributes.
     *
     * @return the internal list of attributes
     */
    public List<ParsedAttribute<?>> getAttributes() {
        return attributes;
    }

    /**
     * Sets the attributes.
     *
     * @param attributes
     *         attributes to set
     */
    public void setAttributes(List<ParsedAttribute<?>> attributes) {
        this.attributes = attributes;
    }

    /**
     * Returns the timestamp of the parse processFields.
     *
     * @return the timestamp of the parse processFields
     */
    public long getParseTimestamp() {
        return parseTimestamp;
    }

    /**
     * Returns the name of the configuration.
     *
     * @return the name of the configuration
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": " + attributes;
    }
}
