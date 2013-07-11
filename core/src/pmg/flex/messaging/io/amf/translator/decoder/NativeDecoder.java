/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-6-15
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package flex.messaging.io.amf.translator.decoder;

public class NativeDecoder extends ActionScriptDecoder {
	public Object decodeObject(Object shell, Object encodedObject,
			@SuppressWarnings("rawtypes") Class desiredClass)
	{
		if (DecoderFactory.isNumber(desiredClass))
		{
			NumberDecoder numberDecoder = new NumberDecoder();

			return numberDecoder.decodeObject(encodedObject, desiredClass);
		}

		return encodedObject;
	}
}
