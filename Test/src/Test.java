import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
//591957332985842
//100000000000
	
	public static void main(String[] args){
		pharseId(592765835797361l);
	}
	
	
	
	
	
	public static Long pharseId(Long id){
		id=591957332985842l/1000-100000000000l+1000000000000l;
		Date date=new Date(id);
		SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss");
		System.out.println(format.format(date));
		return id;
	}
}
