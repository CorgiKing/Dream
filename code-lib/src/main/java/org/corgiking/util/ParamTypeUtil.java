package org.corgiking.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ParamTypeUtil {
	
	/**
	 * 获取泛型类对象obj的实际类型
	 * @param object
	 * @param parametrizedSuperclass
	 * @return
	 */
//	public Type findType(final Object obj, Class<?> parametrizedSuperclass) {
//		
//	}

	/**
	 * 获取泛型类的某个泛型的实际类
	 * @param object 泛型类子类对象
	 * @param parametrizedSuperclass 泛型类
	 * @param typeParamName 泛型名称
	 * @return
	 */
    public static Class<?> findTypeParamClass(final Object object, Class<?> parametrizedSuperclass, String typeParamName) {

        final Class<?> thisClass = object.getClass();
        Class<?> currentClass = thisClass;
        for (;;) {
            if (currentClass.getSuperclass() == parametrizedSuperclass) {
                int typeParamIndex = -1;
                TypeVariable<?>[] typeParams = currentClass.getSuperclass().getTypeParameters();
                for (int i = 0; i < typeParams.length; i ++) {
                    if (typeParamName.equals(typeParams[i].getName())) {
                        typeParamIndex = i;
                        break;
                    }
                }

                if (typeParamIndex < 0) {
                    throw new IllegalStateException(
                            "unknown type parameter '" + typeParamName + "': " + parametrizedSuperclass);
                }

                Type genericSuperType = currentClass.getGenericSuperclass();
                if (!(genericSuperType instanceof ParameterizedType)) {
                    return Object.class;
                }

                Type[] actualTypeParams = ((ParameterizedType) genericSuperType).getActualTypeArguments();

                Type actualTypeParam = actualTypeParams[typeParamIndex];
                if (actualTypeParam instanceof ParameterizedType) {
                    actualTypeParam = ((ParameterizedType) actualTypeParam).getRawType();
                }
                if (actualTypeParam instanceof Class) {
                    return (Class<?>) actualTypeParam;
                }
                if (actualTypeParam instanceof GenericArrayType) {
                    Type componentType = ((GenericArrayType) actualTypeParam).getGenericComponentType();
                    if (componentType instanceof ParameterizedType) {
                        componentType = ((ParameterizedType) componentType).getRawType();
                    }
                    if (componentType instanceof Class) {
                        return Array.newInstance((Class<?>) componentType, 0).getClass();
                    }
                }
                if (actualTypeParam instanceof TypeVariable) {
                    // Resolved type parameter points to another type parameter.
                    TypeVariable<?> v = (TypeVariable<?>) actualTypeParam;
                    currentClass = thisClass;
                    if (!(v.getGenericDeclaration() instanceof Class)) {
                        return Object.class;
                    }

                    parametrizedSuperclass = (Class<?>) v.getGenericDeclaration();
                    typeParamName = v.getName();
                    if (parametrizedSuperclass.isAssignableFrom(thisClass)) {
                        continue;
                    } else {
                        return Object.class;
                    }
                }

                return fail(thisClass, typeParamName);
            }
            currentClass = currentClass.getSuperclass();
            if (currentClass == null) {
                return fail(thisClass, typeParamName);
            }
        }
    }

    private static Class<?> fail(Class<?> type, String typeParamName) {
        throw new IllegalStateException(
                "cannot determine the type of the type parameter '" + typeParamName + "': " + type);
    }

}
