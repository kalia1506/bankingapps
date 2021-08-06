package com.kcpit.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcpit.dto.AccountDetailsDTO;

public class JsonUtils {

	public static byte[] toJson(AccountDetailsDTO dto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
       // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(dto);
	}

}
