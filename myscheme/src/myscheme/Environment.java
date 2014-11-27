package myscheme;

public class Environment {
	public Object vars;
	public Object vals;
	public Environment parent;
	
	public Environment(){
	}
	
	public Environment(Object vars, Object vals, Environment parent){
		this.vars = vars;
		this.vals = vals;
		this.parent = parent;
	}
}
