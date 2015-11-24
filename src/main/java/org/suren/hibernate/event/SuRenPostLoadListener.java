/**
* Copyright © 1998-2015, surenpi.com All Rights Reserved.
*/
package org.suren.hibernate.event;

import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;

/**
 * @author suren
 * @date 2015年11月23日 下午4:22:08
 */
public class SuRenPostLoadListener extends EncryptListener implements PostLoadEventListener
{

	private static final long	serialVersionUID	= 1L;

	public void onPostLoad(PostLoadEvent event)
	{
		unEncrypt(event.getEntity(), event.getSession());
	}
}
