package util.codeGenerate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	public String name() default "";
	public String chineseName() default "";
	public String type() default "";
	public String note() default "";
	public boolean nullAble() default false;
	public boolean editAble() default true;
	public int length() default -1;
}
