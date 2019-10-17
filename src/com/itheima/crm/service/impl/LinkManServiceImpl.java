package com.itheima.crm.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.service.LinkManService;
import com.itheima.domain.PageBean;

@Transactional
public class LinkManServiceImpl implements LinkManService {

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	/**
	 * 分页查询联系人的方法
	 */
	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		//设置当前页数
		pageBean.setCurrentPage(currPage);
		//设置每页显示的记录数
		pageBean.setPageSize(pageSize);
		//设置记录数
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalSize(totalCount);
		//设置总的页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//设置每页显示数据的集合
		Integer begin = (currPage-1) * pageSize;
		List<LinkMan> list= linkManDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	    /**
	    * 业务层保存联系人
	    */
	    public void save(LinkMan linkMan) {
	        
	        linkManDao.save(linkMan);
	    }
		public LinkMan findById(Long lkm_id) {
			return linkManDao.findById(lkm_id);
		}
		
	    /**
	     * 修改联系人
	     */public void update(LinkMan linkMan) {
	     
	        linkManDao.update(linkMan);
	    }
	     
	     /**
	      * 业务层删除客户
	      */
	     public void delete(LinkMan linkMan) {
	      
	         linkManDao.delete(linkMan);
	     }

}