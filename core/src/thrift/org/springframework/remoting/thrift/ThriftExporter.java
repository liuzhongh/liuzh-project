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

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.springframework.context.SmartLifecycle;
import org.springframework.obm.thrift.util.ThriftUtil;
import org.springframework.util.Assert;

import java.net.InetSocketAddress;

/**
 * <P> Exports Thrift based RPC services. This implementation will expose the Thrift based POJO on the
 * {@link #port} specified (or, alternatively, the {@link #address})
 *
 * @see org.springframework.remoting.caucho.HessianExporter
 */
public class ThriftExporter extends AbstractThriftExporter implements SmartLifecycle {

    private volatile boolean running = false;

    private TServerTransport transport;

    private TServer tServer;

    private int port = ThriftUtil.DEFAULT_PORT;

    private InetSocketAddress address;

    public void setTransport(TServerTransport transport) {
        this.transport = transport;
    }

    public void setServer(TServer s) {
        this.tServer = s;
    }

    public void setPort(int listenPort) {
        Assert.isTrue(listenPort > 0, "the port must be a value greater than 0");
        this.port = listenPort;
    }

    public void setAddress(InetSocketAddress address) {
        Assert.notNull(address, "you have specified a null address");
        this.address = address;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 0;
    }

    @Override
    public void start() {

        this.running = true;
        try {

            if (logger.isDebugEnabled()) {
                logger.debug("starting " + ThriftExporter.class.getName() + ". This exporter's only been tested on Thrift 0.7. Your mileage may vary with other versions");
            }

            TServerTransport serverTransport = this.transport;
            if (null == serverTransport) { // ie, no transport specified
                if (this.address != null) {
                    serverTransport = new TServerSocket(this.address);
                } else {
                    serverTransport = new TServerSocket(this.port);
                }
            }

            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.processor(processor);

            if (logger.isDebugEnabled()) {
                logger.debug("starting to listen on " + serverTransport.toString());
            }

            if (null == this.tServer) {
                tServer = new TThreadPoolServer(args);
            }
            tServer.serve();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        if (null != this.tServer) {
            tServer.stop();
        }
        this.running = false;
    }


    @Override
    public void stop(Runnable callback) {
        stop();
        if (callback != null) {
            callback.run();
        }
    }
}
