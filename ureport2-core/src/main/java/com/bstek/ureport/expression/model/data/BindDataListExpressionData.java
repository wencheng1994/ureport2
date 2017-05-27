package com.bstek.ureport.expression.model.data;

import java.util.List;

import com.bstek.ureport.build.BindData;

/**
 * @author Jacky.gao
 * @since 2017年4月28日
 */
public class BindDataListExpressionData implements ExpressionData<List<BindData>>{
	private List<BindData> list;
	
	public BindDataListExpressionData(List<BindData> list) {
		this.list = list;
	}

	@Override
	public List<BindData> getData() {
		return list;
	}
}