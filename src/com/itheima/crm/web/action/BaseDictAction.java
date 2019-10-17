package com.itheima.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict>{
	//注入service
	private BaseDictService baseDictService;

	private BaseDict baseDict = new BaseDict(); 
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	public BaseDict getModel() {
		return baseDict;
	}
    /**
     * 根据类型名称查询字典的方法：findByTypeCode
     * @throws IOException 
     */
    public String findByTypeCode() throws IOException {
            
        List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
        // 将list转成json格式
        /**
        * JSONArray：将数组和list集合转成json
        * JSONObject：将对象和Map转换成json
        * JSONConfig：转JSON的配置对象
        */
         // System.out.println(list); 
        // 因为list中有一部分的数据是不需要的，所以在这里还要使用jsonConfig
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"dict_sort", "dict_enable", "dict_memo"});
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
            
     //   System.out.println(jsonArray.toString());
            
        // 需要将json打印到页面
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
            
        return NONE;
    }

}
