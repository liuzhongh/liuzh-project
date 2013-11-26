/**
 * COPYRIGHT (C) 2013 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013年11月22日
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.core.cache.handler;

public interface CacheHandler {
	
	/**
     * 当KEY存在时，替换原来的值，当KEY不存在时，添加（相应命名空间)
     * @param namespace
     * @param key
     * @param value
     * @param expiry(过期时间，秒）
     */
    public void setWithNamespace(String namespace, final String key, final Object value, final int expiry);
    
    /**
     * 通过命名空间和key从缓存中获取值
     * @param namespace
     * @param key
     * @return
     */
    public <T> T getWithNamespace(String namespace, final String key);
    
    /**
     * 当KEY存在时，替换原来的值，当KEY不存在时，添加（相应命名空间)
     * @param key
     * @param value
     * @return
     */
    public void setWithNamespace(String namespace, final String key, final Object value);
    
    /**
     * 将相应命名空间缓存数据清除
     * @param namespace
     */
    public void invalidateNamespace(String namespace);

}
