package com.bstek.ureport.expression.model.expr;

import java.util.ArrayList;
import java.util.List;

import com.bstek.ureport.build.Context;
import com.bstek.ureport.expression.model.Condition;
import com.bstek.ureport.expression.model.Expression;
import com.bstek.ureport.expression.model.data.ExpressionData;
import com.bstek.ureport.model.Cell;

/**
 * @author Jacky.gao
 * @since 2016年11月18日
 */
public abstract class BaseExpression implements Expression{
	protected String expr;
	
	@Override
	public final ExpressionData<?> execute(Cell cell,Cell currentCell, Context context) {
		ExpressionData<?>  data=compute(cell,currentCell,context);
		return data;
	}
	
	protected abstract ExpressionData<?> compute(Cell cell,Cell currentCell, Context context);
	
	protected List<Cell> filterCells(Cell cell,Context context,Condition condition,List<Cell> targetCells) {
		if(condition==null){
			return targetCells;
		}
		List<Cell> list=new ArrayList<Cell>();
		for(Cell targetCell:targetCells){
			boolean conditionResult=true;
			List<Object> dataList=targetCell.getBindData();
			if(dataList==null){
				conditionResult=false;				
			}else{
				for(Object obj:dataList){
					boolean result=condition.filter(cell,targetCell, obj, context);
					if(!result){
						conditionResult=false;
						break;
					}
				}
			}
			if(!conditionResult){
				continue;
			}
			list.add(targetCell);
		}
		return list;
	}
	
	public void setExpr(String expr) {
		this.expr = expr;
	}
	public String getExpr() {
		return expr;
	}
}