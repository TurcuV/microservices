package com.microservices.product_service.pagegenerator;

public interface HtmlComponent {
  String name();
  Integer elementSize();
  String openingTag();
  String closingTag();
}
