/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 16, 2010
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.facade.test;

import com.shangkang.bo.Information;
import com.shangkang.core.exception.ServiceException;

public interface BusinessFacade {

	public Information getByPK(Integer pk) throws ServiceException;

	public void insert(Information infor, Information infor1) throws ServiceException;
}