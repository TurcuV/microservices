package com.microservices.product_service.pagegenerator;

import com.microservices.product_service.controller.ProductController;
import com.microservices.product_service.pagegenerator.annotations.AutoGenerateComponent;
import com.microservices.product_service.pagegenerator.annotations.AutoGeneratePage;
import com.microservices.product_service.pagegenerator.core.HtmlPage;
import com.microservices.product_service.pagegenerator.core.HtmlPageBuilder;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;


@Component
public class AutoGeneratePageProcessor {

  @PostConstruct
  public void processAnnotation() {
    // Iterate through all classes to find annotations
    Class<?> clazz = ProductController.class; // or any class you want to check

//
    if (clazz.isAnnotationPresent(AutoGeneratePage.class)) {
      List<Method> methodList = Arrays.stream(clazz.getMethods())
          .filter(m -> m.isAnnotationPresent(AutoGenerateComponent.class)).toList();

      AutoGeneratePage annotation = clazz.getAnnotation(AutoGeneratePage.class);
      String htmlPage = HtmlPageBuilder.buildPage(annotation.name(), methodList);

//      Field[] fields = methodList.getAnnotation(AutoGeneratePage.class).classType().getDeclaredFields();
//
//      System.out.println("Annotation Value: " + annotation.name());
//
//      StringBuilder html = new StringBuilder();
//      FormComponent formComponent = new FormComponent();
//      html.append(formComponent.openingTag());
//      Arrays.stream(fields).forEach(field -> {
//        html.append(formComponent.inputElement(field.getName(), "text"));
//      });
//      html.append(formComponent.closingTag());

      try {
        createFile(htmlPage);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  private void createFile(String content) throws Exception {
      File file = new File("C:\\Users\\vturcu\\IdeaProjects\\MicroservicesProject\\product-service\\src\\main\\resources\\static", "test.html");
      FileOutputStream outputStream = new FileOutputStream(file);
      outputStream.write(content.getBytes());
      file.createNewFile();
  }


}
