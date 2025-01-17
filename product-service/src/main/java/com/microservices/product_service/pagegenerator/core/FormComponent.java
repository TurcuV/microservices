package com.microservices.product_service.pagegenerator.core;

import com.microservices.product_service.pagegenerator.annotations.AutoGenerateComponent;


import java.lang.reflect.Field;

public class FormComponent extends HtmlComponent implements ComponentBuilderInterface {

  public FormComponent(AutoGenerateComponent annotation) {
    super(annotation);
    populateOpeningTag();
    populateComponentBody();
    populateClosingTag();
  }

  @Override
  public void populateOpeningTag() {
   setOpeningTag("<form id=\""+getComponentName()+"\" method=\"POST\" class=\"fv-plugins-bootstrap5-form-inline row row-cols-lg-auto g-3\">");
  }

  @Override
  public void populateClosingTag() {
    setClosingTag("""
                    <div class="col-12">
                      <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                  </form>""");
  }

  @Override
  public void populateComponentBody() {
   String element =  """
          <div class="col-4">
            <label>%s</label>
            <input type=%s class="form-control" name="%s"/>
          </div>
          """;
    StringBuilder formFields = new StringBuilder();

    for(Field field : getParameterClass().getDeclaredFields()) {
//      String fieldType = field.isSubclassOf(field.getType(), Number.class) ? "number" : "text";
      formFields.append(String.format(element, field.getName(), "text", field.getName()));
//      Class.forName(((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0].getTypeName()).getDeclaredFields();

    }

    setComponentBody(formFields.toString());
  }
}
