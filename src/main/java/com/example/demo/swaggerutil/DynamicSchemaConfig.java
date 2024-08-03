package com.example.demo.swaggerutil;

import io.github.classgraph.ClassGraph;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class DynamicSchemaConfig {


    @Bean
    public CommandLineRunner dynamicSchemaCustomiserRunner() {
        return args -> {
            for (Class<?> clazz : getClasses("com.example.demo")) {
                for (Field field : clazz.getDeclaredFields()) {
                    Enum2AllowableValues annotation = field.getAnnotation(Enum2AllowableValues.class);
                    if (annotation == null) {
                        continue;
                    }
                    io.swagger.v3.oas.annotations.media.Schema oriSchema = field.getAnnotation(io.swagger.v3.oas.annotations.media.Schema.class);
                    if (oriSchema == null) {
                        continue;
                    }
                    String[] enumValues = getEnumValues(annotation.value(), annotation.method());
                    setSchemaAllowableValues(oriSchema, enumValues);
                }
            }
        };
    }

    private void setSchemaAllowableValues(Object schema, String[] values) {
        try {
            if (Proxy.isProxyClass(schema.getClass())) {
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(schema);
                setSchemaAllowableValues(invocationHandler, values);
                return;
            }
            Field memberValues = schema.getClass().getDeclaredField("memberValues");
            memberValues.setAccessible(true);
            Map<String, Object> memberValuesMap = (Map<String, Object>) memberValues.get(schema);
            memberValuesMap.put("allowableValues", values);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private String[] getEnumValues(Class<? extends Enum<?>> enumClass, String methodName) {
        try {
            Method method = null;
            Method method2 = null;
            if (methodName.indexOf(":") >= 0) {
                String[] split = methodName.split(":");
                method = enumClass.getMethod(split[0].trim());
                method2 = enumClass.getMethod(split[1].trim());
            } else {
                method = enumClass.getMethod(methodName);
                method2 = null;
            }
            final Method finalMethod = method;
            final Method finalMethod2 = method2;
            return Arrays.stream(enumClass.getEnumConstants())
                .map(enumConstant -> {
                    try {
                        if (finalMethod2 != null) {
                            return finalMethod.invoke(enumConstant).toString() + ":" + finalMethod2.invoke(enumConstant).toString();
                        } else {
                            return finalMethod.invoke(enumConstant).toString();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(String[]::new);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Method " + methodName + " not found in enum " + enumClass.getName());
        }
    }

    private List<Class<?>> getClasses(String packageName) {
        // 使用 ClassGraph 库扫描包中的类
        return new ClassGraph().enableAllInfo().whitelistPackages(packageName).scan()
            .getAllClasses().loadClasses();
    }
}
