package Runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("Features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value="steps")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value="html:target/cucumber-report.html")
@ConfigurationParameter(key = ANSI_COLORS_DISABLED_PROPERTY_NAME, value="true")
@ConfigurationParameter(key = PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value="true")

public class TestRunner  {

}




