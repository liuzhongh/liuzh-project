/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-6-16
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package flex.messaging.io;

import java.util.Iterator;
import java.util.Map;
import java.util.List;

import flex.messaging.MessageException;
import flex.messaging.log.Log;
import flex.messaging.log.Logger;
import flex.messaging.util.ClassUtil;

public class MapProxy extends BeanProxy
{
    static final long serialVersionUID = 7857999941099335210L;

    private static final int NULL_KEY_ERROR = 10026;

    /**
     * Constructor
     */
    public MapProxy()
    {
        super();
        //dynamic = true;
    }

    /**
     * Construct with a default instance type.
     * @param defaultInstance defines the alias if provided
     */
    public MapProxy(Object defaultInstance)
    {
        super(defaultInstance);
        //dynamic = true;
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public List getPropertyNames(Object instance)
    {
        if (instance == null)
            return null;

        List propertyNames = null;
        List excludes = null;

        if (descriptor != null)
        {
            excludes = descriptor.getExcludesForInstance(instance);
            if (excludes == null) // For compatibility with older implementations
                excludes = descriptor.getExcludes();
        }

        // Add all Map keys as properties
        if (instance instanceof Map)
        {
            Map map = (Map)instance;

            if (map.size() > 0)
            {
                propertyNames = new ArrayList(map.size());
                SerializationContext context = getSerializationContext();

                Iterator it = map.keySet().iterator();
                while (it.hasNext())
                {
                    Object key = it.next();
                    if (key != null)
                    {
                        if (excludes != null && excludes.contains(key))
                            continue;

                        propertyNames.add(key.toString());
                    }
                    else
                    {
                        // Log null key errors
                        if (Log.isWarn() && context.logPropertyErrors)
                        {
                            Logger log = Log.getLogger(LOG_CATEGORY);
                            log.warn("Cannot send a null Map key for type {0}.",
                                    new Object[] {map.getClass().getName()});
                        }

                        if (!context.ignorePropertyErrors)
                        {
                            // Cannot send a null Map key for type {0}.
                            MessageException ex = new MessageException();
                            ex.setMessage(NULL_KEY_ERROR, new Object[] {map.getClass().getName()});
                            throw ex;
                        }
                    }
                }
            }
        }

        // Then, check for bean properties
        List beanProperties = super.getPropertyNames(instance);
        if (beanProperties != null)
        {
            if (propertyNames == null)
                propertyNames = beanProperties;
            else
                propertyNames.addAll(beanProperties);
        }

        return propertyNames;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("rawtypes")
	@Override
    public Object getValue(Object instance, String propertyName)
    {
        if (instance == null || propertyName == null)
            return null;

        Object value = null;

        // First, check for bean property
        BeanProperty bp = getBeanProperty(instance, propertyName);
        if (bp != null)
            value = super.getBeanValue(instance, bp);

        // Then check for Map entry
        if (value == null && instance instanceof Map)
            value = getMapValue((Map)instance, propertyName);

        return value;
    }

    /** {@inheritDoc} */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void setValue(Object instance, String propertyName, Object value)
    {
        if (instance == null || propertyName == null)
            return;
        
        if(value != null && value.equals(Double.NaN))
        {
        	value = null;
        }

        Map props = getBeanProperties(instance);
        if (props.containsKey(propertyName))
        {
            super.setValue(instance, propertyName, value);
        }
        else if (instance instanceof Map)
        {
            ClassUtil.validateAssignment(instance, propertyName, value);
            ((Map)instance).put(propertyName, value);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Object clone()
    {
        return super.clone();
    }

    /** {@inheritDoc} */
    @Override
    protected boolean ignorePropertyErrors(SerializationContext context)
    {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    protected boolean logPropertyErrors(SerializationContext context)
    {
        return false;
    }

    /**
     * Return the classname of the instance, including ASObject types.
     * If the instance is a Map and is in the java.util package, we return null.
     * @param instance the object to find the class name of
     * @return the class name of the object.
     */
    @Override
    protected String getClassName(Object instance)
    {
        return (instance != null && instance instanceof Map
                && instance.getClass().getName().startsWith("java.util."))?
                        null : super.getClassName(instance);
    }

    /**
     * Given a map and a property name, returns the value keyed under that property
     * name but instead of depending on {@link Map#get(Object)}, propertyName
     * is compared against key#toString. This is due to the fact that propertyNames
     * are always stored as Strings.
     *
     * @param map The Map to check against.
     * @param propertyName The property name to check for.
     * @return The value keyed under property name or null if it does not exist.
     */
    @SuppressWarnings("rawtypes")
	protected Object getMapValue(Map map, String propertyName)
    {
        for (Object key : map.keySet())
        {
            if (key.toString().equals(propertyName))
                return map.get(key);
        }
        return null;
    }
}
