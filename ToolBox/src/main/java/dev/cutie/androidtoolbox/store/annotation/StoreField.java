package dev.cutie.androidtoolbox.store.annotation;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StoreField
{ 
    String value();
}
