package com.oudmon.ble.base.bluetooth;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes3.dex */
public class ParameterizedTypeImpl implements ParameterizedType {
    private final Type rawType;
    private final Type[] typeArguments;

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return null;
    }

    public static ParameterizedTypeImpl get(Type type, Type... typeArr) {
        return new ParameterizedTypeImpl(type, typeArr);
    }

    private ParameterizedTypeImpl(Type type, Type... typeArr) {
        this.rawType = type;
        this.typeArguments = canonicalize((Type[]) typeArr.clone());
    }

    private Type[] canonicalize(Type[] typeArr) {
        if (typeArr == null || typeArr.length <= 1) {
            return typeArr;
        }
        Type[] typeArr2 = new Type[typeArr.length - 1];
        for (int i = 0; i < typeArr.length; i++) {
            if (i > 0) {
                typeArr2[i - 1] = typeArr[i];
            }
        }
        return new Type[]{new ParameterizedTypeImpl(typeArr[0], typeArr2)};
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return (Type[]) this.typeArguments.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.rawType;
    }

    public String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.typeArguments.length + 1) * 30);
        sb.append(typeToString(this.rawType));
        if (this.typeArguments.length == 0) {
            return sb.toString();
        }
        sb.append("<");
        sb.append(typeToString(this.typeArguments[0]));
        for (int i = 1; i < this.typeArguments.length; i++) {
            sb.append(", ");
            sb.append(typeToString(this.typeArguments[i]));
        }
        sb.append(">");
        return sb.toString();
    }
}
