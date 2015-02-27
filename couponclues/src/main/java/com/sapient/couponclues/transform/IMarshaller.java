package com.sapient.couponclues.transform;

import java.io.InputStream;
import java.util.List;

public interface IMarshaller {
    <T> String encode(final T entity);

    <T> T decode(final String entityAsString, final Class<T> clazz);

    <T> List<T> decodeList(final String entitiesAsString, final Class<T> clazz);

    String getMime();

    <T> List<T> decodeList(final InputStream entitiesAsStream, final Class<T> clazz);
}
