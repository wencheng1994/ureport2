package com.bstek.ureport.build.cell.down;

import java.util.List;

import com.bstek.ureport.build.Context;
import com.bstek.ureport.model.Cell;
import com.bstek.ureport.model.Row;

/**
 * @author Jacky.gao
 * @since 2016年11月7日
 */
public class CellDownDuplicateUnit {
	private Cell mainCell;
	private int mainCellRowNumber;
	private Context context;
	private DownDuplicate downDuplicate;
	private DownDuplocatorWrapper downDuplocatorWrapper;
	public CellDownDuplicateUnit(Context context,DownDuplocatorWrapper downDuplocatorWrapper,Cell mainCell,int mainCellRowNumber,int rowSize) {
		this.context=context;
		this.downDuplocatorWrapper=downDuplocatorWrapper;
		this.mainCell=mainCell;
		this.mainCellRowNumber=mainCellRowNumber;
		this.downDuplicate=new DownDuplicate(mainCell,rowSize,context);
	}
	public void duplicate(Cell cell,int index){
		downDuplicate.setIndex(index);
		for(CellDownDuplicator childDuplicator:downDuplocatorWrapper.getMainCellChildren()){
			Cell newCell=childDuplicator.duplicateChildrenCell(downDuplicate, cell, mainCell,false);
			processChildrenCells(newCell,childDuplicator.getCell(),downDuplicate,childDuplicator.isNonChild());
			childDuplicator.setNonChild(false);
		}
		for(CellDownDuplicator cellDownDuplicator:downDuplocatorWrapper.getCellDuplicators()){
			cellDownDuplicator.duplicate(downDuplicate,cell);
		}
		Row newRow=downDuplicate.newRow(cell.getRow(),mainCellRowNumber);
		cell.setRow(newRow);
		newRow.getCells().add(cell);
		cell.getColumn().getCells().add(cell);
		context.addReportCell(cell);
		downDuplicate.reset();
	}
	
	public void complete(){
		downDuplicate.complete();
	}
	
	private void processChildrenCells(Cell cell,Cell originalCell,DownDuplicate downDuplicate,boolean parentNonChild){
		List<CellDownDuplicator> childCellDownDuplicators=downDuplocatorWrapper.fetchChildrenDuplicator(originalCell);
		if(childCellDownDuplicators==null){
			return;
		}
		for(CellDownDuplicator duplicator:childCellDownDuplicators){				
			Cell newCell=duplicator.duplicateChildrenCell(downDuplicate, cell, originalCell,parentNonChild);
			processChildrenCells(newCell,duplicator.getCell(),downDuplicate,duplicator.isNonChild());
			duplicator.setNonChild(false);
		}
	}
}