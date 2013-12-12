/**
 * COPYRIGHT (C) 2013 Liuzh. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of Liuzh.
 *
 * Created By: liuzh
 * Created On: 2013年12月6日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.springframework.obm.thrift;

import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.obm.support.AbstractMarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * implementation of {@link org.springframework.obm.Marshaller} and {@link org.springframework.obm.Unmarshaller}
 * that supports Apache Thrift (http://thrift.apache.org/).
 *
 */
public class ThriftMarshaller<T extends TBase> extends AbstractMarshaller<T> implements InitializingBean {

    private TSerializer serializer;

    private TDeserializer deserializer;

    public void setDeserializer(TDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    public void setSerializer(TSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    @Override
    public void marshal(T obj, OutputStream os) throws IOException, XmlMappingException {
        Assert.isInstanceOf(TBase.class, obj);
        try {
            byte[] bytesForObj = this.serializer.serialize(obj);
            FileCopyUtils.copy(bytesForObj, os);
        } catch (Throwable e) {
            if (log.isErrorEnabled()) {
                log.error("something occurred when trying to TSerializer#serialize() the response", e);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public T unmarshal(Class<T> clazz, InputStream source) throws IOException, XmlMappingException {
        Assert.isTrue(TBase.class.isAssignableFrom(clazz), "the request payload must be a subclas of TBase");
        try {
            T obj = clazz.newInstance();

            byte[] bytes = FileCopyUtils.copyToByteArray(source);

            this.deserializer.deserialize(obj, bytes);

            return obj;
        } catch (Throwable e) {
            if (log.isErrorEnabled()) {
                log.error("something occurred when trying to TDeserializer#deserialize() the incoming request", e);
            }
            throw new RuntimeException(e);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (serializer == null) {
            this.serializer = new TSerializer();
        }

        if (deserializer == null) {
            this.deserializer = new TDeserializer();
        }
    }
}
