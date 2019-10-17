package com.itheima.crm.dao;

import java.io.IOException;
import java.util.List;

import com.itheima.crm.domain.BaseDict;

public interface BaseDictDao {

	List<BaseDict> findByTypeCode(String dict_type_code) throws IOException;

}
