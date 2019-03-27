package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.utils.PageBean;

public interface IDecidedzoneService {

	public void save(Decidedzone model, String[] subareaid);

	public void pageQuery(PageBean pageBean);

	public Decidedzone findById(String id);

	public List<Decidedzone> findByIdAndStation(Decidedzone model);

}
