package com.microservices.product_service.pagegenerator.core;

public interface ComponentBuilderInterface {

  void populateOpeningTag();

  void populateClosingTag();

  void populateComponentBody() throws ClassNotFoundException;
}
