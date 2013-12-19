package io.maddy;

import org.apache.jmeter.testbeans.BeanInfoSupport;

import java.beans.PropertyDescriptor;

public class ConjureRandomVariableConfigBeanInfo extends BeanInfoSupport
{

  private static final String OPTIONS_GROUP = "options";
  private static final String CONJURE_PATTERN = "conjurePatternFile";
  private static final String VARIABLE_NAME = "variableName";
  private static final String BATCH_SIZE = "batchSize";
  private static final String DELIMITER = "delimiter";

  public ConjureRandomVariableConfigBeanInfo()
  {
    super(ConjureRandomVariableConfig.class);

    PropertyDescriptor p;

    createPropertyGroup(OPTIONS_GROUP, new String[]{VARIABLE_NAME, CONJURE_PATTERN, BATCH_SIZE, DELIMITER});

    p = property(VARIABLE_NAME);
    p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    p.setValue(DEFAULT, "conjure");
    p.setValue(NOT_EXPRESSION, Boolean.TRUE);

    p = property(CONJURE_PATTERN);
    p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    p.setValue(DEFAULT, "{}");
    p.setValue(NOT_EXPRESSION, Boolean.TRUE);

    p = property(BATCH_SIZE);
    p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    p.setValue(DEFAULT, "1");
    p.setValue(NOT_EXPRESSION, Boolean.TRUE);

    p = property(DELIMITER);
    p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    p.setValue(DEFAULT, "\n");
    p.setValue(NOT_EXPRESSION, Boolean.TRUE);

  }
}
