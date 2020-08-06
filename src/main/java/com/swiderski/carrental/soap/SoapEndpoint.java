package com.swiderski.carrental.soap;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SoapEndpoint {

    String bindingUri() default "http://www.w3.org/2003/05/soap/bindings/HTTP/";

    String publish();
}
