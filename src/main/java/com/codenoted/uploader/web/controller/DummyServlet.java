package com.codenoted.uploader.web.controller;

import javax.servlet.http.HttpServlet;

/**
 * Purpose of the class is to extract the full path of web-application.
 * 
 * @author andreas
 */

@SuppressWarnings("serial")
public class DummyServlet extends HttpServlet {

  public DummyServlet() {
  }

  /**
   * Return the real path of the web-app
   * 
   * @return path, value could be null.
   */
  public String getContextPath() {
    String result = null;

    if (this.getServletContext() == null && this.getServletContext().getRealPath("/") == null) {
      result = this.getServletContext().getRealPath("/");
    }
    return result;
  }

}//
