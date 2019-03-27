package com.itheima.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.utils.PageBean;
import com.itheima.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	@Autowired
	private IStaffService staffService;
	//属性驱动，接受将要作废的id
	private String ids;
	
	private String[] staffIds = ServletActionContext.getRequest().getParameterValues("staffIds[]");
	
	public void setStaffIds(String[] staffIds) {
		this.staffIds = staffIds;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}



	/**
	 * 添加取派员staff
	 */
	public String add() {
		staffService.save(model);
		return LIST;
	}
	
	/**
	 * 分页查询指派员
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException {

		staffService.pageQuery(pageBean);
		
/*		//使用json-lib将PageBean对象转为json，通过输出流写回页面中
		//JSONObject---将单一对象转为json
		//JSONArray----将数组或者集合对象转为json
		JsonConfig jsonConfig = new JsonConfig();
		//指定哪些属性不需要转json
		jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
		String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);*/
		java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
		return NONE;
	}
	/**
	 * 功能:
	 * 1.将某些指派员作废
	 * @return
	 */
	public String deleteBatch() {
		staffService.deleteBatch(ids);
		return LIST;

	}
	
	/**
	 * 功能：
	 * 1.还原某些取派员；
	 * @return
	 */
	public String restore() {
		staffService.restore(staffIds);
		List<Staff> list = staffService.findAll();
		this.java2Json(list, new String[] {"decidedzones"});
		return NONE;
	}
	/**
	 * 功能：
	 * 1.修改某些指派员
	 * @return
	 */
	public String edit() {
		Staff staff = staffService.findById(model.getId());
		//使用页面提交的数据进行覆盖
				staff.setName(model.getName());
				staff.setTelephone(model.getTelephone());
				staff.setHaspda(model.getHaspda());
				staff.setStandard(model.getStandard());
				staff.setStation(model.getStation());
				staffService.update(staff);
				return LIST;
		
	}
	/**
	 * 功能：
	 * 1.返回没作废的取派员
	 * @return
	 */
	public String listajax() {
		List<Staff> list = staffService.findByNotDelete();
		this.java2Json(list, new String[] {"decidedzones"});
		return NONE;
		
	}
}
