package com.itheima.bos.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.xml.resolver.helpers.PublicId;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IDecidedzoneService;
import com.itheima.bos.web.action.base.BaseAction;
import com.itheima.crm.Customer;
import com.itheima.crm.ICustomerService;
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	//需要关联的分区id
	private String[] subareaid;
	private List<Integer> customerIds;
	
	private String searchid;
	private String staffstation;
	
	public String getSearchid() {
		return searchid;
	}

	public void setSearchid(String searchid) {
		this.searchid = searchid;
	}

	public String getStaffstation() {
		return staffstation;
	}

	public void setStaffstation(String staffstation) {
		this.staffstation = staffstation;
	}

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	//调用发布的“customerService”
	@Autowired
	private ICustomerService proxy;
	
	
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	
	/**
	 * 功能：
	 * 1.添加定区对象并且关联分区对象
	 * @return
	 */
	public String add() {
		/**
		 * 1.保存定区对象；
		 * 2.使用“多的一方“分区对象保存定区对象
		 */
		decidedzoneService.save(model,subareaid);
		return LIST;
	}
	/**
	 * 功能：
	 * 1.分页查询定区数据，需要关联取派员；
	 * @return
	 */
	public String pageQuery() {
		if (StringUtils.isNotBlank(model.getId()) && StringUtils.isNotBlank(model.getStaff().getStation())) {
			DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
			detachedCriteria.add(Restrictions.eq("id", model.getId()));
			if (model.getStaff()!=null) {
				detachedCriteria.createAlias("staff", "s");
				detachedCriteria.add(Restrictions.eq("s.station", model.getStaff().getStation()));
			}
			
		}
		decidedzoneService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});
		return NONE;
	}
	
	/**
	 * 功能：
	 * 1。获取所有没有关联定区的客户；返回json数据
	 * @return
	 */
	public String findListNotAssociation() {
		List<Customer> list = proxy.findListNotAssociation();
		this.java2Json(list, new String[]{});
		return NONE;
		
	}
	/**
	 * 功能：
	 * 1.获取已经关联了相关定区的客户；返回json数组；
	 * @return
	 */
	public String findListHasAssociation() {
		
		List<Customer> list = proxy.findListHasAssociation(model.getId());
		this.java2Json(list, new String[] {});
		return NONE;
		
	}
	/**
	 * 功能：
	 * 1.将上传的客户关联到指定的定区
	 * @return
	 */
	public String assigncustomerstodecidedzone() {
		proxy.assigncustomerstodecidedzone(model.getId(), customerIds);
		return LIST;
		
	}
}
