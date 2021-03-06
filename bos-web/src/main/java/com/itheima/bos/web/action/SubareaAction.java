package com.itheima.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.domain.Region;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.IDecidedzoneService;
import com.itheima.bos.service.IRegionService;
import com.itheima.bos.service.ISubareaService;
import com.itheima.bos.utils.FileUtils;
import com.itheima.bos.utils.PinYin4jUtils;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{
	@Autowired
	private ISubareaService subareaService;
	@Autowired
	private IRegionService regionService;
	@Autowired
	private IDecidedzoneService decidedzoneService;
	//属性驱动，接收上传的文件
	private File subareaFile;

	public void setSubareaFile(File subareaFile) {
		this.subareaFile = subareaFile;
	}

	/**
	 * 添加分区
	 */
	public String add(){
		subareaService.save(model);
		return LIST;
	}
	
	/**
	 * 功能：
	 * 1.刷新时查询分区分页数据；
	 * 2.带有查询条件的分区数据分页查询；
	 * @return
	 */
	public String pageQuery(){
		/**
		 * 带有条件的查询
		 */
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		if (StringUtils.isNotBlank(addresskey)) {
			detachedCriteria.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		Region region = model.getRegion();
		if(region != null) {
			//当需要关联查询时，需要对关联对象设别名，查询条件也要使用别名
			detachedCriteria.createAlias("region", "r");
			//获取region表的几个字段
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			
			if (StringUtils.isNotBlank(province)) {
				detachedCriteria.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if (StringUtils.isNotBlank(city)) {
				detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if (StringUtils.isNotBlank(district)) {
				detachedCriteria.add(Restrictions.like("r.district", "%"+district+"%"));
			}
			
		}
		
		subareaService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize",
				"decidedzone","subareas"});
		return NONE;
	}
	
	/**
	 * 功能：
	 * 1.分区数据poi导出；
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException {
		/**
		 * 1.获取全部分区数据；
		 * 2.生成一个内存Excel文件；
		 * 3.根据http协议，一个流两个头返回文件；
		 */
		List<Subarea> list = subareaService.findAll();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet();
		HSSFRow row1 = hssfSheet.createRow(0);
		//列标题
		row1.createCell(0).setCellValue("分区编号");
		row1.createCell(1).setCellValue("开始编号");
		row1.createCell(2).setCellValue("结束编号");
		row1.createCell(3).setCellValue("位置信息");
		row1.createCell(4).setCellValue("省市区");
		for (Subarea subarea : list) {
			HSSFRow dataRow = hssfSheet.createRow(hssfSheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}	
		String filename = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);
		
		//获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		hssfWorkbook.write(out);
		return NONE;

	}
	/**
	 * 功能：
	 * 1.导入分区数据Excel文件，使用POI解析；
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String importXls() throws FileNotFoundException, IOException{
		List<Subarea> subareas = new ArrayList<>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(subareaFile));
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		for (Row row : hssfSheet) {
			int firstRow = row.getRowNum();
			if (firstRow == 0) {
				continue;
			}
			//获取每行每列的值
			 String id 		   = row.getCell(0).getStringCellValue();
			 String region_id  = row.getCell(1).getStringCellValue();
			 String addresskey = row.getCell(2).getStringCellValue();
			 String startnum   = row.getCell(3).getStringCellValue();
			 String endnum     = row.getCell(4).getStringCellValue();
			 String single     = row.getCell(5).getStringCellValue();
			 String position   = row.getCell(6).getStringCellValue();
			 //封装每行数据
			/* //根据id查询定区对象
			 Decidedzone decidedzone = decidedzoneService.findById(id);*/
			 //先根据region_id查询区域对象，如果有，才将这行分区数据存储
			 Region region = regionService.findById(region_id);
			 Subarea subarea = null;
			 if (region != null) {
				subarea = new Subarea(id, null, region, addresskey, startnum, endnum, single, position);
				subareas.add(subarea);
			 }
		}
		subareaService.saveBatch(subareas);
		hssfWorkbook.close();
		return NONE;
	}
	/**
	 * 功能：
	 * 1.返回所有的未关联到定区的分区数据，以json格式；
	 * @return
	 */
	public String listajax() {
		List<Subarea> list = subareaService.findListNotAssociation();
		this.java2Json(list, new String[] {"region","decidedzone"});
		return NONE;
		
	}
	/**
	 * 功能：
	 * 1.查询相应定区id的分区对象
	 * @return
	 */
	public String findListByDecidedzoneId() {
		List<Subarea> list= subareaService.findListByDecidedzoneId(model.getDecidedzone().getId());
		this.java2Json(list, new String[] {"decidedzone","subareas"});
		return NONE;
		
	}
	
	/**S
	 * 查询区域分区分布图数据
	 */
	public String findSubareasGroupByProvince(){
		List<Object> list = subareaService.findSubareasGroupByProvince();
		this.java2Json(list, new String[]{});
		return NONE;
	}
	
}
