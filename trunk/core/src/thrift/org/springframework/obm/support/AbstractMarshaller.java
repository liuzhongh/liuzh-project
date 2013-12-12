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
package org.springframework.obm.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.obm.Marshaller;
import org.springframework.obm.Unmarshaller;

/**
 * Simple base class to make sure we make as many things common and reusable as possible
 *
 */
abstract public class AbstractMarshaller<T> implements Marshaller<T>, Unmarshaller<T> {

    protected Log log = LogFactory.getLog(getClass());

}
