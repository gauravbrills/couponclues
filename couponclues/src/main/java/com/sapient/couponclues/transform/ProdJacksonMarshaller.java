package com.sapient.couponclues.transform;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.sapient.couponclues.model.UserTransaction;

@Component
public final class ProdJacksonMarshaller implements IMarshaller {
    private final Logger logger = LoggerFactory.getLogger(ProdJacksonMarshaller.class);
    private static final String DD_MM_YYYY = "yyyy-MM-dd";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DD_MM_YYYY);
    private final ObjectMapper objectMapper;

    public ProdJacksonMarshaller() {
        super();

        objectMapper = new ObjectMapper();
        // Register date format for marshalling unmarshalling dates
        objectMapper.setDateFormat(DATE_FORMAT);
    }

    // API

    @Override
    public final <T> T decode(final String entityAsString, final Class<T> clazz) {
        Preconditions.checkNotNull(entityAsString);

        T entity = null;
        try {
            entity = objectMapper.readValue(entityAsString, clazz);
        } catch (final JsonParseException parseEx) {
            logger.error("", parseEx);
        } catch (final JsonMappingException mappingEx) {
            logger.error("", mappingEx);
        } catch (final IOException ioEx) {
            logger.error("", ioEx);
        }

        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T> List<T> decodeList(final InputStream entitiesAsStream, final Class<T> clazz) {

        Preconditions.checkNotNull(entitiesAsStream);

        List<T> entities = null;
        try {
            if (clazz.equals(UserTransaction.class)) {
                entities = objectMapper.readValue(entitiesAsStream, new TypeReference<List<UserTransaction>>() {
                    // ...
                });
            } else {
                entities = objectMapper.readValue(entitiesAsStream, List.class);
            }
        } catch (final JsonParseException parseEx) {
            logger.error("", parseEx);
        } catch (final JsonMappingException mappingEx) {
            logger.error("", mappingEx);
        } catch (final IOException ioEx) {
        }

        return entities;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T> List<T> decodeList(final String entitiesAsString, final Class<T> clazz) {

        Preconditions.checkNotNull(entitiesAsString);

        List<T> entities = null;
        try {
            if (clazz.equals(UserTransaction.class)) {
                entities = objectMapper.readValue(entitiesAsString, new TypeReference<List<UserTransaction>>() {
                    // ...
                });
            } else {
                entities = objectMapper.readValue(entitiesAsString, List.class);
            }
        } catch (final JsonParseException parseEx) {
            logger.error("", parseEx);
        } catch (final JsonMappingException mappingEx) {
            logger.error("", mappingEx);
        } catch (final IOException ioEx) {
        }

        return entities;
    }

    @Override
    public final <T> String encode(final T entity) {
        Preconditions.checkNotNull(entity);
        String entityAsJSON = null;
        try {
            entityAsJSON = objectMapper.writeValueAsString(entity);
        } catch (final JsonParseException parseEx) {
            logger.error("", parseEx);
        } catch (final JsonMappingException mappingEx) {
            logger.error("", mappingEx);
        } catch (final IOException ioEx) {
            logger.error("", ioEx);
        }

        return entityAsJSON;
    }

    @Override
    public final String getMime() {
        return MediaType.APPLICATION_JSON.toString();
    }

}
