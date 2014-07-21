package com.codenoted.test.rules;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.MockitoAnnotations;

public class MockitoRule {

  private final Object target;

  public MockitoRule(Object target) {
    this.target = target;
  }

  public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        MockitoAnnotations.initMocks(target);
        base.evaluate();
      }
    };
  }

}// ;~
