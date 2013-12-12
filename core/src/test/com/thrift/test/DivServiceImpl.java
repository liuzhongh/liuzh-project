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
package com.thrift.test;

import org.apache.thrift.TException;

import com.thrift.test.DivService.Iface;

public class DivServiceImpl implements Iface {

	@Override
	public int div(int param1, int param2) throws TException
	{
		return param1 - param2;
	}

}
