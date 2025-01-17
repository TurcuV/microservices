package com.microservices.product_service.pagegenerator.core;

import com.microservices.product_service.pagegenerator.annotations.AutoGenerateComponent;

import java.lang.reflect.Method;
import java.util.List;

public final class HtmlPage {

  private final String pageName;

  private final List<Method> methodList;

  private List<? extends HtmlComponent> htmlComponents;

  public HtmlPage(String pageName, List<Method> methodList) {
    this.pageName = pageName;
    this.methodList = methodList;

    setHtmlComponents();
  }

  public void setHtmlComponents() {
    htmlComponents = methodList.stream()
                               .map(m -> {
                                 AutoGenerateComponent annotation = m.getAnnotation(AutoGenerateComponent.class);
                                 return new FormComponent(annotation);
                               }).toList();
  }

  public List<? extends HtmlComponent> getHtmlComponents() {
    return htmlComponents;
  }
}
