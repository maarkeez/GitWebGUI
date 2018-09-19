package com.gitwebgui.utils.parser;

import com.gitwebgui.utils.parser.model.DMDField;
import com.gitwebgui.utils.parser.model.DMDObject;
import com.gitwebgui.utils.parser.model.ObjectDest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class CustomParser<T extends Object> {


    public CustomParser(){

    }


    @Getter
    private Function<T, ObjectDest> objectDestParser = new Function<T, ObjectDest>() {
        public ObjectDest apply(T obj) {

            ObjectDest dest = new ObjectDest();
            Class objClass = obj.getClass();
            try {
                if (objClass.isAnnotationPresent(DMDObject.class)) {

                    DMDObject dmdObject = (DMDObject) objClass.getAnnotation(DMDObject.class);
                    dest.setType(dmdObject.value());

                    Arrays.stream(objClass.getDeclaredFields()).forEach(f -> {
                        if (f.isAnnotationPresent(DMDField.class)) {
                            DMDField dmdField = f.getAnnotation(DMDField.class);
                            try {
                                f.setAccessible(true);

                                if (f.get(obj) instanceof Iterable) {
                                    ParameterizedType stringListType = (ParameterizedType) f.getGenericType();
                                    Class<?> iterableClass = (Class<?>) stringListType.getActualTypeArguments()[0];

                                    if ( iterableClass.isAnnotationPresent(DMDObject.class)) {
                                        //Is custom class, must do it recursively


                                        Iterable it = (Iterable) f.get(obj);
                                        Iterator iter = it.iterator();
                                        log.info("PARSING ITERABLE");

                                        CustomParser cp = new CustomParser();

                                        List list = new ArrayList<>();
                                        while (iter.hasNext()) {
                                            Object itObj = iter.next();
                                            log.info("    - This is the nested iterable object: {}", itObj);
                                            ObjectDest itObjDest = (ObjectDest) cp.getObjectDestParser().apply(itObj);
                                            if (itObjDest != null) {
                                                log.info("    - ADDED:{}", itObj);
                                                list.add(itObjDest);
                                            }
                                        }
                                        dest.getFields().put(dmdField.value(), list);

                                    }else{
                                        //Any other class, just add the iterable as it is
                                        dest.getFields().put(dmdField.value(), f.get(obj));
                                    }
                                } else {
                                    dest.getFields().put(dmdField.value(), f.get(obj));
                                }
                            } catch (IllegalAccessException e) {
                                log.error("Could not access to field:{}. Error:", f.getName(), e);
                            }
                        }
                    });

                    return dest;
                }
            } catch (Exception e) {
                log.error("Could not parse object: ", e);
            }
            log.info("Could not parse object of class: " + objClass.getName());
            return null;
        }
    };
}
