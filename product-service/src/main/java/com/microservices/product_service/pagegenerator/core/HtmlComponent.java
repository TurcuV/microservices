package com.microservices.product_service.pagegenerator.core;

import com.microservices.product_service.pagegenerator.HtmlComponentType;
import com.microservices.product_service.pagegenerator.annotations.AutoGenerateComponent;

public class HtmlComponent extends HtmlGenericComponent{

  private final HtmlComponentType componentType;

  private final Class<?> parameterClass;

  private final String componentName;

  private final int order;

  public HtmlComponent(AutoGenerateComponent annotation) {
    this.componentType = annotation.type();
    this.parameterClass = annotation.classType();
    this.componentName = annotation.name();
    this.order = annotation.order();
  }

  public String getComponentName() {
    return componentName;
  }

  public HtmlComponentType getComponentType() {
    return componentType;
  }

  public Class<?> getParameterClass() {
    return parameterClass;
  }

  public int getOrder() {
    return order;
  }
}
