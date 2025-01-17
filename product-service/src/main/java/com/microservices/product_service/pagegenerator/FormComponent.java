package com.microservices.product_service.pagegenerator;

import lombok.Builder;

@Builder
public class FormComponent implements HtmlComponent{

  @Override
  public String name() {
    return null;
  }

  @Override
  public Integer elementSize() {
    return null;
  }

  @Override
  public String openingTag() {
    return "<form id=\""+name()+"\" method=\"POST\" class=\"fv-plugins-bootstrap5-form-inline row row-cols-lg-auto g-3\">";
  }

  @Override
  public String closingTag() {
    return "</form>";
  }

  public String inputElement(String name, String type) {
        return String.format("<div class=\"col-6\">\n" +
                      "<label>%s</label>\n" +
                      "<input type=\"%s\" class=\"form-control\" name=\"%s\" style=\"width: 100\" />\n" +
                      " </div>", name, type, name);

  }
}
