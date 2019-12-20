package com.kunlong.platform.util;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
 * 
 * @className: ValueFieldHelper
 * @description: TODO
 * @author zz | www.integriti.cn
 * @date 2018年7月16日
 *
 */
public class ValueFieldHelper {
	
	private ValueFieldHelper() {}

	/**
	 * 获取域值
	 * @param items
	 * @param method
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S,K> List<K> getFieldValues(List<S> items, Function<S,K> method) {
		if(items == null) {
			return Collections.EMPTY_LIST;
		}
		return items.stream().map(method).filter(a -> a!=null).collect(Collectors.toList());
	}
	
	/**
	 * 一对一填充
	 * 如
	 * User{id:1,orgId:2,org:{},...}
	 * Org:{id:2,name:'org',....}
	 * @param tarItems 如List<User>
	 * @param tarFieldGetFn 如 User::getOrgId
	 * @param srcItems 如List<Org>
	 * @param srcIdGetFn 如 Org::getId
	 * @param action 如User::setOrg
	 */
	public static <S, K, T> void oneToOne(Collection<S> tarItems, Function<S, K> tarFieldGetFn, Collection<T> srcItems, Function<T, K> srcIdGetFn,
			BiConsumer<S, T> action) {
		if(srcItems == null || srcItems.isEmpty()) return ;
		Map<K,T> map = srcItems.stream().collect(Collectors.toMap(srcIdGetFn, Function.identity()));
		for(S item:tarItems) {
			action.accept(item, map.get(tarFieldGetFn.apply(item)));
		}
		map.clear();
		map = null;
	}

}
