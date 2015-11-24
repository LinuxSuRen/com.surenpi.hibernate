/**
* Copyright © 1998-2015, surenpi.com All Rights Reserved.
*/
package org.suren.hibernate.event;

import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;

/**
 * @author suren
 * @date 2015年11月23日 下午5:27:02
 */
public class SuRenPostInsertListener extends EncryptListener implements PostInsertEventListener
{

	private static final long	serialVersionUID	= 1L;

	public void onPostInsert(PostInsertEvent event)
	{
		unEncrypt(event.getEntity(), event.getSession());
	}
}
