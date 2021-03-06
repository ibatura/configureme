package org.configureme.parser;

import org.configureme.sources.ConfigurationSourceKey;

/**
 * The configuration parser interfaces defines a configuration parser for a special configuration format.
 * As of now only JSON format is supported. XML and property-files are planed in the near feature.
 *
 * @author lrosenberg
 * @version $Id: $Id
 */
public interface ConfigurationParser {
	/**
	 * Returns the parsed configuration.
	 *
	 * @param name name of the configuration. Needed because its contained in the container name and not in the source content and hence isn't accessible by the parser.
	 * @param content the content of the configuration source (file or whatever).
	 * @return ParsedConfiguration object
	 * @throws org.configureme.parser.ConfigurationParserException if the file is not parseable
	 */
	ParsedConfiguration parseConfiguration(String name, String content) throws ConfigurationParserException;

	/**
	 * Return format of the current parser configuration.
	 *
	 * @return {@link ConfigurationSourceKey.Format}
	 */
	ConfigurationSourceKey.Format getFormat();
}
