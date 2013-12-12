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
package org.springframework.obm;

import java.io.InputStream;

public interface Unmarshaller<T> {

    boolean supports(Class<T> clazz);

    T unmarshal(Class<T> clazz, InputStream source) throws Exception;

}
