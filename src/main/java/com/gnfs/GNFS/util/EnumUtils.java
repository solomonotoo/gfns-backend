package com.gnfs.GNFS.util;

import java.util.Arrays;
import java.util.List;

import com.gnfs.GNFS.dto.enums.EnumResponse;

//NB A helper class that will convert any enum to a list of EnumResponse
public class EnumUtils {
	
	public static<E extends Enum<E>> List<EnumResponse> toEnumResponseList(Class<E> enumClass){
		return Arrays.stream(enumClass.getEnumConstants())
				.map(e -> new EnumResponse(
						e.name(),
						getDisplayName(e)
						))
				.toList();
	}
	
	// Use reflection to get getDisplayName() if it exists, otherwise fallback to enum name
    private static <E extends Enum<E>> String getDisplayName(E e) {
        try {
            var method = e.getClass().getMethod("getDisplayName");
            return (String) method.invoke(e);
        } catch (Exception ex) {
            return e.name(); // fallback
        }
    }

}
