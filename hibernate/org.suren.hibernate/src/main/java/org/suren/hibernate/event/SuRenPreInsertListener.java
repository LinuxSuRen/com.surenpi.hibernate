/**
* Copyright © 1998-2015, surenpi.com. All Rights Reserved.
*/
package org.suren.hibernate.event;

import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;

/**
 * @author suren
 * @since jdk1.6
 * 2015年11月23日
 */
public class SuRenPreInsertListener extends EncryptListener implements PreInsertEventListener {

	private static final long	serialVersionUID	= 1L;

	public boolean onPreInsert(PreInsertEvent event)
	{
		encrypt(event.getEntity(), event.getPersister().getPropertyNames(), event.getState());
		
		return false;
	}
}
