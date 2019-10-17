package com.itheima.crm.dao.impl;

import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

	public List<BaseDict> findByTypeCode(String dict_type_code) throws IOException {
     //Criteria
        //创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
        //封装条件
        dc.add(Restrictions.eq("dict_type_code", dict_type_code));
        //执行查询
        List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
       //System.out.println(list);
        return list;
	}

}
