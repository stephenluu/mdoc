package org.stephen.mdoc.api;

import java.util.List;

import org.stephen.mdoc.model.User;

/**
 * @author liuyu.lu
 * @since  Jan 21, 2016
 */
public interface IUserService {

	/**
	 * 获取用户
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	User getUser(Long userId, String username);

	/**
	 * 批量获取用户
	 * 
	 * @param userIds
	 *            用户ids
	 * @return
	 */
	List<User> getUsers(List<Long> userIds);
}
