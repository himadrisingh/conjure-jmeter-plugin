package io.maddy;

import io.d8a.conjure.ConjureTemplate;
import io.d8a.conjure.ConjureTemplateParser;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.engine.event.LoopIterationEvent;
import org.apache.jmeter.engine.event.LoopIterationListener;
import org.apache.jmeter.engine.util.NoConfigMerge;
import org.apache.jmeter.engine.util.NoThreadClone;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConjureRandomVariableConfig extends ConfigTestElement
    implements TestBean, LoopIterationListener, NoThreadClone, NoConfigMerge
{
  private static final Logger log = LoggingManager.getLoggerForClass();
  private static final String NL = System.getProperty("line.separator");

  private String variableName;

  private String conjurePatternFile;

  private String batchSize;

  private Long batchSizeInLong;

  private ConjureTemplate template;

  private void init() throws IOException
  {
    ConjureTemplateParser parser = new ConjureTemplateParser();
    template = parser.parse(new FileInputStream(new File(conjurePatternFile)));
    batchSizeInLong = NumberUtils.toLong(batchSize);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void iterationStart(LoopIterationEvent iterEvent)
  {
    if (template == null) {
      try {
        init();
      }
      catch (IOException e) {
        log.warn("Error: ", e);
      }
    }
    StringBuffer retVal = new StringBuffer();
    for (int i = 0; i < batchSizeInLong; i++) {
      retVal.append(template.conjure());
      retVal.append(NL);
    }

    JMeterVariables variables = JMeterContextService.getContext().getVariables();
    variables.put(getVariableName(), retVal.toString());
  }

  /**
   * @return the variableName
   */
  public synchronized String getVariableName()
  {
    return variableName;
  }

  /**
   * @param variableName the variableName to set
   */
  public synchronized void setVariableName(String variableName)
  {
    this.variableName = variableName;
  }

  /**
   * @return the name of the file containing the conjure pattern
   */
  public synchronized String getConjurePatternFile()
  {
    return conjurePatternFile;
  }

  /**
   * @param conjurePatternFile the name of file with conjure pattern
   */
  public synchronized void setConjurePatternFile(String conjurePatternFile)
  {
    this.conjurePatternFile = conjurePatternFile;
  }

  public synchronized String getBatchSize()
  {
    return batchSize;
  }

  public synchronized void setBatchSize(String batchSize)
  {
    this.batchSize = batchSize;
  }
}
