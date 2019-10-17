package com.itheima.crm.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
@Transactional
public class BaseDictServiceImpl implements BaseDictService {
	// 注入Dao
	private BaseDictDao baseDictDao;
	
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	/**
	 * 根据字典按类别查询
	 */
	public List<BaseDict> findByTypeCode(String dict_type_code) throws IOException {
		//System.out.println(baseDictDao.findByTypeCode(dict_type_code)); 
		return baseDictDao.findByTypeCode(dict_type_code);
	}

}
