package com.microservices.product_service.pagegenerator.annotations;

import com.microservices.product_service.pagegenerator.HtmlComponentType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoGenerateComponent {

  String name() default "";

  Class<?> classType();

  HtmlComponentType type();

  int order() default 0;
}
