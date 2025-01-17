package com.microservices.product_service.pagegenerator.core;

public abstract class HtmlGenericComponent {

  private Integer elementSize;

  private String openingTag;

  private String closingTag;

  private String componentBody;

  public Integer getElementSize() {
    return elementSize;
  }

  public void setElementSize(Integer elementSize) {
    this.elementSize = elementSize;
  }

  public String getOpeningTag() {
    return openingTag;
  }

  public void setOpeningTag(String openingTag) {
    this.openingTag = openingTag;
  }

  public String getClosingTag() {
    return closingTag;
  }

  public void setClosingTag(String closingTag) {
    this.closingTag = closingTag;
  }

  public String getComponentBody() {
    return componentBody;
  }

  public void setComponentBody(String componentBody) {
    this.componentBody = componentBody;
  }
}
