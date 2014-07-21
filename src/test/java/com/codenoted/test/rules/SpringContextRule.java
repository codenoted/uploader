package com.codenoted.test.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * /** Creates a spring context's for testing Spring components.
 * 
 * 
 * @author andreas
 * 
 */
public class SpringContextRule implements TestRule {

  /** The target test. */
  private final Object target;

  /** A list of class-path contexts. */
  private final String[] classpathContextFiles;

  public SpringContextRule(Object target, String... classpathContextFiles) {
    this.target = target;
    this.classpathContextFiles = classpathContextFiles;
  }

  @Override
  public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(classpathContextFiles);
        context.getAutowireCapableBeanFactory().autowireBean(target);
        context.start();
        try {
          base.evaluate();
        } finally {
          context.close();
        }
      }
    };
  }

}
