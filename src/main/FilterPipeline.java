package main;

import java.util.Collection;
import java.util.LinkedList;

import org.opencv.core.Rect;

public class FilterPipeline {
	
	@FunctionalInterface
	public interface Filter{
		public boolean test(CvEngine pipe, Rect rect);
	}
	
	Collection<Filter> filterProceduers;
	CvEngine pipe;
	
	public FilterPipeline(){
		this.filterProceduers = new LinkedList<>();
	}
	
	public void injectPipeDependency(CvEngine pipe){
		this.pipe = pipe;
	}
	
	public boolean eval(Rect rect){
		boolean state = true;
		for(Filter filter : filterProceduers){
			if(!filter.test(this.pipe, rect)){
				state = false;
				break;
			}
		}
		return state;
	}
	
	public void addFilter(Filter filter){
		this.filterProceduers.add(filter);
	}
	
	public void resetFilterPipeline(){
		this.filterProceduers.clear();
	}
	
}
