package com.microservices.product_service.pagegenerator.core;


import java.lang.reflect.Method;
import java.util.List;

public class HtmlPageBuilder {

  private HtmlPageBuilder() {
    throw new IllegalStateException("Utility class");
  }

  public static String buildPage(String pageName, List<Method> methodList) {
    StringBuilder result = new StringBuilder();
    HtmlPage htmlPage = new HtmlPage(pageName, methodList);

    injectPageHeader(result, pageName);
    injectPageBody(result, htmlPage.getHtmlComponents());
    injectPageFooter(result);
    return result.toString();
  }

  private static void injectPageHeader(StringBuilder result, String headerText) {
    result.append("""
                  <html>
                  <head>
                    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
                    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.min.js"></script>
                  </head>
                  <body>
                  <div class="container">
                    <div class="row">
                  """);
    if (!headerText.isEmpty()) {
      result.append(String.format("<h1>%s</h1>", headerText));
    }
  }

  private static void injectPageBody(StringBuilder result, List<? extends HtmlComponent> htmlComponents) {
    for(HtmlComponent htmlComponent : htmlComponents) {
      result.append(htmlComponent.getOpeningTag())
            .append(htmlComponent.getComponentBody())
            .append(htmlComponent.getClosingTag());
    }
  }

  private static void injectPageFooter(StringBuilder result) {
    result.append("""
                          </div>
                        </div>
                      </body>
                      """);
  }
}
