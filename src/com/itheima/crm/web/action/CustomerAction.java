package com.itheima.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.UploadUtils;
import com.itheima.domain.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	//模型驱动使用对象
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	
	//注入service
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//使用set方法的方式接收数据
	private Integer currPage=1;
	public void setCurrPage(Integer currPage) {
		
		  if (currPage==null) { 
			  	currPage = 1; 
			  }
		 
		this.currPage = currPage;
	}
	//使用set方法的方式接收每页显示的记录数
	private Integer pageSize=3;
	
	public void setPageSize(Integer pageSize) {
		
		  if (pageSize==null) { pageSize =3; }
		 
		this.pageSize = pageSize;
	}
	/**
	 * 文件上传提供的三个属性；
	 * 
	 */
	private String uploadFileName;//文件名称
	private File upload;			//上传文件
	private String uploadContentType;//文件类型
	
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 * 分页查询客户的方法:findAll
	 * @return
	 * @throws Exception
	 */
	public String findAll() throws Exception {
			//接收参数：分页参数
		
		  //调用业务层查询 
		 //封装离线条件查询( 条件查询--带分页)
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class); 
		 
			  // 设置条件：（在web层设置条件）
		    if (customer.getCust_name() != null) {
		        // 输入名称:
		        detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		    }
		    if (customer.getBaseDictSource() != null) {
		        if (customer.getBaseDictSource().getDict_id() != null && !"".equals(customer.getBaseDictSource().getDict_id())) {
		            detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
		        }
		 
		    }
		    if (customer.getBaseDictLevel() != null) {
		        if (customer.getBaseDictLevel().getDict_id() != null && !"".equals(customer.getBaseDictLevel().getDict_id())) {
		            detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
		        }
		    }
		    if (customer.getBaseDictIndustry() != null && customer.getBaseDictIndustry().getDict_id() != null) {
		        if (customer.getBaseDictIndustry().getDict_id() != null && !"".equals(customer.getBaseDictIndustry().getDict_id())) {
		            detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
		        }
		    }
			//调用业务层的方法查询 
		  PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);
		  //将pageBean的数据存入值栈 
		  ValueStack valueStack = ActionContext.getContext().getValueStack();
		  valueStack.push(pageBean);
		  System.out.println(pageBean.getList());
		  return "findAll";
	}
	/**
	 * 跳转到添加客户的页面方法
	 * @return
	 */
	public String saveUI() {
		return "saveUI";
	}
	/**
	 * 跳转到添加客户的页面方法
	 * @return
	 * @throws IOException 
	 */
	public String save() throws IOException {
		System.out.println(upload);
		//上传图片
		if (upload!=null) {
			System.out.println(upload);
			//文件上传
			//设置文件上传路径
			String path = "D:/upload";
			//一个目录下存放相同文件名：随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			//一个目录下存放的文件过多：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			//创建目录
			String url = path+realPath;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdir();
			}
			//文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			   // 设置cust_image属性值
	        customer.setCust_image(url+"/"+uuidFileName);
		}
		//System.out.println(customer);
		customerService.save(customer);
		return "saveSuccess";
	}
    /**
     * 删除客户
     */
    public String delete() {
        customer = customerService.findById(customer);
        String cust_image = customer.getCust_image();
        if(customer.getCust_image()!=null) {
            File file = new File(customer.getCust_image());
            if(file.exists()) {
                file.delete();
            }
        }
        customerService.delete(customer);
        return "deleteSuccess";
    }
    /**
     * 修改用户回显
     */
    public String edit() {
     
        // 根据id查询，跳转页面，回显数据
        customer = customerService.findById(customer);
        // 将customer传递到页面：两种方式
        // 一：手动压栈,<s:property value="cust_name" />,<s:propetry name="cust_name" value="" />
        // 二：模型驱动对象，默认在栈里，<s:propetry value="model.cust_name" />
        ActionContext.getContext().getValueStack().push(customer);
        return "editSuccess";
    }
    /**
     * 修改用户
     * @throws IOException 
     */
    public String update() throws IOException {
        String cust_image = customer.getCust_image();
        // 文件项是否已经选择，如果选择了就删除原有文件，没选就使用原有文件
        if(upload != null && !"".equals(cust_image)) {
            File oldFile = new File(cust_image);
            if(oldFile.exists()) {
                oldFile.delete();
            }
            // 设置文件上传路径
            String path = "D:/upload";
            // 为了避免上传文件名相同，所以采用随机文件名
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            // 一个目录存放文件过多，目录分离
            String realPath = UploadUtils.getPath(uuidFileName);
            // 创建目录
            String dir = path + realPath;
            File file = new File(dir);
            if(!file.exists()) {
                file.mkdirs();
            }
            // 文件上传
            File destFile = new File(dir+"/"+uuidFileName);
            FileUtils.copyFile(upload, destFile);
            // 设置cust_image属性值
            customer.setCust_image(dir+"/"+uuidFileName);
        }
        customerService.update(customer);
        return "updateSuccess";
    }
    
    public String findAllCustomer() throws IOException {
    	List<Customer> list = customerService.findAll();
    	//list转json
    	JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.setExcludes(new String[] {"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
    	JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
    	ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
    	ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
    	return NONE;
    }
}
