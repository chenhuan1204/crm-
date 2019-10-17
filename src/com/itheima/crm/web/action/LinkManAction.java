package com.itheima.crm.web.action;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.LinkManService;
import com.itheima.domain.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	// 模型驱动
	private LinkMan linkMan = new LinkMan();

	public LinkMan getModel() {
		return linkMan;
	}

	// 属性注入
	private CustomerService customerService;
	private LinkManService linkManService;

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//分页参数
	private Integer currPage=1;
	private Integer pageSize=3;


	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	public String findAll() {
		//创建离线条件查询：
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		//设置条件
		
	    if(linkMan.getLkm_name() != null) {
	        // 设置按名称查询条件
	        detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
	    }
	    if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
	        // 设置按性别查询
	        detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
	    }
		//调用业务层
		PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	/**
	 *   跳转保存联系人
	 * @return
	 */

	public String saveUI() {
		List<Customer> list = customerService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);;
		return "saveUI";
	}

	/**
	 * 保存联系人
	 */public String save(){
		 linkManService.save(linkMan);
		 return "saveSuccess";

	 }

	 /**
	  * 修改联系人
	  */public String edit(){
		  //查询某个联系人，查询所有客户
		 
		  //根据id查询联系人
		  linkMan = linkManService.findById(linkMan.getLkm_id());
		  //查询所有客户
		  List<Customer> list = customerService.findAll();
		  //将对象的值存入到值栈
		  ActionContext.getContext().getValueStack().set("list", list);
		  ActionContext.getContext().getValueStack().push(linkMan);
		  return "editSuccess";

	  }
	    public String update() {
            
	        linkManService.update(linkMan);
	        return "updateSuccess";
	    }
	    
	    /**
	     * 删除联系人
	     */
	    public String delete() {
	        System.out.println("delete执行了");
	        linkMan = linkManService.findById(linkMan.getLkm_id());
	        linkManService.delete(linkMan);
	        return "deleteSuccess";
	    }
}
