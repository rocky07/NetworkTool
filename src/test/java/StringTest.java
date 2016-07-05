import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StringTest {

	public static <T> void main(String ag[]){
		List<Integer> foo=new ArrayList<Integer>(); 
		List<Integer> bar=new ArrayList<Integer>();
		Set<Integer> foobar=new TreeSet<Integer>();
		for(int i =0;i<=100;i++){
			if(i%3==0){
				foo.add(i);
				foobar.add(i);
			}else if(i%5==0){
				bar.add(i);
				foobar.add(i);
			}
		}
		System.out.println(foo);
		System.out.println(bar);
		System.out.println(foobar);
	}
}
