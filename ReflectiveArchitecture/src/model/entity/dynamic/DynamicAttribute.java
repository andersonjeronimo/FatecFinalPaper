package model.entity.dynamic;

public class DynamicAttribute {
	private String name;
    private Class<?> className;
    private Object value;
       
    public DynamicAttribute(String nome, Class<?> className){
        this.name = nome;
        this.className = className;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClassName() {
        return className;
    }

    public void setClassName(Class<?> className) {
        this.className = className;
    }

    public Object getValue() {
        return value;
    }    
   
	public void setValue(Object value) {
        if(this.className.isAssignableFrom(value.getClass())){
            this.value = value;
        }else{
            throw new ClassCastException("Tipos incompatíveis: " + this.className + "/" + value.getClass());
        }        
    }    
}
