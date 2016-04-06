package com.erx.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CacheHelper {
	public static <T, E> List<E> getList(Map<T, List<E>> map, T key) {
		return getList(map, key, true);
	}
	
	public static <T, E> List<E> getList(Map<T, List<E>> map, T key, boolean insert) {
		if(map.get(key) == null && insert){
			ArrayList<E> value = new ArrayList<E>();
			map.put(key, value);
		}
		return map.get(key);
	}
	
	
	public static <T, E> List<E> getAll(Map<T, List<E>> map) {
		List<E> results = new ArrayList<>();
		Collection<List<E>> list = map.values();
		for (List<E> tmp : list) {
			results.addAll(tmp);
		}
		return results;
	}
	
	
}
