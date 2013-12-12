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
package org.springframework.remoting.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p/>
 * Simple {@link HttpRequestHandler} implementation that fields an incoming request and
 * forwards it to Thrift to handle. The {@link HttpRequestHandler} is the simplest of all
 * Spring Web components.
 * <p/>
 * <P>The easiest way to expose an HttpRequestHandler bean in Spring style
 * is to define it in Spring's root web application context and define
 * an {@link org.springframework.web.context.support.HttpRequestHandlerServlet}
 * in <code>web.xml</code>, pointing at the target HttpRequestHandler bean
 * through its <code>servlet-name</code> which needs to match the target bean name.*
 * <p/>
 * <P>This code is inspired by (and in large part duplicates) the {@link org.apache.thrift.server.TServlet}, but adapted
 * to work with Spring web infrastructure, benefiting from dependency injection and more.
 * <p/>
 * <P>The class provides the ability to register a (<EM>static</EM>) list of headers that
 * to be written on to the response.
 * <p/>
 * <P>Simple configuration using Java configuration might look like: </P>
 * <p/>
 * <CODE>
 *
 * @Bean public ThriftServiceExporter exporter (){
 * MyThriftService.Iface serviceBean = new MyRegularServiceBean();
 * ThriftServiceExporter e = new ThriftServiceExporter ();
 * e.setService(serviceBean);
 * e.setServiceInterface(MyThriftService.Iface.class); // or, MyThriftService.class
 * return e;
 * }
 * </CODE>
 * <p/>
 * <P>Thrift clients will by default
 * use the Thrift binary protocol unless the {@link #protocolFactory} is overridden.
 * @see org.apache.thrift.server.TServlet
 * @see HttpRequestHandler
 * @see org.springframework.remoting.caucho.HessianServiceExporter
 */
public class ThriftServiceExporter extends AbstractThriftExporter implements InitializingBean, HttpRequestHandler {

    private TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();

    private final Collection<Map.Entry<String, String>> customHeaders = new ArrayList<Map.Entry<String, String>>();

    private MediaType mediaType;

    public void setProtocolFactory(TProtocolFactory inProtocolFactory) {
        this.protocolFactory = inProtocolFactory;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void addCustomHeader(String k, String v) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(k, v);
        this.customHeaders.add(header.entrySet().iterator().next());
    }

    public void setCustomHeaders(Collection<Map.Entry<String, String>> headers) {
        this.customHeaders.clear();
        this.customHeaders.addAll(headers);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        Assert.notNull(this.protocolFactory, "the 'protocolFactory' can't be null");
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            if (null != mediaType) {
                response.setContentType(mediaType.toString());
            }

            if (null != this.customHeaders) {
                for (Map.Entry<String, String> header : this.customHeaders) {
                    response.addHeader(header.getKey(), header.getValue());
                }
            }
            InputStream in = request.getInputStream();
            OutputStream out = response.getOutputStream();

            // this is not pluggable from this end
            TTransport transport = new TIOStreamTransport(in, out);
            TProtocol protocol = protocolFactory.getProtocol(transport);
            processor.process(protocol, protocol);
            out.flush();
        } catch (TException te) {
            throw new ServletException(te);
        }
    }
}
